<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@page import="controller.ServletController"%>
<%@page import="model.Product"%>
<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>

<%!List<Product> products;
	HashMap<Product, Integer> cart;
	ServletController sc = new ServletController(true);
	HttpSession session;%>
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>Shop</title>
<style type="text/css">
table {
	border: 1px solid black;
	border-collapse: collapse;
	width: 100%;
}

th, td {
	border: 1px solid black;
	padding: 15px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	height: 50px;
	background-color: #4CAF50;
	color: white;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>
</head>
<body>
	<h1>WELCOME TO THE SHOP DEAR CUSTOMER</h1>
	<%
		products = sc.getProducts();
		cart = new HashMap<Product, Integer>();
		session = request.getSession();
		if (!session.isNew() && session.getAttribute("cart") != null)
			cart = (HashMap<Product, Integer>) session.getAttribute("cart");
	%>
	<form action="Shoop.jsp" method="post">
		<b>Products:</b> <select name="product">
			<%
				for (Product prod : products) {
					System.out.println("Loading product: " + prod);
			%>
			<option><%=prod%></option>
			<%
				}
			%>
		</select> <b>Number: </b> <input type="Number" Name="number" /> <br /> <input
			type="submit" name="Option" value="Add to the cart" />
	</form>
	<br>
	<%
		if (!cart.isEmpty()) {
	%>
	<div style="overflow-x: auto">
		<table>
			<tr>
				<th></th>
				<th>Product</th>
				<th>Price</th>
				<th>Units</th>
			</tr>

			<%
				int number;
					double total = 0;
					for (Product p : cart.keySet()) {
						number = cart.get(p);
			%>
			<tr>
				<td></td>
				<td><%=p.getDescription()%></td>
				<td><%=p.getPrice()%></td>
				<td><%=number%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<%
		}
	%>
</body>
</html>