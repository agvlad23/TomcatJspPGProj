<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 14.01.2022
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.Calendar" %>
<html>
<head class="mdl-layout__header">
    <div class="mdl-layout__header-row">
        <span class="mdl-layout-title"> Personal stuff management</span>
        <div class="mdl-layout-spacer"></div>
        <tag:formatDate date="<%=Calendar.getInstance().getTime()%>"
                        format="dd-MM-YYYY hh:mm"></tag:formatDate>
        <nav class="mdl-navigation mdl-layou--large-screen-only">
            <a class="mdl-navigation__link" href="/"
        </nav>
    </div>
    <title>Title</title>
</head>
<body>

</body>
</html>
