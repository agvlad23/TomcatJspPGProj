<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 14.01.2022
  Time: 12:52
  To change this template use File | Settings | File Templates.

--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show All Users</title>
</head>
<body>
<%@include file="head.jsp"%>
<table border=1 >
    <thead>
    <tr>
        <th>ID</th>
        <th>User Name</th>
        <th>Role</th>
        <th>Average Scores</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>



    <jsp:useBean id="listStuff" scope="request" type="java.util.List"/>
    <c:forEach items="${listStuff}" var="stuff">
        <tr>
            <td><c:out value="${stuff.getId()}" /></td>
            <td><c:out value="${stuff.getName()}" /></td>
            <td><c:out value="${stuff.getRole()}" /></td>
            <td><c:out value="${stuff.getAvgScore()}" /></td>
            <td><a href="Stuff?action=edit&id=<c:out value="${stuff.getId()}"/>">Update</a></td>
            <td><a href="Stuff?action=delete&id=<c:out value="${stuff.getId()}"/>">Delete</a></td>
        </tr>
    </c:forEach>

</tbody>
</table>
<p><a href="Stuff?action=new">Add User</a></p>
<p><a href="Score">Scores</a></p>
</body>
</html>
