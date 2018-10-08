<%@include file="/fragments/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${'err_registration'.equals(param.err)}">
    <p style="color: darkred">Ошибка регистрации!</p>
</c:if>
<form action="/registration" method="post">
    <label>
        <span style="font-weight: bold">Логин:</span> <input required name="login" type="text"/>
    </label><br>
    <label>
        <span style="font-weight: bold">Пароль:</span> <input required name="password" type="password"/>
    </label><br>
    <label>
        <span style="font-weight: bold">Имя:</span> <input required name="name" type="text"/>
    </label><br>
    <label>
        <span style="font-weight: bold">Фамилия:</span> <input required name="surname" type="text"/>
    </label><br>
    <label>
        <span style="font-weight: bold">Дата рождения:</span> <input required name="birthDay" type="date"/>
    </label><br>
    <input type="submit" value="Зарегистрироваться"/><br>
</form>
<%@include file="/fragments/footer.jsp" %>