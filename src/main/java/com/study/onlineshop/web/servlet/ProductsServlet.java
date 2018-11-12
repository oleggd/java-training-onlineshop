package com.study.onlineshop.web.servlet;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.study.onlineshop.entity.Cart;
import com.study.onlineshop.entity.Product;
import com.study.onlineshop.entity.Session;
import com.study.onlineshop.entity.User;
import com.study.onlineshop.service.ProductService;
import com.study.onlineshop.service.SecurityService;
import com.study.onlineshop.service.ServiceLocator;
import com.study.onlineshop.service.impl.DefaultProductService;
import com.study.onlineshop.service.impl.DefaultSecurityService;
import com.study.onlineshop.web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsServlet extends HttpServlet {
    private ProductService  productService  = ServiceLocator.getService(DefaultProductService.class);
    private SecurityService securityService = ServiceLocator.getService(DefaultSecurityService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PageGenerator pageGenerator = PageGenerator.instance();
        List<Product> products = productService.getAll();
        //Cart cart = cartService.getAll
        HashMap<String, Object> parameters = new HashMap<>();

        Cookie[] cookies = req.getCookies();
        Session session = securityService.getSession(cookies,"user-token");

        parameters.put("products", products);
        parameters.put("disabled", session.getUser().getRole().equals("Admin")? "btn" : "btn disabled");
        parameters.put("cartDisabled", session.getUser().getRole().equals("User")? "btn" : "btn disabled");

        String page = pageGenerator.getPage("products", parameters);
        resp.getWriter().write(page);
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
