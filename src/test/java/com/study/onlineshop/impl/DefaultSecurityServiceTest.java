package com.study.onlineshop.service.impl;

import com.study.onlineshop.dao.UserDao;
import com.study.onlineshop.dao.jdbc.JdbcUserDao;
import com.study.onlineshop.service.SecurityService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultSecurityServiceTest {

    JdbcUserDao jdbcUserDao;
    DefaultSecurityService defaultSecurityService;

    @Before
    public void Before() {
        jdbcUserDao    = new JdbcUserDao();
        // configure services
        defaultSecurityService = new DefaultSecurityService(jdbcUserDao);

    }
    @Test
    public void getCurrentUser() {
    }

    @Test
    public void getUser() {
    }

    @Test
    public void isAuthenticated() {
        //SecurityService securityService = defaultSecurityService;

        //assertTrue(defaultSecurityService.isAuthenticated("User1","pass1"));
        //assertFalse(defaultSecurityService.isAuthenticated("User1","pass2"));
        //assertFalse(defaultSecurityService.isAuthenticated("User5","pass2"));
    }

    @Test
    public void isAuthorized() {

        //assertTrue(defaultSecurityService.isAuthorized("Guest","login"));
        //assertTrue(defaultSecurityService.isAuthorized("Guest","logout"));
        //assertTrue(defaultSecurityService.isAuthorized("User","products"));
        //assertFalse(defaultSecurityService.isAuthorized("Guest","products"));
        //assertTrue(defaultSecurityService.isAuthorized("Admin","edit"));
        //assertTrue(defaultSecurityService.isAuthorized("Admin","add"));
        //assertTrue(defaultSecurityService.isAuthorized("Admin","delete"));

    }
}