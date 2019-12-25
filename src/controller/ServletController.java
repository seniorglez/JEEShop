package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.User;

/**
 * Servlet implementation class ServletController
 */
@WebServlet("/ServletController")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("Index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Context ctx = null;
		DataSource dataSource = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		User us = null;
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String submit = request.getParameter("submit");

		try {
			ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/database");
			con = dataSource.getConnection();
			System.out.println(con != null ? "Connected" : "Conexion failed");
			st = con.prepareStatement(
					"SELECT*FROM customers WHERE customers.name = '" + name + "' AND customers.password = '" + password + "';");
			rs = st.executeQuery();
			us = new User();
			us.setName(name);
			us.setPassword(password);
			if (rs.next()) {
				if (submit.equalsIgnoreCase("Login")) {
					System.out.println("Login in");
					HttpSession session = request.getSession();
					session.setAttribute("user", us);
					response.sendRedirect("Shoop.jsp");
				} else {
					response.sendRedirect("Index.jsp");
				}

			} else {
				if (submit.equalsIgnoreCase("Singin")) {
					System.out.println("creating a new account");
					st = con.prepareStatement(
							"INSERT INTO customers(name,password) VALUES('" + name + "','" + password + "');");
					st.executeUpdate();
					response.sendRedirect("LogIn.jsp");
				}else {
					response.sendRedirect("Index.jsp");
				}
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			response.getWriter().close();
		}
	}
}
