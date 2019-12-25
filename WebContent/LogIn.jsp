<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>Login</title>
</head>
<body>
<div class="login">
      <h1>Login to Web App</h1>
      <form method="post" action="ServletController">
        <p><input type="text" name="name" placeholder="Username"></p>
        <p><input type="password" name="password" placeholder="Password"></p>
        <p class="submit"><input type="submit" name="submit" value="Login"></p>
      </form>
    </div>
</body>
</html>