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
            <p>Вы вошли как ${logged_user.name}</p>
            <p style="text-align: right"><a href="/login?action=logout">Выйти</a></p>
        </c:if>
        <c:if test="${logged_user == null}">
            <p>Приветсвую тебя, Гость!</p>
            <p style="text-align: right"><a href="/login">Войти</a></p>
        </c:if>
    </header><!-- .header-->

    <div class="middle">
        <div class="container">
            <main class="content">
