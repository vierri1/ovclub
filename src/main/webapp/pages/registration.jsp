<%@include file="/fragments/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="/registration" method="post">
    <label>
        <span style="font-weight: bold">Логин:</span> <input name="login" type="text"/>
    </label><br>
    <label>
        <span style="font-weight: bold">Пароль:</span> <input name="password" type="password"/>
    </label><br>
    <label>
        <span style="font-weight: bold">Имя:</span> <input name="name" type="text"/>
    </label><br>
    <label>
        <span style="font-weight: bold">Фамилия:</span> <input name="surname" type="text"/>
    </label><br>
    <label>
        <span style="font-weight: bold">Дата рождения:</span> <input name="birthDay" type="date"/>
    </label><br>
    <input type="submit" value="Зарегистрироваться"/><br>
</form>
<%@include file="/fragments/footer.jsp" %>