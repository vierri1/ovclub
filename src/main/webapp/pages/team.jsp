<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 02.10.2018
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/fragments/header.jsp" %>
<strong>Список участников команды ${team.name}:</strong>
<ui>
    <c:forEach var="user" items="${team.users}">
        <li><a href="/user?id=${user.id}">${user.name}</a> ${user.captain ? "(cap)" : ""}</li>
    </c:forEach>

</ui>
<%@include file="/fragments/footer.jsp" %>