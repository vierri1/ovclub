<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/fragments/header.jsp" %>
<strong style="font-weight: bold">Страница команды ${team.name}</strong>
<p><span style="font-weight: bold">Капитан команды:</span> <a href="/user?id=${captain.id}">${captain.name}</a></p>
<ui>
    <p>Список участников: </p>
    <c:forEach var="user" items="${team.users}">
        <li><a href="/user?id=${user.id}">${user.name}</a> ${user.captain ? "(cap)" : ""}</li>
    </c:forEach>
</ui>
<%@include file="/fragments/footer.jsp" %>