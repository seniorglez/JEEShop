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
        <p><input type="text" name="login" value="name" placeholder="Username"></p>
        <p><input type="password" name="password" value="password" placeholder="Password"></p>
        <p class="submit"><input type="submit" name="submit" value="Singin"></p>
      </form>
    </div>
</body>
</html>