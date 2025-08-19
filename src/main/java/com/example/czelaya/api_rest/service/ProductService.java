package com.example.czelaya.api_rest.service;

import com.example.czelaya.api_rest.entity.Product;
import com.example.czelaya.api_rest.entity.StatusProduct;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    Product update(Long id, Product product);

    void delete(Long id);

    Product updateStatus(Long id, StatusProduct status);

    List<Product> findAllByStatus(StatusProduct status);
}
