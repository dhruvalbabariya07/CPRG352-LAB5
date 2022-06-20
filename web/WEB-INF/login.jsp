<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
    <center>
    <form method="POST" action="login">
        <h1> Login</h1>
        <label>Username:</label>
        <input name="username" type="text" value="${username}">
      <br>
        <label>Password:</label>
        <input name="password" type="password" value="${password}">
      <br >
        <input type="submit" value="Log in">    
    </form>
            <c:if test="${invalidMessage}">
                <p>Invalid login.</p>
    </center>
  </body>
</html>