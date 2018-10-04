<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 03.10.2018
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/fragments/header.jsp" %>
<c:if test="${'error_login'.equals(param.err)}">
    <p style="color: darkred">Ошибка входа!</p>
</c:if>
<c:if test="${'no_access'.equals(param.err)}">
    <p style="color: darkred">Для доступа к странице войдите или зарегестрируйтесь!</p>
</c:if>
<p style="font-weight: bold">Введите Ваши данные:</p>
<form action="/login" method="post">
    <label style="font-weight: bold">Логин: <input name="login" type="text"/></label><br>
    <label style="font-weight: bold">Пароль: <input name="password" type="password"/></label>
    <input type="submit" value="Войти"/>
</form>
<%@include file="/fragments/footer.jsp" %>

