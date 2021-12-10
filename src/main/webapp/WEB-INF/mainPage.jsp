<%--
  Created by IntelliJ IDEA.
  User: danil
  Date: 17.11.2021
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Car Rental</title>
    <link rel="icon" href="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij48cGF0aCBkPSJNNyAxMy41YzAtLjgyOC0uNjcyLTEuNS0xLjUtMS41cy0xLjUuNjcyLTEuNSAxLjUuNjcyIDEuNSAxLjUgMS41IDEuNS0uNjcyIDEuNS0xLjV6bTkgMWMwLS4yNzYtLjIyNC0uNS0uNS0uNWgtN2MtLjI3NiAwLS41LjIyNC0uNS41cy4yMjQuNS41LjVoN2MuMjc2IDAgLjUtLjIyNC41LS41em00LTFjMC0uODI4LS42NzItMS41LTEuNS0xLjVzLTEuNS42NzItMS41IDEuNS42NzIgMS41IDEuNSAxLjUgMS41LS42NzIgMS41LTEuNXptLTE3LjI5OC02LjVoLTIuMjAyYy0uMjc2IDAtLjUuMjI0LS41LjV2LjUxMWMwIC43OTMuOTI2Ljk4OSAxLjYxNi45ODlsMS4wODYtMnptMTkuMzE4IDMuMTY4Yy0uNzYxLTEuNDEzLTEuNjk5LTMuMTctMi42ODQtNC44MTItLjc4Ni0xLjMxMi0xLjM3LTEuOTM4LTIuNzUxLTIuMTg3LTEuMzk1LS4yNS0yLjY4MS0uMzQ3LTQuNTg1LS4zNDdzLTMuMTkuMDk3LTQuNTg1LjM0N2MtMS4zODEuMjQ4LTEuOTY1Ljg3NS0yLjc1MSAyLjE4Ny0uOTgxIDEuNjM3LTEuOTEzIDMuMzgyLTIuNjg0IDQuODEyLS42ODcgMS4yNzMtLjk4IDIuNDEyLS45OCAzLjgwNiAwIDEuMzE4LjQyIDIuNDE1IDEgMy44MTd2Mi4yMDljMCAuNTUyLjQ0OCAxIDEgMWgxLjVjLjU1MiAwIDEtLjQ0OCAxLTF2LTFoMTN2MWMwIC41NTIuNDQ4IDEgMSAxaDEuNWMuNTUyIDAgMS0uNDQ4IDEtMXYtMi4yMDljLjU4LTEuNDAzIDEtMi40OTkgMS0zLjgxNyAwLTEuMzk0LS4yOTMtMi41MzMtLjk4LTMuODA2em0tMTUuNjQxLTMuNzg0Yy42Ny0xLjExNy44NTItMS4xNDkgMS4zOS0xLjI0NiAxLjI2OC0uMjI3IDIuNDU1LS4zMTYgNC4yMzEtLjMxNnMyLjk2My4wODggNC4yMzEuMzE2Yy41MzguMDk3LjcyLjEyOSAxLjM5IDEuMjQ2LjQwOC42ODEuODEgMS4zODggMS4xOTUgMi4wODEtMS40NTYuMjItNC4wMi41MzUtNi44MTYuNTM1LTMuMDQ4IDAtNS41MTctLjMzNi02LjgwNS0uNTU1LjM4Mi0uNjg2Ljc3OS0xLjM4NiAxLjE4NC0yLjA2MXptMTEuNTk1IDEwLjYxNmgtMTEuOTQ4Yy0xLjY3MSAwLTMuMDI2LTEuMzU0LTMuMDI2LTMuMDI2IDAtMS42NDEuNTA2LTIuNDIxIDEuMTg0LTMuNjc4IDEuMDQxLjIwNSAzLjk2Ny43MDQgNy44MTYuNzA0IDMuNDgxIDAgNi41NjEtLjQ1NSA3LjgzNC0uNjcyLjY2NCAxLjIzMSAxLjE2NiAyLjAxIDEuMTY2IDMuNjQ2IDAgMS42NzItMS4zNTUgMy4wMjYtMy4wMjYgMy4wMjZ6bTUuNTI2LTEwYy4yNzYgMCAuNS4yMjQuNS41di41MTFjMCAuNzkzLS45MjYuOTg5LTEuNjE2Ljk4OWwtMS4wODYtMmgyLjIwMnoiLz48L3N2Zz4=">
</head>
<body>
<header>
    <a href="${pageContext.request.contextPath}/login">
        <img src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjQiIGhlaWdodD0iMjQiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgZmlsbC1ydWxlPSJldmVub2RkIiBjbGlwLXJ1bGU9ImV2ZW5vZGQiPjxwYXRoIGQ9Ik01IDIzaC0ydi0xMGw4Ljk5MS04LjAwNWMxLjEyNC45OTggMi4yNSAxLjk5NyAzLjM3OCAyLjk5NmwyLjI1NSAxLjk5N2MxLjEyNy45OTkgMi4yNTIgMi4wMTMgMy4zNzYgMy4wMTJ2MTBoLTJ2LTkuMTE4bC03LjAwOS02LjIxNS02Ljk5MSA2LjIydjkuMTEzem0yLTJoMTB2MmgtMTB2LTJ6bTAtM2gxMHYyaC0xMHYtMnptMTAtM3YyaC0xMHYtMmgxMHptLTUtMTRsMTIgMTAuNjMyLTEuMzI4IDEuNDkzLTEwLjY3Mi05LjQ4MS0xMC42NzIgOS40ODEtMS4zMjgtMS40OTMgMTItMTAuNjMyeiIvPjwvc3ZnPg==">
    </a>
    <a href="${pageContext.request.contextPath}/cabinet?userId=${user.getId()}">
        <img src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjQiIGhlaWdodD0iMjQiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgZmlsbC1ydWxlPSJldmVub2RkIiBjbGlwLXJ1bGU9ImV2ZW5vZGQiPjxwYXRoIGQ9Ik0xMiAwYzYuNjIzIDAgMTIgNS4zNzcgMTIgMTJzLTUuMzc3IDEyLTEyIDEyLTEyLTUuMzc3LTEyLTEyIDUuMzc3LTEyIDEyLTEyem04LjEyNyAxOS40MWMtLjI4Mi0uNDAxLS43NzItLjY1NC0xLjYyNC0uODUtMy44NDgtLjkwNi00LjA5Ny0xLjUwMS00LjM1Mi0yLjA1OS0uMjU5LS41NjUtLjE5LTEuMjMuMjA1LTEuOTc3IDEuNzI2LTMuMjU3IDIuMDktNi4wMjQgMS4wMjctNy43OS0uNjc0LTEuMTE5LTEuODc1LTEuNzM0LTMuMzgzLTEuNzM0LTEuNTIxIDAtMi43MzIuNjI2LTMuNDA5IDEuNzYzLTEuMDY2IDEuNzg5LS42OTMgNC41NDQgMS4wNDkgNy43NTcuNDAyLjc0Mi40NzYgMS40MDYuMjIgMS45NzQtLjI2NS41ODYtLjYxMSAxLjE5LTQuMzY1IDIuMDY2LS44NTIuMTk2LTEuMzQyLjQ0OS0xLjYyMy44NDggMi4wMTIgMi4yMDcgNC45MSAzLjU5MiA4LjEyOCAzLjU5MnM2LjExNS0xLjM4NSA4LjEyNy0zLjU5em0uNjUtLjc4MmMxLjM5NS0xLjg0NCAyLjIyMy00LjE0IDIuMjIzLTYuNjI4IDAtNi4wNzEtNC45MjktMTEtMTEtMTFzLTExIDQuOTI5LTExIDExYzAgMi40ODcuODI3IDQuNzgzIDIuMjIyIDYuNjI2LjQwOS0uNDUyIDEuMDQ5LS44MSAyLjA0OS0xLjA0MSAyLjAyNS0uNDYyIDMuMzc2LS44MzYgMy42NzgtMS41MDIuMTIyLS4yNzIuMDYxLS42MjgtLjE4OC0xLjA4Ny0xLjkxNy0zLjUzNS0yLjI4Mi02LjY0MS0xLjAzLTguNzQ1Ljg1My0xLjQzMSAyLjQwOC0yLjI1MSA0LjI2OS0yLjI1MSAxLjg0NSAwIDMuMzkxLjgwOCA0LjI0IDIuMjE4IDEuMjUxIDIuMDc5Ljg5NiA1LjE5NS0xIDguNzc0LS4yNDUuNDYzLS4zMDQuODIxLS4xNzkgMS4wOTQuMzA1LjY2OCAxLjY0NCAxLjAzOCAzLjY2NyAxLjQ5OSAxIC4yMyAxLjY0LjU5IDIuMDQ5IDEuMDQzeiIvPjwvc3ZnPg==">
    </a>
    <a href="${pageContext.request.contextPath}/logout">Log out</a>
</header>
<menu>
    <div>
        <form action="${pageContext.request.contextPath}/getCarByMark">
            <label>Filter by mark:</label>
            <select onchange="this.form.submit()" name="mark">
                <option></option>
                <c:forEach items="${carMarks}" var="mark">
                    <c:choose>
                        <c:when test="${mark.name().equals(markName)}">
                            <option selected value="${mark.name()}">${mark.name()}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${mark.name()}">${mark.name()}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </form>
        <form action="${pageContext.request.contextPath}/getCarByClass">
            <label>Filter by class:</label>
            <select onchange="this.form.submit()" name="car_class">
                <option></option>
                <c:forEach items="${carClass}" var="car_class">
                    <option value="${car_class.name()}">${car_class.name()}</option>
                </c:forEach>
            </select>
        </form>
        <p>
            <strong>Sort:</strong>
            <c:choose>
                <c:when test="${markName != null}">
                    <a href="${pageContext.request.contextPath}/getCarByNameAZ?mark=${markName}">Name A-z</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/getCarByNameAZ">Name A-z</a>
                </c:otherwise>
            </c:choose>
            <strong>|</strong>
            <a href="${pageContext.request.contextPath}/getCarByNameZA">Name z-A</a>
            <strong>|</strong>
            <a href="${pageContext.request.contextPath}/getCarByCostEC">Cost E-c</a>
            <strong>|</strong>
            <a href="${pageContext.request.contextPath}/getCarByCostCE">Cost C-e</a>
        </p>


    </div>
    <c:choose>
        <c:when test="${cars != null}">
            <table>
                <c:forEach items="${cars}" var = "car">
                    <tr>
                        <td>
                            <c:out value="${car}"/>
                            <a href="${pageContext.request.contextPath}/takeOrder?carId=${car.getId()}&userId=${user.getId()}">Order</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
    </c:choose>

    <table>
        <c:forEach items="${carsByClassList}" var = "carClass">
            <tr>
                <td>
                    <c:out value="${carClass}"/>
                    <a href="${pageContext.request.contextPath}/takeOrder?carId=${carClass.getId()}&userId=${user.getId()}">Order</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <table>
        <c:forEach items="${carsByMarkList}" var = "carMark">
            <tr>
                <td>
                    <c:out value="${carMark}"/>
                    <a href="${pageContext.request.contextPath}/takeOrder?carId=${carMark.getId()}&userId=${user.getId()}">Order</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <table>
        <c:forEach items="${carByNameAZ}" var="carNameAZ">
            <tr>
                <td><c:out value="${carNameAZ}"/></td>
            </tr>
        </c:forEach>
    </table>
    <table>
        <c:forEach items="${carByNameZA}" var="carNameZA">
            <tr>
                <td><c:out value="${carNameZA}"/></td>
            </tr>
        </c:forEach>
    </table>
    <table>
        <c:forEach items="${carByCostEC}" var="carCostEC">
            <tr>
                <td><c:out value="${carCostEC}"/></td>
            </tr>
        </c:forEach>
    </table>
    <table>
        <c:forEach items="${carByCostCE}" var="carCostCE">
            <tr>
                <td><c:out value="${carCostCE}"/></td>
            </tr>
        </c:forEach>
    </table>
</menu>
</body>
</html>
