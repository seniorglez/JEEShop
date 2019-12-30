package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DataBaseTool;
import model.Product;
import model.User;

/**
 * Servlet implementation class ServletController
 */
@WebServlet("/ServletController")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataBaseTool dbt;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletController() {
		super();
		this.dbt = new DataBaseTool();
	}

	public ServletController(boolean b) {
		this.dbt = new DataBaseTool();
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

		User us = null;
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String submit = request.getParameter("submit");

		us = new User();
		us.setName(name);
		us.setPassword(password);
		if (dbt.checkUser(name, password)) {
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
				dbt.createUser(name, password);
				response.sendRedirect("LogIn.jsp");
			} else {
				response.sendRedirect("Index.jsp");
			}
		}

		response.getWriter().close();

	}

	public List<Product> getProducts() {
		return this.dbt.getProducts();
	}

	public Product getProduct(int code) {
		return dbt.getProduct(code);
	}
	
	public void createPurchase(User us, Map<Integer,Integer> cart) {
		
	}
}
