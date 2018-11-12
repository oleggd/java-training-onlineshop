package com.study.onlineshop.service.impl;

import com.study.onlineshop.entity.Product;
import com.study.onlineshop.service.CartService;
import com.study.onlineshop.service.SecurityService;

import java.util.List;

public class DefaultCartService implements CartService {

    private SecurityService securityService;

    public DefaultCartService(DefaultSecurityService defaultSecurityService) {
        this.securityService = defaultSecurityService;
    }

    @Override
    public List<Product> getAll() {
        //return securityService.getSession();
        return null;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public void removeProductById(int id) {

    }
}
