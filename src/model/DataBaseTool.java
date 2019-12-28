package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
	 * @param name the name of the costumer's account.
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
	 * @param name the name of the costumer's account.
	 * @param password the password that the costumer will use to login.
	 */
	public void createUser(String name, String password) {
		try (Connection con = dataSource.getConnection();) {
			System.out.println(con != null ? "Connected" : "Conexion failed");
			PreparedStatement st = con.prepareStatement(
					"INSERT INTO customers(name,password) VALUES('" + name + "','" + password + "');");
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
