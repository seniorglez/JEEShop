<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@page import="controller.ServletController"%>
<%@page import="model.Product"%>
<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>

<%!List<Product> products;
	HashMap<Integer, Integer> cart;
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
		cart = new HashMap<Integer, Integer>();
		session = request.getSession();
		if (!session.isNew() && session.getAttribute("cart") != null)
			cart = (HashMap<Integer, Integer>) session.getAttribute("cart");
	%>
	<form action="Shoop.jsp" method="post">
		<b>Products:</b> <select name="product">
			<%
				for (Product prod : products) {
					System.out.println("Loading product: " + prod);
			%>
			<option value="<%=prod.getCode()%>"><%=prod%></option>
			<%
				}
			%>
		</select> <b>Number: </b> <input type="Number" Name="number" value="1"/> <br /> <input
			type="submit" name="submit" value="Add to the cart" />
	</form>
	<br>
	<%
		if (request.getParameter("submit") != null) {
			if (request.getParameter("submit").equals("Add to the cart")) {
				Integer code = Integer.parseInt((String) request.getParameter("product"));
				Integer units = Integer.parseInt((String) request.getParameter("number"));
				System.out.println("Adding " + units + " of id " + code);
				cart.put(code, units);
				session.setAttribute("cart", cart);
			}
			if (!cart.isEmpty()) {

				switch (request.getParameter("submit")) {
				case "Log Out":
					session.invalidate();
					response.sendRedirect("Index.jsp");
					break;
				case "Buy":
					sc.createPurchase((User) session.getAttribute("user"), cart);
					cart = new HashMap<Integer, Integer>();
					break;

				case "Delete product":
					String s = request.getParameter("productCheck");
					if (s != null) {
						String[] strs = s.split(";");
						for (String e : strs) {
							Integer i = Integer.parseInt(e);
							if (cart.containsKey(i))
								cart.remove(i);
							session.setAttribute("cart", cart);
						}
					}
				}
	%>
	<div style="overflow-x: auto">
		<form action="Shoop.jsp" method="post">
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
							Product p;
							for (int i : cart.keySet()) {
								number = cart.get(i);
								p = sc.getProduct(i);
				%>
				<!-- why all checkboxs have the same name: https://stackoverflow.com/questions/15775412/checking-which-check-boxes-are-selected-using-java-a-jsp -->
				<tr>
					<td><input type="checkbox" name="productCheck"
						value="<%=p.getCode()%>"></td>
					<td><%=p.getDescription()%></td>
					<td><%=p.getPrice()%></td>
					<td><%=number%></td>
				</tr>
				<%
					}
				%>
				<tr>
					<td colspan="4">Total <%=total%></td>
				</tr>
			</table>
			<br> <br> <input type="submit" name="submit"
				value="Delete product" /><input type="submit" name="submit"
				value="Buy" /><input type="submit" name="submit" value="Log Out" />
		</form>
	</div>
	<%
		}
		}
	%>
</body>
</html>