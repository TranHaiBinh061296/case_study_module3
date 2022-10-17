<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Management Application</title>
</head>
<body>
<center>
    <h1>Product Management</h1>
    <h2>
        <a href="/category?action=create">Add New Category</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Category</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="category" items="${listCategory}">
            <tr>
                <td><c:out value="${category.getId()}"/></td>
                <td><c:out value="${category.getName()}"/></td>
                <td>
                    <a href="/category?action=edit&id=${category.getId()}">Edit</a>
                    <a href="/category?action=delete&id=${category.getId()}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>