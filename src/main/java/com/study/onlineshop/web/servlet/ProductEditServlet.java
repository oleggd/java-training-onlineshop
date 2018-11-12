package com.study.onlineshop.web.servlet;

import com.study.onlineshop.entity.Product;
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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductEditServlet extends HttpServlet {

    private ProductService  productService  = ServiceLocator.getService(DefaultProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PageGenerator pageGenerator = PageGenerator.instance();

        // security check
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME.ofPattern("yyyy-MM-dd HH:mm:ss");    //2018-11-01T11:03
        HashMap<String, Object> parameters = new HashMap<>();
        Product product = new Product();

        // get product for editing
        String currentID = req.getParameter("id");
        String name = req.getParameter("name");
        LocalDateTime creationDate = LocalDateTime.parse(req.getParameter("creationDate"));
        double price = Double.parseDouble(req.getParameter("price"));

        product.setId(Integer.parseInt(currentID));
        product.setName(name);
        product.setCreationDate(creationDate);
        product.setPrice(price);

        parameters.put("product", product);

        String page = pageGenerator.getPage("product_edit", parameters);
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Product product = new Product();

        // get product for editing
        String currentID = req.getParameter("id");
        String name      = req.getParameter("name");
        LocalDateTime    creationDate = LocalDateTime.parse(req.getParameter("creationDate"));
        double price     = Double.parseDouble(req.getParameter("price"));

        product.setId(Integer.parseInt(currentID));
        product.setName(name);
        product.setCreationDate(creationDate);
        product.setPrice(price);
        productService.set(product);

        resp.sendRedirect("/products");
    }


    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

}
