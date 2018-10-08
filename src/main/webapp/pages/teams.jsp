<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/fragments/header.jsp" %>
<c:if test="${'user_has_no_team'.equals(param.mess)}">
    Вы пока не состоите в команде! <br>
    Организуйте свою или вступите в существующую команду!
</c:if>
<c:if test="${logged_user != null && logged_user.team == null}">
    <p><a href="/logged/create_team">Создать команду</a></p>
</c:if>
<p>Список команд: </p>
<c:forEach items="${teams}" var="team">
    <p style="font-weight: bold"><a href="/team?id=${team.id}">${team.name}</a></p>
</c:forEach>
<%@include file="/fragments/footer.jsp" %>
