package ru.openvoleyballclub.controller;

import ru.openvoleyballclub.model.Team;
import ru.openvoleyballclub.service.implementation.TeamServiceImpl;
import ru.openvoleyballclub.service.intervaces.TeamService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TeamServlet extends HttpServlet {

    private TeamService teamService;

    @Override
    public void init() throws ServletException {
        super.init();
        teamService = new TeamServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Team> teams = teamService.getAll();
        request.setAttribute("teams", teams);
        Team team = teamService.getById(request.getParameter("id"));
        request.setAttribute("team", team);
        request.getRequestDispatcher("/pages/team.jsp").forward(request, response);
    }

}
