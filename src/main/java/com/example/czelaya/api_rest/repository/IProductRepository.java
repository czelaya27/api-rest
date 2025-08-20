package com.example.czelaya.api_rest.repository;

import com.example.czelaya.api_rest.entity.Product;
import com.example.czelaya.api_rest.entity.StatusProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    Optional<Product> findById(Long id);
    List<Product> findAllByStatus(StatusProduct status);
}
