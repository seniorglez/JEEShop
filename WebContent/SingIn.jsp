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
<title>Register</title>
</head>
<body>
<div class="singin">
      <h1>Register to Web App</h1>
      <form method="post" action="ServletController">
        <p><input type="text" name="name"  placeholder="Username"></p>
        <p><input type="password" name="password" placeholder="Password"></p>
        <p class="submit"><input type="submit" name="submit" value="Singin"></p>
      </form>
    </div>
</body>
</html>