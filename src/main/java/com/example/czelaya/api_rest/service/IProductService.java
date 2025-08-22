package com.example.czelaya.api_rest.service;

import com.example.czelaya.api_rest.dto.product.ProductDTO;
import com.example.czelaya.api_rest.entity.StatusProduct;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductService {

    ProductDTO save(ProductDTO productDTO);

    List<ProductDTO> findAll();

    ProductDTO findById(Long id);

    ProductDTO findByName(String name);

    ProductDTO update(Long id, ProductDTO productDTO);

    ResponseEntity<ProductDTO> delete(Long id);

    ProductDTO updateStatus(Long id, StatusProduct status);

    List<ProductDTO> findAllByStatus(StatusProduct status);

    List<ProductDTO> findByCategory(Long categoryId);
}
