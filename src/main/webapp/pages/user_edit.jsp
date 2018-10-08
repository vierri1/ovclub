<%@include file="/fragments/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="/logged/user_edit" method="post">
    <label><span style="font-weight: bold">Логин:</span> ${logged_user.login}</label><br>
    <label>
        <span style="font-weight: bold">Имя:</span> <input name="name" type="text" value="${logged_user.name}"/>
    </label><br>
    <label>
        <span style="font-weight: bold">Команда:</span> ${logged_user.team} ${logged_user.captain ? (Капитан) : ""}
    </label><br>
    <input type="submit" value="Сохранить"/><br>
</form>
<%@include file="/fragments/footer.jsp" %>
