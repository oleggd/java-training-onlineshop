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
import org.springframework.web.bind.annotation.*;

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
    //@Autowired
    //private CartService cartService;
    @Autowired
    private ProductService productService;

    PageGenerator pageGenerator = PageGenerator.instance();

    @RequestMapping(path = "/cart", method = RequestMethod.GET)
    @ResponseBody
    protected String getCart(@CookieValue(value = "user-token",required = false) String userToken) throws ServletException, IOException {
        HashMap<String, Object> parameters = new HashMap<>();

        Session session = securityService.getSession(userToken);
        Cart cart = session.getCart();

        if (cart != null) {
            parameters.put("products", cart.getProducts());
            parameters.put("disabled", session.getUser().getRole().equals("User") ? "btn" : "btn disabled");
        } else {
            parameters.put("products", new ArrayList<>());
        }
        String page = pageGenerator.getPage("cart", parameters);
        return page;
    }

    @RequestMapping(path = "/cart/add", method = RequestMethod.GET)
    protected String addToCart(@CookieValue(value = "user-token",required = false) String userToken, @RequestParam("id") int id) throws ServletException, IOException {

        Session session  = securityService.getSession(userToken);

        Cart cart = session.getCart();
        if ( cart == null) {
            cart = new Cart();
        }
        Product product  = productService.getProduct(id);
        cart.getProducts().add(product);
        session.setCart(cart);

        System.out.println("cartAdd doGet : " + id);
        return "redirect:/products";
    }

    @RequestMapping(path = "/cart/delete", method = RequestMethod.GET)
    protected String deleteFromCart(@CookieValue(value = "user-token",required = false) String userToken, @RequestParam("id") int id) throws ServletException, IOException {

        Session session = securityService.getSession(userToken);

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
        return "redirect:/cart";
    }
}
