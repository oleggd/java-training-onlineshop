package com.study.onlineshop.dao;

import com.study.onlineshop.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    List<Product> getAll();

    void set(Product product);

    void add(Product product);

    void removeById(int id);

    Product getProduct(int id);
}
