<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 04.10.2018
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/fragments/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="/logged/user_edit" method="post">
    <label><span style="font-weight: bold">Логин:</span> ${logged_user.login}</label><br>
    <label><span style="font-weight: bold">Имя:</span> <input name="name" type="text"
                                                              value="${logged_user.name}"/></label><br>
    <label><span
            style="font-weight: bold">Команда:</span> ${logged_user.teamName} ${logged_user.captain ? (Капитан) : ""}
    </label><br>
    <input type="submit" value="Сохранить"/><br>
</form>
<%@include file="/fragments/footer.jsp" %>
