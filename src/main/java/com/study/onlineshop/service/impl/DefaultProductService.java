package com.study.onlineshop.service.impl;

import com.study.onlineshop.dao.ProductDao;
import com.study.onlineshop.entity.Product;
import com.study.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class DefaultProductService implements ProductService {

    private ProductDao productDao;
    @Autowired
    public DefaultProductService(ProductDao productDao) {        this.productDao = productDao;    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public void set(Product product) {
        productDao.set(product);
    }

    @Override
    public void add(Product product) {
        productDao.add(product);
    }

    @Override
    public void removeById(int id) {
        productDao.removeById(id);
    }

    @Override
    public Product getProduct(int id) {
        return productDao.getProduct(id);
    }
}
