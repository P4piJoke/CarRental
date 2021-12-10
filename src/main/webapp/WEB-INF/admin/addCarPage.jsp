<%--
  Created by IntelliJ IDEA.
  User: danil
  Date: 22.11.2021
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Add car</title>
</head>
<body>
<header>
    <h1>Add car page</h1>
</header>
<main>
    <form action="${pageContext.request.contextPath}/addNewCar" method="post">
        <input name="carName" type="text" required placeholder="Car name"><br>
        <label>Mark: </label>
        <select name="mark">
            <option></option>
            <c:forEach items="${carMarks}" var="mark">
                <option value="${mark.name()}">${mark.name()}</option>
            </c:forEach>
        </select>
        <label>Class: </label>
        <select name="car_class">
            <option></option>
            <c:forEach items="${carClass}" var="car_class">
                <option value="${car_class.name()}">${car_class.name()}</option>
            </c:forEach>
        </select>
        <input name="carCost" type="number" required placeholder="Cost" min="0"><br>
        <button type="submit">Add car</button>
    </form>
    <a href="${pageContext.request.contextPath}/admin">Return</a>
</main>

</body>
</html>
