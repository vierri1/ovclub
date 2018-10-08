package ru.openvoleyballclub.controller;

import ru.openvoleyballclub.model.User;
import ru.openvoleyballclub.service.implementation.UserServiceImpl;
import ru.openvoleyballclub.service.intervaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = userService.getAuthUser(login, password);
        if (user != null) {
            request.getSession().setAttribute("logged_user", user);
            response.sendRedirect("/user");
        } else {
            request.getRequestDispatcher("/pages/login.jsp?err=error_login").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("logout".equals(request.getParameter("action"))) {
            request.getSession().invalidate();

        }
        if (request.getSession().getAttribute("logged_user") != null) {
            response.sendRedirect("/user");
        } else {
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
        }
    }
}
