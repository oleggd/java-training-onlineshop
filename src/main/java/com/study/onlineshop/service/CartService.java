package com.study.onlineshop.service;

import com.study.onlineshop.entity.Product;

import java.util.List;

public interface CartService {

    List<Product> getAll();

    void add(Product product);

    void removeProductById(int id);
}
