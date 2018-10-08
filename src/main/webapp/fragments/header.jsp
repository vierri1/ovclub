<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 02.10.2018
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Open Volleyball League</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link href="../resources/style.css" rel="stylesheet">
</head>

<body>

<div class="wrapper">

    <header class="header">
        <p>OPEN VOLLEYBALL CLUB</p>
        <c:if test="${logged_user != null}">
            <p>Вы вошли как <a href="/logged/my_page">${logged_user.name}</a></p>
            <p style="width: 50%;text-align: right;float: right;"><a href="/login?action=logout">Выйти</a></p>
        </c:if>
        <c:if test="${logged_user == null}">
            <p>Приветсвую тебя, Гость!</p>
            <p style="width: 5%;text-align: right;float: right;"><a href="/login">Войти</a></p>
            <p style="width: 95%;text-align: right;float: right;"><a href="/registration">Регистрация</a></p>
        </c:if>
        <%--<p style="width: 50%;display: inline-block;float: left;width: 50%;"><a href="/logged/my_page">Моя страница</a></p>--%>
    </header><!-- .header-->

    <div class="middle">
        <div class="container">
            <main class="content">
