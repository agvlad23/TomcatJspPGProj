<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 15.01.2022
  Time: 18:31
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
    <c:if test="${score!=null}">Edit Score</c:if>
    <c:if test="${score==null}">Add New Score</c:if>
</h2>
<div class="mdl-card__supporting-text">
    <c:if test="${score!=null}">
    <form name="myForm" action="Score?action=update" method="post" onsubmit="return validateForm()">
        </c:if>
        <c:if test="${score==null}">
        <form name="myForm" action="Score?action=insert" method="post" onsubmit="return validateForm()">
            </c:if>

            <c:if test="${score !=null}">
                <div class="mdl-textfield mdl-js-textfield">
                    <input type="hidden" name="id" value="<c:out value='${score.getId()}'/>"/>
                    <input type="hidden" name="idUser" value="<c:out value='${score.getIdUser()}'/>"/>
                    <input type="hidden" name="idSubject" value="<c:out value='${score.getIdSubject()}'/>"/>
                </div>

            </c:if>
            <div class="mdl-textfield mdl-js-textfield">

                <c:if test="${score ==null}">
                    <div class="mdl-textfield mdl-js-textfield">
                        <input type="text" name="id" value="<c:out value='${score.getId()}'/>"/><label
                            class="mdl-textfield__label" for="score"> ID</label>
                        <input type="text" name="idUser" value="<c:out value='${score.getIdUser()}'/>"/><label
                            class="mdl-textfield__label" for="score"> User ID</label>
                        <input type="text" name="idSubject" value="<c:out value='${score.getIdSubject()}'/>"/><label
                            class="mdl-textfield__label" for="score"> Subject ID</label>
                    </div>
                    <div class="mdl-textfield mdl-js-textfield">
                        <input type="date" name="date" value="<c:out value='${score.getDate()}'/>"/> <label
                            class="mdl-textfield__label" for="score"> Date</label>
                        <input type="text" name="nameUser" value="<c:out value='${score.getNameUser()}'/>"/><label
                            class="mdl-textfield__label" for="score"> Name</label>
                        <input type="text" name="nameSubject" value="<c:out value='${score.getNameSubject()}'/>"/><label
                            class="mdl-textfield__label" for="score"> Subject</label>
                    </div>
                </c:if>
                <c:if test="${score !=null}">
                    <div class="mdl-textfield mdl-js-textfield">
                        <input type="text" name="date" value="<c:out value='${score.getDate()}'/>"  readonly="readonly"/> <label
                        class="mdl-textfield__label" for="score"> Date</label>
                        <input type="text" name="nameUser" value="<c:out value='${score.getNameUser()}'/>" readonly="readonly"/><label
                            class="mdl-textfield__label" for="score"> Name</label>
                        <input type="text" name="nameSubject" value="<c:out value='${score.getNameSubject()}'/>" readonly="readonly"/><label
                            class="mdl-textfield__label" for="score"> Subject</label>
                    </div>
                </c:if>

                    <div class="mdl-textfield mdl-js-textfield">
                        <input class="mdl-textfield__input" type="text" name="score"
                               value="<c:out value='${score.score}'/>" id="score"/> <label
                             class="mdl-textfield__label" for="score"> Score</label>
                    </div>


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

