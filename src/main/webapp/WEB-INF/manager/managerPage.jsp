<%--
  Created by IntelliJ IDEA.
  User: danil
  Date: 26.11.2021
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Manager page</title>
</head>
<body>
    <h1>MANAGER PAGE</h1>
    <p>
        <strong>Name</strong>: ${user.getName()}
        <strong>Surname</strong>: ${user.getSurname()}
        <strong>Role</strong>: ${user.getUserRole()}
        <a href="${pageContext.request.contextPath}/logout">LogOut</a>
    </p>

</body>
</html>
