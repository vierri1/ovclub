<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 02.10.2018
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/fragments/header.jsp" %>
<c:set var="isLoggedUserPage" value="${false}"/>
<c:if test="${sessionScope.logged_user != null && (user.equals(sessionScope.logged_user) || user == null)}">
    <c:set var="user" value="${sessionScope.logged_user}"/>
    <strong style="font-weight: bold">Моя страница:</strong>
    <c:set var="isLoggedUserPage" value="${true}"/>
</c:if>

<c:if test="${user != sessionScope.logged_user}">
    <a style="font-weight: bolder">Страничка пользователя ${user.name}</a>
</c:if>

<c:if test="${user.captain}">
    <br>Капитан команды ${user.team}
</c:if>
<br><br>
<p><a style="font-weight: bolder">Имя</a>: ${user.name}</p>
<p><a style="font-weight: bolder">Фамилия</a>: ${user.surname}</p>
<p><a style="font-weight: bolder">Команда: </a>${user.team}</p>
<p><a style="font-weight: bolder">Дата рождения: </a>${user.birthDay}</p>

<c:if test="${isLoggedUserPage == true}">
    <a href="/logged/user_edit">Редактировать</a>
</c:if>
<%@include file="/fragments/footer.jsp" %>
