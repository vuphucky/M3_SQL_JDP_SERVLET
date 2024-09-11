<%--
  Created by IntelliJ IDEA.
  User: BESTCODE
  Date: 9/11/2024
  Time: 9:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>quan li ng dung</title>
</head>
<body>
<center>
    <h1>Quan li nguoi dung</h1>
    <h2><a href="/users?action=create">Them moi nguoi dung</a></h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Danh sach nguoi dung</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Country</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${listUser}">
            <tr>
                <td><c:out value="${user.id}"/> </td>
                <td><c:out value="${user.name}"/><td>
                <td><c:out value="${user.email}"/><td>
                <td><c:out value="${user.country}"/><td>
                <td>
                <a href="/user?action=edit&id=${user.id}">Edit</a>
                <a href="/user?action=delete&id=${user.id}">delete</a>
            </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
