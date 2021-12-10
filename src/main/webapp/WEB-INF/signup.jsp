<%--
  Created by IntelliJ IDEA.
  User: danil
  Date: 11.11.2021
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Sign Up</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
<div class="page">
    <h1>Sign up</h1>
    <form action="${pageContext.request.contextPath}/signup" method="post">
        <input type="text" name="name" required placeholder="Name" minlength="2"><br>
        <input type="text" name="surname" required placeholder="Surname" minlength="2"><br>
        <input type="email" name="email" required placeholder="E-mail"><br>
        <input type="text" name="login" required placeholder="Login" minlength="2"><br>
        <input type="password" name="password" required placeholder="Password" minlength="8"><br>
        <input type="password" name="confirm_password" required placeholder="Confirm password" minlength="8"><br>
        <button type="submit">Sign up</button>
    </form>
    <a href="${pageContext.request.contextPath}/login">Sign in</a>
</div>
</body>
</html>
