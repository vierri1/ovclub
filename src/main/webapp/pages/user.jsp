<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 02.10.2018
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/fragments/header.jsp" %>

<strong>Страничка пользователя ${user.name}</strong>
<c:if test="${user.captain}">
    <br>Капитан команды ${user.teamName}
</c:if>
<p>Имя: ${user.name}</p>
<p>Команда: ${user.teamName}</p>

<%@include file="/fragments/footer.jsp" %>
