package com.study.onlineshop.web.servlet;

import com.study.onlineshop.entity.Cart;
import com.study.onlineshop.entity.Product;
import com.study.onlineshop.entity.Session;
import com.study.onlineshop.service.CartService;
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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class CartServlet extends HttpServlet {
    private CartService     cartService  = ServiceLocator.getService(CartService.class);
    private SecurityService securityService = ServiceLocator.getService(DefaultSecurityService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PageGenerator pageGenerator = PageGenerator.instance();
        //List<Product> products = cartService.getAll();
        //Cart cart = cartService.getAll
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

        Product product = new Product();

        // get product for editing
        String name      = req.getParameter("name");
        LocalDateTime creationDate = LocalDateTime.parse(req.getParameter("creationDate"));
        double price     = Double.parseDouble(req.getParameter("price"));

        product.setName(name);
        product.setCreationDate(creationDate);
        product.setPrice(price);
        //productService.add(product);

        resp.sendRedirect("/products");
    }
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
