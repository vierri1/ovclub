package ru.openvoleyballclub.controller;

import ru.openvoleyballclub.model.Team;
import ru.openvoleyballclub.model.User;
import ru.openvoleyballclub.service.implementation.TeamServiceImpl;
import ru.openvoleyballclub.service.implementation.UserServiceImpl;
import ru.openvoleyballclub.service.intervaces.TeamService;
import ru.openvoleyballclub.service.intervaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyTeamServlet extends HttpServlet {

    private TeamService teamService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        teamService = new TeamServiceImpl();
        userService = new UserServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("logged_user");
        Team team = teamService.getById(String.valueOf(user.getId()));
        request.setAttribute("captain", userService.getCaptain(team));
        request.setAttribute("team", team);
        request.getRequestDispatcher("/pages/team.jsp").forward(request, response);
    }
}