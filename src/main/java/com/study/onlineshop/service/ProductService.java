package com.study.onlineshop.service;

import com.study.onlineshop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    void set(Product product);

    void add(Product product);

    void removeById(int id);

    Product getProduct(int id);
}
