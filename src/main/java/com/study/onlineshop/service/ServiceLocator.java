package com.study.onlineshop.service;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.study.onlineshop.dao.jdbc.JdbcProductDao;
import com.study.onlineshop.dao.jdbc.JdbcUserDao;
import com.study.onlineshop.service.impl.DefaultCartService;
import com.study.onlineshop.service.impl.DefaultProductService;
import com.study.onlineshop.service.impl.DefaultSecurityService;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServiceLocator {

    private static final Map<Class, Object> SERVICES = new HashMap<>();

    public void register(Class serviceClass, Object service) {
        SERVICES.put(serviceClass, service);
    }

    public static <T> T getService(Class<T> serviceClass) {
        return serviceClass.cast(SERVICES.get(serviceClass));
    }

    static
    {
        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        JdbcUserDao jdbcUserDao    = new JdbcUserDao();
        String filename = "app.properties";
        Properties appProps = new Properties();

        try (InputStream aapFile = ServiceLocator.class.getClassLoader().getResourceAsStream(filename);){
            appProps.load(aapFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jdbcUserDao.setConnectionParameters(appProps);

        // configure services
        DefaultProductService  defaultProductService  = new DefaultProductService(jdbcProductDao);
        DefaultSecurityService defaultSecurityService = new DefaultSecurityService(jdbcUserDao);
        DefaultCartService     defaultCartService     = new DefaultCartService(defaultSecurityService);

        SERVICES.put(DefaultProductService.class,defaultProductService);
        SERVICES.put(DefaultSecurityService.class,defaultSecurityService);
        SERVICES.put(DefaultCartService.class,defaultCartService);


    }
}
