<%--
  Created by IntelliJ IDEA.
  User: danil
  Date: 11.11.2021
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Log in</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
<div class="page">
    <main>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="loader"></div>
            <input type="text" name="login" required placeholder="Login"><br>
            <input type="password" name="password" required placeholder="Password" minlength="8"><br>

            <button type="submit">Sign in</button>
        </form>
        <a href="${pageContext.request.contextPath}/signup">Sign Up</a>
    </main>
</div>

</body>
</html>
