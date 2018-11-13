package com.study.onlineshop.web.servlet;

import com.study.onlineshop.entity.Cart;
import com.study.onlineshop.entity.Session;
import com.study.onlineshop.service.SecurityService;
import com.study.onlineshop.service.ServiceLocator;
import com.study.onlineshop.service.impl.DefaultSecurityService;
import com.study.onlineshop.web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class CartServlet extends HttpServlet {
    private SecurityService securityService = ServiceLocator.getService(DefaultSecurityService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PageGenerator pageGenerator = PageGenerator.instance();
        HashMap<String, Object> parameters = new HashMap<>();

        Cookie[] cookies = req.getCookies();
        Session session = securityService.getSession(cookies,"user-token");

        Cart cart = session.getCart();

        if (cart != null) {
            parameters.put("products", cart.getProducts());
        }
        parameters.put("disabled", session.getUser().getRole().equals("User")? "btn" : "btn disabled");

        String page = pageGenerator.getPage("cart", parameters);
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("/products");
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
