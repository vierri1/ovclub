package ru.openvoleyballclub.controller;

import ru.openvoleyballclub.model.User;
import ru.openvoleyballclub.service.implementation.TeamServiceImpl;
import ru.openvoleyballclub.service.intervaces.TeamService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LeaveTeamServlet extends HttpServlet {

    private TeamService teamService;

    @Override
    public void init() throws ServletException {
        super.init();
        teamService = new TeamServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teamId = request.getParameter("id");
        User user = (User) request.getSession().getAttribute("logged_user");
        String teamName = user.getTeam();
        String message = teamService.leaveTeam(user, teamId);
        if (message != null && message.equals("Пользователь " + user.getName() + " успешно покинул команду " + teamName + "!")) {
            request.getRequestDispatcher("/logged/my_team").forward(request, response);
        } else {
            request.setAttribute("leave_message", message);
            request.getRequestDispatcher("/pages/team.jsp").forward(request, response);
        }
    }
}
