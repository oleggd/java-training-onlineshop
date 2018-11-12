package com.study.onlineshop.service.impl;

import com.study.onlineshop.dao.UserDao;
import com.study.onlineshop.entity.Session;
import com.study.onlineshop.entity.User;
import com.study.onlineshop.service.SecurityService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

public class DefaultSecurityService implements SecurityService {

    private UserDao userDao;
    private List<Session> sessionList = new ArrayList<>();

    public DefaultSecurityService(UserDao UserDao) {
        this.userDao = UserDao;
    }

    @Override
    public User getUser(String name) {
        return userDao.getUser(name);
    }

    public String getToken (Cookie[] cookies, String tokenName) {

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(tokenName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public Session getSession(String token) {

        Iterator iterator = sessionList.iterator();
        Session session;

        while (iterator.hasNext()) {
            session = (Session) iterator.next();
            if (session.getToken().equals(token)) {
                // if session expired remove from list
                if (session.getExpireDate().isAfter(LocalDateTime.now())) {
                    return session;
                } else {
                    iterator.remove();
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public Session getSession(Cookie[] cookies, String tokenName) {

        String token = getToken(cookies, tokenName);
        Session session = getSession(token);
        return session;
    }


    @Override
    public Session isAuthenticated(String login, String password) {

        // if user is valid
        if (userDao.isAuthenticated(login, password)) {
            User user = userDao.getUser(login);

            Session session = new Session();
            session.setUser(user);
            session.setToken(UUID.randomUUID().toString());

            session.setExpireDate(LocalDateTime.now().plusHours(5));
            sessionList.add(session);
            return session;

        }
        return null;
   }

    @Override
    public boolean isAuthorized(String login, String object) {
        return userDao.isAuthorized(login, object);
    }

    @Override
    public void removeSession(Cookie[] cookies, String tokenName) {

        Iterator iterator = sessionList.iterator();
        Session session;

        String token = getToken(cookies, tokenName);

        while (iterator.hasNext()) {
            session = (Session) iterator.next();
            if (session.getToken().equals(token)) {
                iterator.remove();
            }
        }
    }
}
