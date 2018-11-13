package com.study.onlineshop.web.controller;

import com.study.onlineshop.entity.Cart;
import com.study.onlineshop.entity.Product;
import com.study.onlineshop.entity.Session;
import com.study.onlineshop.service.CartService;
import com.study.onlineshop.service.ProductService;
import com.study.onlineshop.service.SecurityService;
import com.study.onlineshop.web.templater.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@Controller
public class CartController {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ProductService productService;

    PageGenerator pageGenerator = PageGenerator.instance();

    @RequestMapping(path = "/cart", method = RequestMethod.GET)
    protected void getCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> parameters = new HashMap<>();

        Cookie[] cookies = request.getCookies();
        Session session = securityService.getSession(cookies,"user-token");

        Cart cart = session.getCart();

        if (cart != null) {
            parameters.put("products", cart.getProducts());
            parameters.put("disabled", session.getUser().getRole().equals("User") ? "btn" : "btn disabled");
        } else {
            parameters.put("products", new ArrayList<>());
        }
        String page = pageGenerator.getPage("cart", parameters);
        response.getWriter().write(page);
    }

    @RequestMapping(path = "/cart/add", method = RequestMethod.GET)
    protected void addToCart(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") int id) throws ServletException, IOException {

        Product product  = productService.getProduct(id);
        Cookie[] cookies = request.getCookies();
        Session session  = securityService.getSession(cookies,"user-token");

        Cart cart = session.getCart();
        if ( cart == null) {
            cart = new Cart();
        }
        cart.getProducts().add(product);
        session.setCart(cart);

        System.out.println("cartAdd doGet : " + id);
        response.sendRedirect("/products");
    }

    @RequestMapping(path = "/cart/delete", method = RequestMethod.GET)
    protected void deleteFromCart(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") int id) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        Session session = securityService.getSession(cookies,"user-token");

        Cart cart = session.getCart();
        if ( cart == null) {
            cart = new Cart();
        }

        Iterator iterator = cart.getProducts().iterator();
        Product product = null;

        while (iterator.hasNext()) {
            product = (Product) iterator.next();
            if (product.getId() == id) {
                iterator.remove();
                break;
            }
        }
        session.setCart(cart);

        System.out.println("cartDelete doGet : " + id);
        response.sendRedirect("/cart");
    }
}
