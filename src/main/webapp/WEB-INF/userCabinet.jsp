<%--
  Created by IntelliJ IDEA.
  User: danil
  Date: 30.11.2021
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <img src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjQiIGhlaWdodD0iMjQiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgZmlsbC1ydWxlPSJldmVub2RkIiBjbGlwLXJ1bGU9ImV2ZW5vZGQiPjxwYXRoIGQ9Ik01IDIzaC0ydi0xMGw4Ljk5MS04LjAwNWMxLjEyNC45OTggMi4yNSAxLjk5NyAzLjM3OCAyLjk5NmwyLjI1NSAxLjk5N2MxLjEyNy45OTkgMi4yNTIgMi4wMTMgMy4zNzYgMy4wMTJ2MTBoLTJ2LTkuMTE4bC03LjAwOS02LjIxNS02Ljk5MSA2LjIydjkuMTEzem0yLTJoMTB2MmgtMTB2LTJ6bTAtM2gxMHYyaC0xMHYtMnptMTAtM3YyaC0xMHYtMmgxMHptLTUtMTRsMTIgMTAuNjMyLTEuMzI4IDEuNDkzLTEwLjY3Mi05LjQ4MS0xMC42NzIgOS40ODEtMS4zMjgtMS40OTMgMTItMTAuNjMyeiIvPjwvc3ZnPg==" alt="Main page">
        </a>
    </header>
    <p>
        <strong>Name</strong>: ${user.getName()}
        <strong>Surname</strong>: ${user.getSurname()}
        <a href="${pageContext.request.contextPath}/logout">LogOut</a>
    </p>
    <p>
        <strong>Balance</strong>: ${user.getBalance()}
        <a href="${pageContext.request.contextPath}/topUp?userId=${user.getId()}">
            <img src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij48cGF0aCBkPSJNMCAxMmMwIDYuNjI3IDUuMzczIDEyIDEyIDEyczEyLTUuMzczIDEyLTEyLTUuMzczLTEyLTEyLTEyLTEyIDUuMzczLTEyIDEyem0xOC0xaC00djdoLTR2LTdoLTRsNi02IDYgNnoiLz48L3N2Zz4=" alt="Top up">
        </a>
    </p>
    <form action="${pageContext.request.contextPath}/receiptAction" method="post">
        <label for="pending"><strong>Pending recipes</strong></label>
        <table id="pending">
            <c:forEach items="${pending}" var = "pending">
                <tr>
                    <td>
                        <c:out value="${pending}"/>
                    </td>
                </tr>
            </c:forEach>
        </table> <br>
        <label for="active"><strong>Active recipes</strong></label>
        <table id="active">
            <c:forEach items="${active}" var = "active">
                <tr>
                    <td>
                        <c:out value="${active}"/>
                        <c:choose>
                            <c:when test="${active.getRepairBill() > 0}">
                                <c:out value="Repair: ${active.getRepairBill()}"/>
                                <button type="submit" name="repair" value="${active.getId()}">Pay repair</button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" name="return" value="${active.getId()}">Return car</button>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table> <br>
        <label for="rejected"><strong>Rejected recipes</strong></label>
        <table id="rejected">
            <c:forEach items="${rejected}" var = "rejected">
                <c:choose>
                    <c:when test="${rejected != null}">
                        <tr>
                            <td>
                                <c:out value="${rejected}"/>
                                <a href="${pageContext.request.contextPath}/checkComment?orderId=${rejected.getId()}">Check comment</a>
                            </td>
                        </tr>
                    </c:when>
                </c:choose>
            </c:forEach>
        </table> <br>
        <label for="closed"><strong>Closed recipes</strong></label>
        <table id="closed">
            <c:forEach items="${closed}" var = "closed">
                <tr>
                    <td>
                        <c:out value="${closed}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
</body>
</html>
