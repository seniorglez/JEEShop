/*
 *
 *  Copyright (c) 2019 Diego Dominguez Gonzalez
 *
 *	This file is part of JEEShop.
 *
 *  JEEShop is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or any later version.
 *
 *	JEEShop is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 *	You should have received a copy of the GNU General Public License
 *	along with JEEShop. If not, see <https://www.gnu.org/licenses/>.
 */
package model;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * A class that allows us to read and write a sql database
 * 
 * @author Diego Dominguez Gonzalez
 *
 */
public class DataBaseTool {
	/**
	 * The Context of the JEE App
	 */
	private Context ctx = null;
	/**
	 * The DataSource that allows the connection with the database
	 */
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
	 * Checks if a costumer exists on the database
	 * 
	 * @param name     the name which identifies the costumer's account.
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

	private int checkUserId(User u) {
		try (Connection con = dataSource.getConnection();) {
			System.out.println(con != null ? "Connected" : "Conexion failed");
			PreparedStatement st = con.prepareStatement("SELECT*FROM customers WHERE customers.name = '" + u.getName()
					+ "' AND customers.password = '" + u.getPassword() + "';");
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				int i = rs.getInt("id");
				rs.close();
				return i;
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Adds a new costumer to the database
	 * 
	 * @param name     the name which identifies the costumer's account.
	 * @param password the password that the costumer will use to login.
	 */
	public void createUser(String name, String password) {

		insertData("INSERT INTO customers(name,password) VALUES('" + name + "','" + password + "');");

	}

	/**
	 * Gets a List of Products that represents all the products on the database.
	 * 
	 * @return List of Products that represents all the products on the database.
	 */
	public List<Product> getProducts() {
		String searchQuery = "SELECT idProduct, description, price FROM products";
		List<Product> products = new ArrayList<>();
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(searchQuery);) {
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

	private int getLastBillid(int clientId) {
		try (Connection con = dataSource.getConnection();) {
			System.out.println(con != null ? "Connected" : "Conexion failed");
			PreparedStatement st = con.prepareStatement("SELECT MAX(bills.id) FROM bills, customers WHERE customers.id="+clientId);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				int i = rs.getInt(1);
				rs.close();
				return i;
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	

	public void addPurchase(User us, Map<Integer, Integer> cart) {
		int clientId=checkUserId(us);
		insertData("INSERT INTO bills(client_id, purchase_date) VALUES (" + clientId + ", NOW())");
		int billId=getLastBillid(clientId);
		cart.forEach((p,u)->insertData("INSERT INTO bill_lines (bill_id, product_id, units) VALUES("+billId+","+p+","+u+")"));
	}

	/**
	 * Gets a product object that represents a product stored on the database.
	 * 
	 * @param code The unitary code which identifies the product.
	 * @return the product object that represents the product stored on the
	 *         database.
	 */
	public Product getProduct(int code) {
		String searchQuery = "SELECT idProduct, description, price FROM products WHERE idProduct='" + code + "'";
		Product p = new Product();
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(searchQuery);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p.setCode(rs.getInt("idProduct"));
				p.setDescription(rs.getString("description"));
				p.setPrice(rs.getDouble("price"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	/**
	 * Executes a sql statement on the database.
	 * 
	 * @param sql The sql statement to execute.
	 */
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
