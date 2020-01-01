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

/**
 * 
 * @author Diego Dominguez Gonzalez Represents a customer registered on the web
 */
public class User {
	/**
	 * The name which identifies the costumer's account.
	 */
	private String name;
	/**
	 * The password for the costumer's account.
	 */
	private String password;

	/**
	 * Gets the name which identifies the costumer's account.
	 * 
	 * @return the name which identifies this costumer's account.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Changes the name which identifies the costumer's account.
	 * 
	 * @param name the new name which identifies this costumer's account.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the password for the costumer's account.
	 * 
	 * @return The password for this costumer's account.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Changes the password for the costumer's account.
	 * 
	 * @param password The new password for this costumer's account.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
