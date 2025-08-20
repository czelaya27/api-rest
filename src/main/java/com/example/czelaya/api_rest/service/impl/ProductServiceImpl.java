package com.example.czelaya.api_rest.service.impl;

import com.example.czelaya.api_rest.entity.Product;
import com.example.czelaya.api_rest.entity.StatusProduct;
import com.example.czelaya.api_rest.repository.IProductRepository;
import com.example.czelaya.api_rest.service.IProductService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    public ProductServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    @SneakyThrows
    public Product update(Long id, Product product) {
        Product p = productRepository.findById(id).orElseThrow(()-> new Exception("Product " + id + " not found"));
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setAmount(product.getAmount());
        p.setDescription(product.getDescription());
        return productRepository.save(p);
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found" + " " + id));
        productRepository.deleteById(id);
    }

    @Override
    public Product updateStatus(Long id, StatusProduct status) {
        var p = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found" + id));
        p.setStatus(status);
        return productRepository.save(p);
    }

    @Override
    public List<Product> findAllByStatus(StatusProduct status) {
        return productRepository.findAllByStatus(status);
    }
}
