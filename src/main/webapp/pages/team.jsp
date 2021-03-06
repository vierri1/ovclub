<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/fragments/header.jsp" %>
<strong style="font-weight: bold">Страница команды ${team.name}</strong>
<p><span style="font-weight: bold">Капитан команды:</span> <a href="/user?id=${captain.id}">${captain.name}</a></p>
<c:if test="${logged_user != null && logged_user.team == null}">
    <a href="/logged/join_team?id=${team.id}">Вступить в команду</a>
</c:if>

<c:if test="${leave_message != null}">
    <p>${leave_message}</p>
</c:if>
<c:if test="${'team_created'.equals(param.mess)}">
    <p>Поздравляем, команда успешно создана!</p>
</c:if>
<ui>
    <p>Список участников: </p>
    <c:forEach var="user" items="${team.users}">
        <li><a href="/user?id=${user.id}">${user.name}</a> ${user.captain ? "(cap)" : ""}</li>
    </c:forEach>
</ui>
<c:if test="${logged_user != null && logged_user.team == team.name}">
    <a href="/logged/leave_team?id=${team.id}">Покинуть команду</a>
</c:if>
<%@include file="/fragments/footer.jsp" %>