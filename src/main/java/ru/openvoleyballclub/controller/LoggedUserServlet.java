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
import java.util.List;

public class LoggedUserServlet extends HttpServlet {

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
        List<Team> teams = teamService.getAll();
        // request.setAttribute("logged_user", request.getSession().getAttribute("logged_user"));
        request.setAttribute("teams", teams);
        request.getRequestDispatcher("/pages/user.jsp").forward(request, response);
    }
}
