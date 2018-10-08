package ru.openvoleyballclub.controller;

import ru.openvoleyballclub.model.Team;
import ru.openvoleyballclub.service.implementation.TeamServiceImpl;
import ru.openvoleyballclub.service.implementation.UserServiceImpl;
import ru.openvoleyballclub.service.intervaces.TeamService;
import ru.openvoleyballclub.service.intervaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TeamServlet extends HttpServlet {

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
        Team team = teamService.getById(request.getParameter("id"));
        request.setAttribute("team", team);
        request.setAttribute("captain", userService.getCaptain(team));
        request.getRequestDispatcher("/pages/team.jsp").forward(request, response);
    }
}