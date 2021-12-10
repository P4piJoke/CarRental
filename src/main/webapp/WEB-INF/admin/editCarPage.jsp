<%--
  Created by IntelliJ IDEA.
  User: danil
  Date: 24.11.2021
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit car</title>
</head>
<body>
<header>
    <h1>Edit car page</h1>
</header>
<main>
    <form action="${pageContext.request.contextPath}/editCar" method="post">
        <input name="carName" type="text" required placeholder="Name" value="${car.getName()}"><br>
        <input name="carMark" type="text" required placeholder="Mark" value="${car.getCarMark()}"><br>
        <input name="carClass" type="text" required placeholder="Class" value="${car.getCarClass()}"><br>
        <input name="carCost" type="number" required placeholder="Cost" value="${car.getCost()}"><br>
        <br>
        <button type="submit" name="carId" value="${car.getId()}">Save changes</button>
    </form>
    <a href="${pageContext.request.contextPath}/admin">Return</a>
</main>
</body>
</html>
