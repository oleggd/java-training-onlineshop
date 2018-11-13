package com.study.onlineshop.web.controller;

import com.study.onlineshop.entity.Product;
import com.study.onlineshop.service.ProductService;
import com.study.onlineshop.web.templater.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    //private SecurityService securityService
    PageGenerator pageGenerator = PageGenerator.instance();

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public void getAll(HttpServletResponse response) throws IOException {
        List<Product> products = productService.getAll();

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("products", products);
        parameters.put("disabled", "btn");
        parameters.put("cartDisabled", "btn");

        String page = pageGenerator.getPage("products", parameters);
        response.getWriter().write(page);
    }

    @RequestMapping(path = "/product/edit", method = RequestMethod.GET)
    public void productEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HashMap<String, Object> parameters = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME.ofPattern("yyyy-MM-dd HH:mm:ss");    //2018-11-01T11:03
        Product product = new Product();

        // get product for editing
        String currentID = request.getParameter("id");
        String name      = request.getParameter("name");
        LocalDateTime creationDate = LocalDateTime.parse(request.getParameter("creationDate"));
        double price     = Double.parseDouble(request.getParameter("price"));

        product.setId(Integer.parseInt(currentID));
        product.setName(name);
        product.setCreationDate(creationDate);
        product.setPrice(price);

        parameters.put("product", product);

        String page = pageGenerator.getPage("product_edit", parameters);
        response.getWriter().write(page);
    }

    @RequestMapping(path = "/product/edit", method = RequestMethod.POST)
    public void productEditSave(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Product product = new Product();

        // get product info for saving
        String currentID = request.getParameter("id");
        String name      = request.getParameter("name");
        LocalDateTime    creationDate = LocalDateTime.parse(request.getParameter("creationDate"));
        double price     = Double.parseDouble(request.getParameter("price"));

        product.setId(Integer.parseInt(currentID));
        product.setName(name);
        product.setCreationDate(creationDate);
        product.setPrice(price);
        productService.set(product);

        response.sendRedirect("/products");
    }

    @RequestMapping(path = "/product/delete", method = RequestMethod.GET)
    public void productDelete(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") int id) throws IOException {
        productService.removeById(id);
        response.sendRedirect("/products");
    }

    @RequestMapping(path = "/product/add", method = RequestMethod.GET)
    protected void productAddGet(HttpServletResponse response) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();

        String page = pageGenerator.getPage("product_add", null);
        response.getWriter().write(page);
    }

    @RequestMapping(path = "/product/add", method = RequestMethod.POST)
    protected void productAddPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Product product = new Product();

        // get product for editing
        String name      = request.getParameter("name");
        LocalDateTime creationDate = LocalDateTime.parse(request.getParameter("creationDate"));
        double price     = Double.parseDouble(request.getParameter("price"));

        product.setName(name);
        product.setCreationDate(creationDate);
        product.setPrice(price);
        productService.add(product);

        response.sendRedirect("/products");
    }
}
