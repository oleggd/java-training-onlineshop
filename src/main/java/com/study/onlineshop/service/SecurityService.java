package com.study.onlineshop.service;

import com.study.onlineshop.entity.Session;
import com.study.onlineshop.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public interface SecurityService {

   User getUser(String name);

   String getToken (Cookie[] cookies, String tokenName);

   Session getSession(String token);

   Session getSession(Cookie[] cookies, String tokenName);

   Session isAuthenticated(String login, String password);

   boolean isAuthorized(String user, String object);

   void removeSession(Cookie[] cookies, String tokenName);

}
