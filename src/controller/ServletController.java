package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class ServletController
 */
@WebServlet("/ServletController")
@Resource(name = "jdbc/pool1")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Context ctx = null;
		DataSource dataSource=null;
		Connection con = null;
		PreparedStatement st=null;
		ResultSet rs = null;
		//igual es mejor crear aqui el objeto
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		
		try {
			ctx=new InitialContext();
			dataSource=(DataSource)ctx.lookup("java:comp/env/jdbc/pool1");
			con=dataSource.getConnection();
			System.out.println(con!=null ? "Connected" : "Conexion failed");
			st=con.prepareStatement("SELECT*FROM users WHERE users.name = 'name'AND users.password = 'passw';");
			rs=st.executeQuery();
		} catch (NamingException |SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null) {
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
