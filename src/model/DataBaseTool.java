package model;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class DataBaseTool {
	private Context ctx = null;
	private DataSource dataSource = null;

	public DataBaseTool() {
		try {
			ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/database");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks that if a costumer exists on the database
	 * 
	 * @param name     the name of the costumer's account.
	 * @param password the costumer's account.
	 */
	public boolean checkUser(String name, String password) {
		try (Connection con = dataSource.getConnection();) {
			System.out.println(con != null ? "Connected" : "Conexion failed");
			PreparedStatement st = con.prepareStatement("SELECT*FROM customers WHERE customers.name = '" + name
					+ "' AND customers.password = '" + password + "';");
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				rs.close();
				return true;
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Adds a new costumer to the database
	 * 
	 * @param name     the name of the costumer's account.
	 * @param password the password that the costumer will use to login.
	 */
	public void createUser(String name, String password) {

		insertData("INSERT INTO customers(name,password) VALUES('" + name + "','" + password + "');");

	}

	public List<Product> getProducts() {
		String searchQuery = "SELECT idProduct, description, price FROM products";
		List<Product> products = new ArrayList<>();
		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(searchQuery);) {
			ResultSet rs = ps.executeQuery();
			Product p;
			while (rs.next()) {
				p = new Product();
				p.setCode(rs.getInt("idProduct"));
				p.setDescription(rs.getString("description"));
				p.setPrice(rs.getDouble("price"));
				products.add(p);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	/**
	 * Adds the purchase stored in the HttpSession to the db
	 * 
	 * @param sesion
	 */
	public void addPurchase(HttpSession sesion) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String date = sdf.format(new Date().getTime());

		// insertData(sql);
	}

	private void insertData(String sql) {
		try (Connection con = dataSource.getConnection();) {
			System.out.println(con != null ? "Connected" : "Conexion failed");
			PreparedStatement st = con.prepareStatement(sql);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
