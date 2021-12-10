<%--
  Created by IntelliJ IDEA.
  User: danil
  Date: 26.11.2021
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Manager registration</title>
</head>
<body>
    <h1>New manager</h1>
    <form action="${pageContext.request.contextPath}/addManager" method="post">
        <input type="text" name="name" required placeholder="Name" minlength="2"><br>
        <input type="text" name="surname" required placeholder="Surname" minlength="2"><br>
        <input type="email" name="email" required placeholder="E-mail"><br>
        <input type="text" name="login" required placeholder="Login" minlength="2"><br>
        <input type="password" name="password" required placeholder="Password" minlength="8"><br>
        <input type="password" name="confirm_password" required placeholder="Confirm password" minlength="8"><br>
        <button type="submit">Create new manager</button>
    </form>
    <a href="${pageContext.request.contextPath}/admin">Return</a>
</body>
</html>
