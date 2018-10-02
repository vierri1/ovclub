<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 02.10.2018
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
</main><!-- .content -->
</div><!-- .container-->

<aside class="left-sidebar">
    <h1>Список команд:</h1>
    <c:forEach var="team" items="${teams}">
        <br/> <a href="/team?id=${team.id}">${team.name}</a>
    </c:forEach>
</aside>
</div><!-- .middle-->

</div><!-- .wrapper -->

<footer class="footer">
    <strong>footer</strong>
</footer>
<!-- .footer -->

</body>
</html>