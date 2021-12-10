<%--
  Created by IntelliJ IDEA.
  User: danil
  Date: 17.11.2021
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
    <h1>ADMIN PAGE</h1>
    <p>
        <strong>Name</strong>: ${user.getName()}
        <strong>Surname</strong>: ${user.getSurname()}
        <strong>Role</strong>: ${user.getUserRole()}
        <a href="${pageContext.request.contextPath}/logout">LogOut</a>
    </p>
    <p>
        <a href="${pageContext.request.contextPath}/addNewCar"><strong>Add car</strong></a>

        <a href="${pageContext.request.contextPath}/addManager"><strong>New manager</strong></a>
    </p>
    <form action="${pageContext.request.contextPath}/deleteCar" method="post">
        <table>
            <c:forEach items="${cars}" var = "car">
                <tr>
                    <td>
                        <c:out value="${car}"/>
                        <a href="${pageContext.request.contextPath}/editCar?carId=${car.getId()}">Edit</a>
                        <button type="submit" name="carId" value="${car.getId()}">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
</body>
</html>
