package com.study.onlineshop.dao;

import com.study.onlineshop.entity.User;

import javax.sql.DataSource;

public interface UserDao {

    User getUser(String name);

    public boolean isAuthenticated(String login, String password);

    boolean isAuthorized(String user, String object);

    void setDataSource(DataSource dataSource);
}
