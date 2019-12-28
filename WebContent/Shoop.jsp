<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@page import="controller.ServletController"%>
<%@page import="model.Product"%>
<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%!List<Product> products;
	ServletController sc = new ServletController(true);%>
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>Shop</title>
</head>
<body>
	<h1>WELCOME TO THE SHOP DEAR CUSTOMER</h1>
	<%
		products = sc.getProducts();

		{
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
	<%
		}
	%>
</body>
</html>