package com.study.onlineshop.web.controller;

import com.study.onlineshop.entity.Session;
import com.study.onlineshop.service.SecurityService;
import com.study.onlineshop.web.templater.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class LogInOutController {
    @Autowired
    private SecurityService securityService;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    protected void loginForm(HttpServletResponse resp) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        HashMap<String, Object> parameters = new HashMap<>();
        String page = pageGenerator.getPage("login", parameters);
        resp.getWriter().write(page);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login    = request.getParameter("login");
        String password = request.getParameter("password");

        Session session = securityService.isAuthenticated(login, password);
        // if user is valid
        if ( session != null ) {
            Cookie cookie = new Cookie("user-token", session.getToken());
            response.addCookie(cookie);
            response.sendRedirect("/products");
        } else {
            response.sendRedirect("/login");
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    protected void logout(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {

        //HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Cookie[] cookies = request.getCookies();

        securityService.removeSession(cookies,"user-token");
        response.sendRedirect("/login");
    }

}
