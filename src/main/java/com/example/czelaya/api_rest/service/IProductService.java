package com.example.czelaya.api_rest.service;

import com.example.czelaya.api_rest.dto.product.CreateProductDto;
import com.example.czelaya.api_rest.dto.product.UpdateProductDto;
import com.example.czelaya.api_rest.entity.Product;
import com.example.czelaya.api_rest.entity.StatusProduct;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Product save(CreateProductDto createProductDto);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    Product update(Long id, UpdateProductDto dto);

    void delete(Long id);

    Product updateStatus(Long id, StatusProduct status);

    List<Product> findAllByStatus(StatusProduct status);
}
