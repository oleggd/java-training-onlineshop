package com.study.onlineshop.entity;

import com.study.onlineshop.entity.Product;
import com.study.onlineshop.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class Session {
    private String token;
    private User user;
    private LocalDateTime expireDate;
    private Cart cart;

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() { return user;}

    public void setUser(User user) {
        this.user = user;
    }

    public Cart getCart() { return cart; }

    public void setCart(Cart cart) { this.cart = cart; }

}
