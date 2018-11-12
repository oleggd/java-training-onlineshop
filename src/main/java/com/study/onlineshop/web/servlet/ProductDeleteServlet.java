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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDeleteServlet extends HttpServlet {
    private ProductService  productService  = ServiceLocator.getService(DefaultProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PageGenerator pageGenerator = PageGenerator.instance();

        Integer productID = Integer.parseInt(req.getParameter("id"));
        System.out.println("doGet : " + productID);
        productService.removeById(productID);

        resp.sendRedirect("/products");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PageGenerator pageGenerator = PageGenerator.instance();
        resp.sendRedirect("/product/delete");
    }
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

}
