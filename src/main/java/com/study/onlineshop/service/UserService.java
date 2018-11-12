package com.study.onlineshop.service;

public interface UserService {

    public boolean isAuthenticated(String login, String password);

    boolean isAuthorized(String user, String object);

}
