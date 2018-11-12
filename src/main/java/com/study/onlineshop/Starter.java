/*
package com.study.onlineshop;

import com.study.onlineshop.dao.jdbc.JdbcProductDao;
import com.study.onlineshop.dao.jdbc.JdbcUserDao;
import com.study.onlineshop.entity.User;
import com.study.onlineshop.service.impl.DefaultCartService;
import com.study.onlineshop.service.impl.DefaultProductService;
import com.study.onlineshop.service.impl.DefaultSecurityService;
import com.study.onlineshop.web.filters.AdminSecurityFilter;
import com.study.onlineshop.web.filters.ProductSecurityFilter;
import com.study.onlineshop.web.filters.UserSecurityFilter;
import com.study.onlineshop.web.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class Starter {
    public static void main(String[] args) throws Exception {
        // configure daos
        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        JdbcUserDao    jdbcUserDao    = new JdbcUserDao();

        String filename = "app.properties";
        Properties appProps = new Properties();

        try (InputStream aapFile = Starter.class.getClassLoader().getResourceAsStream(filename);){
            appProps.load(aapFile);
        }
        jdbcUserDao.setConnectionParameters(appProps);

        // configure services
        DefaultProductService defaultProductService   = new DefaultProductService(jdbcProductDao);
        DefaultSecurityService defaultSecurityService = new DefaultSecurityService(jdbcUserDao);
        DefaultCartService defaultCartService         = new DefaultCartService(defaultSecurityService);

        // store user-name + user-token

        // servlets
        LoginServlet         loginServlet         = new LoginServlet(defaultSecurityService);
        LogoutServlet        logoutServlet        = new LogoutServlet(defaultSecurityService);
        ProductsServlet      productsServlet      = new ProductsServlet();
        ProductEditServlet   productEditServlet   = new ProductEditServlet();
        ProductAddServlet    productAddServlet    = new ProductAddServlet();
        ProductDeleteServlet productDeleteServlet = new ProductDeleteServlet();
        CartServlet          cartServlet          = new CartServlet();
        CartAddServlet       cartAddServlet       = new CartAddServlet();
        CartDeleteServlet    cartDeleteServlet    = new CartDeleteServlet();

        //
        //defaultSecurityService.setActiveUserInfo(activeTokens,activeUserList);
        productsServlet.setProductService(defaultProductService);
        productsServlet.setSecurityService(defaultSecurityService);
        //ProductsApiServlet productsApiServlet = new ProductsApiServlet(defaultProductService);
        productEditServlet.setProductService(defaultProductService);
        productAddServlet.setProductService(defaultProductService);
        productDeleteServlet.setProductService(defaultProductService);
        cartServlet.setSecurityService(defaultSecurityService);
        cartServlet.setCartService(defaultCartService);
        cartAddServlet.setSecurityService(defaultSecurityService);
        cartAddServlet.setProductService(defaultProductService);
        cartAddServlet.setSecurityService(defaultSecurityService);
        cartAddServlet.setProductService(defaultProductService);
        cartDeleteServlet.setSecurityService(defaultSecurityService);

        // config web server
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //login
        //servletContextHandler.addServlet(new ServletHolder(productsApiServlet), "/api/v1/products");
        servletContextHandler.addServlet(new ServletHolder(loginServlet), "/login");
        servletContextHandler.addServlet(new ServletHolder(logoutServlet), "/logout");
        //other
        servletContextHandler.addServlet(new ServletHolder(productsServlet), "/");
        servletContextHandler.addServlet(new ServletHolder(productsServlet), "/products");
        servletContextHandler.addServlet(new ServletHolder(productEditServlet), "/product/edit");
        servletContextHandler.addServlet(new ServletHolder(productAddServlet), "/product/add");
        servletContextHandler.addServlet(new ServletHolder(productDeleteServlet), "/product/delete");
        servletContextHandler.addServlet(new ServletHolder(cartServlet), "/cart");
        servletContextHandler.addServlet(new ServletHolder(cartAddServlet), "/cart/add");
        servletContextHandler.addServlet(new ServletHolder(cartDeleteServlet), "/cart/delete");

        FilterHolder adminHolder = new FilterHolder(new AdminSecurityFilter(defaultSecurityService));
        FilterHolder userHolder  = new FilterHolder(new UserSecurityFilter(defaultSecurityService));
        FilterHolder productHolder = new FilterHolder(new ProductSecurityFilter(defaultSecurityService));

        servletContextHandler.addFilter(userHolder, "/logout",EnumSet.of(DispatcherType.REQUEST));
        servletContextHandler.addFilter(userHolder, "/",EnumSet.of(DispatcherType.REQUEST));
        servletContextHandler.addFilter(userHolder, "/products",EnumSet.of(DispatcherType.REQUEST));
        servletContextHandler.addFilter(userHolder, "/cart",EnumSet.of(DispatcherType.REQUEST));
        servletContextHandler.addFilter(userHolder, "/cart/add",EnumSet.of(DispatcherType.REQUEST));
        servletContextHandler.addFilter(userHolder, "/cart/delete",EnumSet.of(DispatcherType.REQUEST));
        //servletContextHandler.addFilter(adminHolder, "/products",EnumSet.of(DispatcherType.REQUEST));
        servletContextHandler.addFilter(adminHolder, "/product/edit",EnumSet.of(DispatcherType.REQUEST));
        servletContextHandler.addFilter(adminHolder, "/product/add",EnumSet.of(DispatcherType.REQUEST));
        servletContextHandler.addFilter(adminHolder, "/product/delete",EnumSet.of(DispatcherType.REQUEST));


        Server server = new Server(8081);
        server.setHandler(servletContextHandler);
        server.start();
    }
}
*/
