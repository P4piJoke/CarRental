<%--
  Created by IntelliJ IDEA.
  User: danil
  Date: 16.12.2021
  Time: 23:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Order info</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/login">Return</a>
    <p>
        <strong>Order №: </strong>${order.getId()} <br>
        <strong>Client: </strong>${userByReceipt.getName()} ${userByReceipt.getSurname()}<br>
        <strong>Car: </strong>${carByReceipt.getName()} ${carByReceipt.getCarMark()} ${carByReceipt.getCarClass()}<br>
        <strong>Order status: </strong>${order.getOrderStatus()}<br>
        <strong>Manager notification</strong><br>
        <input type="text" readonly value="${order.getComment()}"><br>
    </p>
</body>
</html>
