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
 * @author Diego Dominguez Gonzalez
 * 
 *         Represent a product that the web offers.
 */
public class Product {
	/**
	 * The unitary code which identifies the product.
	 */
	private int code;
	/**
	 * The name of the product
	 */
	private String description;
	/**
	 * The price of one unit of the product.
	 */
	private double price;

	/**
	 * Gets the unitary code that identifies the product.
	 * 
	 * @return This product code.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Changes the code that identifies the product.
	 * 
	 * @param code This product new code.
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Gets the name of the product.
	 * 
	 * @return This product name.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Changes the name of the product.
	 * 
	 * @param description This product new name.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the price of the product.
	 * 
	 * @return This product price.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Changes the price of the product.
	 * 
	 * @param price This product new price.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return getDescription() + " - " + getPrice();
	}

}
