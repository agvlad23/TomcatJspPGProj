<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 15.01.2022
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2 class="mdl-card__title-text">
    <c:if test="${stuff!=null}">Edit Stuff</c:if>
    <c:if test="${stuff==null}">Add New Stuff</c:if>
</h2>
<div class="mdl-card__supporting-text">
    <c:if test="${stuff!=null}">
        <form name="myForm" action="Stuff?action=update" method="post" onsubmit="return validateForm()">
    </c:if>
    <c:if test="${stuff==null}">
        <form name="myForm" action="Stuff?action=insert" method="post" onsubmit="return validateForm()">
    </c:if>

            <c:if test="${stuff !=null}">
                <input type="hidden" name="id" value="<c:out value='${stuff.getId()}'/>"/>
            </c:if>
            <div class="mdl-textfield mdl-js-textfield">
                <input class="mdl-textfield__input" type="text" name="name"
                value="<c:out value='${stuff.name}'/>" id="name"/> <label
                    class="mdl-textfield__label" for="name"> Name</label>
            </div>
        
        <div class="mdl-textfield mdl-js-textfield">
            <select class="mdl-textfield__input" name="role"
                    id="roleSelect">
                <option value="1">Teacher</option>
                <option value="2">Student</option>
            </select>
            <label
                class="mdl-textfield__label" for="roleSelect"> Role</label>
        </div>
            <input type="submit" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
                   value="save"/>

        </form>

</div>
</body>
<script type="text/javascript">
    function validateForm(){

    }
</script>
</html>
