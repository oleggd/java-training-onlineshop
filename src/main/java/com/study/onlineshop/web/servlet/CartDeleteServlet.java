package com.study.onlineshop.web.servlet;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.study.onlineshop.entity.Cart;
import com.study.onlineshop.entity.Product;
import com.study.onlineshop.entity.Session;
import com.study.onlineshop.service.CartService;
import com.study.onlineshop.service.ProductService;
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
import java.util.Iterator;

public class CartDeleteServlet extends HttpServlet {
    private SecurityService securityService = ServiceLocator.getService(DefaultSecurityService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int productID = Integer.parseInt(req.getParameter("id"));
        //Product product = productService.getProduct(productID);

        Cookie[] cookies = req.getCookies();
        Session session = securityService.getSession(cookies,"user-token");

        Cart cart = session.getCart();
        if ( cart == null) {
            cart = new Cart();
        }
        Iterator iterator = cart.getProducts().iterator();
        Product product = null;
        while (iterator.hasNext()) {
            product = (Product) iterator.next();
            if (product.getId() == productID) {
                iterator.remove();
                break;
            }
        }
        session.setCart(cart);

        System.out.println("cartDelete doGet : " + productID);

        resp.sendRedirect("/cart");
    }

    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PageGenerator pageGenerator = PageGenerator.instance();
        resp.sendRedirect("/cart/delete");
    }*/

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
