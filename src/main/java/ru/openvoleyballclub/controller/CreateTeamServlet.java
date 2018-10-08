package ru.openvoleyballclub.controller;

import ru.openvoleyballclub.model.User;
import ru.openvoleyballclub.service.implementation.TeamServiceImpl;
import ru.openvoleyballclub.service.intervaces.TeamService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateTeamServlet extends HttpServlet {


    private TeamService teamService;

    @Override
    public void init() throws ServletException {
        super.init();
        teamService = new TeamServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("logged_user");
        String teamName = request.getParameter("name");
        String message = teamService.createTeam(user, teamName);
        if (message.equals("Команда " + teamName + " успешно создана!")) {
            response.sendRedirect("/logged/my_team?mess=team_created");
        } else {
            request.setAttribute("create_message", message);
            request.getRequestDispatcher("/pages/create_team.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/create_team.jsp").forward(request, response);
    }
}
