<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 03.10.2018
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/fragments/header.jsp" %>
<p>Введите Ваши данные:</p>
<c:if test="${param.err != null && param.err.equals('error_login')}">
    <p>Ошибка входа!</p>
</c:if>
<c:if test="${param.err != null && param.err.equals('no_access')}">
    <p>Отказано в доступе!</p>
</c:if>
<form action="/login" method="post">
    <label>Логин: <input name="login" type="text"/></label><br>
    <label>Пароль: <input name="password" type="password"/></label>
    <input type="submit" value="Войти"/>
</form>
<%@include file="/fragments/footer.jsp" %>

