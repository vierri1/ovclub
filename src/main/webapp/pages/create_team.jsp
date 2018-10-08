<%@include file="/fragments/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${create_message != null}">
    <p>${create_message}</p>
</c:if>
<form action="/logged/create_team" method="post">
    <label>Название команды: <input name="name" type="text"/></label>
    <input type="submit" value="Сохранить"/><br>
</form>
<%@include file="/fragments/footer.jsp" %>