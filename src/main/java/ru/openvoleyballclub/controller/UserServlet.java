package ru.openvoleyballclub.controller;

import ru.openvoleyballclub.model.User;
import ru.openvoleyballclub.service.implementation.UserServiceImpl;
import ru.openvoleyballclub.service.intervaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getById(request.getParameter("id"));
        request.setAttribute("user", user);
        request.getRequestDispatcher("/pages/user.jsp").forward(request, response);
    }
}
