package ru.openvoleyballclub.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoggedFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("logged_user") != null) {
            chain.doFilter(req, resp);
        } else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login?err=no_access");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
