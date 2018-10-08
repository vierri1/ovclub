<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/fragments/header.jsp" %>
<p>Список команд: </p>

<c:forEach items="${teams}" var="team">
    <p style="font-weight: bold"><a href="/team?id=${team.id}">${team.name}</a></p>
</c:forEach>
<%@include file="/fragments/footer.jsp" %>
