package com.study.onlineshop.service.impl;

import com.study.onlineshop.entity.Cart;
import com.study.onlineshop.entity.Product;
import com.study.onlineshop.service.CartService;
import com.study.onlineshop.service.ProductService;
import com.study.onlineshop.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("cartService")
public class DefaultCartService implements CartService {

    private SecurityService securityService;
    @Autowired
    private ProductService productService;

    @Autowired
    public DefaultCartService(DefaultSecurityService defaultSecurityService) {
        this.securityService = defaultSecurityService;
    }

    @Override
    public List<Product> getAll() {
        //return securityService.getSession();
        return null;
    }

    @Override
    public void add(Cart cart, int productID) {

    }

    @Override
    public void removeProductById(int id) {

    }
}
