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
    <h1 style="font-weight: bolder">Список команд:</h1>
    <c:forEach var="team" items="${teams}">
        <a href="/team?id=${team.id}">${team.name}</a> <br>
    </c:forEach>
</aside>
</div><!-- .middle-->

</div><!-- .wrapper -->

<footer class="footer">
    <p style="text-align: center; font-size: medium; color: #7e828f;">Открытая волейбольная лига.</p>
    <p style="text-align: right; font-size: smaller; color: #7e828f;">Разработчик: Попонин Андрей Витальевич</p>
    <p style="text-align: right; font-size: smaller; color: #7e828f;">2018 год.</p>
</footer>
<!-- .footer -->

</body>
</html>