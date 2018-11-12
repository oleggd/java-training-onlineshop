package com.study.onlineshop.web.servlet;

import com.study.onlineshop.entity.Cart;
import com.study.onlineshop.entity.Product;
import com.study.onlineshop.entity.Session;
import com.study.onlineshop.service.CartService;
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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class CartAddServlet extends HttpServlet {
    private ProductService  productService  = ServiceLocator.getService(DefaultProductService.class);
    private SecurityService securityService = ServiceLocator.getService(DefaultSecurityService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int productID = Integer.parseInt(req.getParameter("id"));
        Product product = productService.getProduct(productID);

        Cookie[] cookies = req.getCookies();
        Session session = securityService.getSession(cookies,"user-token");

        Cart cart = session.getCart();
        if ( cart == null) {
            cart = new Cart();
        }
        cart.getProducts().add(product);
        session.setCart(cart);

        System.out.println("cartAdd doGet : " + productID);

        resp.sendRedirect("/products");
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
