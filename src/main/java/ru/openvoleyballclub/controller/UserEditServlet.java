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
import java.util.List;

public class UserEditServlet extends HttpServlet {
    private TeamService teamService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        teamService = new TeamServiceImpl();
        userService = new UserServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name != null) {
            User user = (User) request.getSession().getAttribute("logged_user");
            user.setName(name);
            userService.update(user);
            response.sendRedirect("/user");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Team> teams = teamService.getAll();
        request.setAttribute("teams", teams);
        request.getRequestDispatcher("/pages/user_edit.jsp").forward(request, response);
    }
}
