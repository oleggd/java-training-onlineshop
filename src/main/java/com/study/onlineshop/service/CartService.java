package com.study.onlineshop.service;

import com.study.onlineshop.entity.Cart;
import com.study.onlineshop.entity.Product;

import java.util.List;

public interface CartService {

    List<Product> getAll();

    void add(Cart cart, int productID);

    void removeProductById(int id);
}
