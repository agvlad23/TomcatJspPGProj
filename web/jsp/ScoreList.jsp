<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 15.01.2022
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
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
        <th>User Name</th>
        <th>Subject Name</th>
        <th>Date</th>
        <th>Score</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>



    <jsp:useBean id="listScore" scope="request" type="java.util.List"/>
    <c:forEach items="${listScore}" var="score">
        <tr>
            <td><c:out value="${score.getNameUser()}" /></td>
            <td><c:out value="${score.getNameSubject()}" /></td>
            <td><c:out value="${score.getDate()}" /></td>
            <td><c:out value="${score.getScore()}" /></td>
            <td><a href="Score?action=edit&id=<c:out value="${score.getId()}"/>">Update</a></td>
            <td><a href="Score?action=delete&id=<c:out value="${score.getId()}"/>">Delete</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
<p><a href="Score?action=new">Add Score</a></p>
</body>
</html>

