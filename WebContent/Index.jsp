<!-- 
	Copyright (c) 2019 Diego Dominguez Gonzalez

 	This file is part of JEEShop.

    JEEShop is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or any later version.

    JEEShop is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with JEEShop. If not, see <https://www.gnu.org/licenses/>.

 -->
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>Welcome</title>
<style>
topnav {
	background-color: #333;
	overflow: hidden;
}

.topnav a {
	float: left;
	color: #68ccac;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a:hover {
	background-color: #ddd;
	color: black;
}

.topnav a.active {
	background-color: #4CAF50;
	color: white;
}

.title {
	color: #999999;
	font-family: arial, sans-serif;
	font-size: 32px;
	font-weight: bold;
	margin-top: 0px;
	margin-bottom: 1px;
}
</style>
</head>
<body>
	<div class="topnav">
		<a class="active" href="https://github.com/seniorglez/JEEShop">Source</a>
		<a href="LogIn.jsp">Login</a> <a href="SingIn.jsp">Register</a>
	</div>
	<br>
	<br>
	<br>
	<h1 class="title">Welcome</h1>
	<p>This is an example of a shop made with JEE</p>
</body>
</html>