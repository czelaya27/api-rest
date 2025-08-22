package com.example.czelaya.api_rest.service.impl;

import com.example.czelaya.api_rest.dto.product.CreateProductDto;
import com.example.czelaya.api_rest.dto.product.UpdateProductDto;
import com.example.czelaya.api_rest.entity.Category;
import com.example.czelaya.api_rest.entity.Product;
import com.example.czelaya.api_rest.entity.StatusProduct;
import com.example.czelaya.api_rest.exceptions.ResourceNotFoundException;
import com.example.czelaya.api_rest.repository.ICategoryRepository;
import com.example.czelaya.api_rest.repository.IProductRepository;
import com.example.czelaya.api_rest.service.IProductService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    public ProductServiceImpl(IProductRepository productRepository, ICategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product save(CreateProductDto createProductDto) {
        Category categoryId = categoryRepository.findById(createProductDto.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Product p = new Product();
        p.setName(createProductDto.name());
        p.setPrice(createProductDto.price());
        p.setAmount(createProductDto.amount());
        p.setDescription(createProductDto.description());
        p.setStatus(createProductDto.status());
        p.setCategory(categoryId);
        return productRepository.save(p);
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
    public Product update(Long id, UpdateProductDto dto) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product " + id + " not found"));
        p.setName(dto.name());
        p.setPrice(dto.price());
        p.setAmount(dto.amount());
        p.setDescription(dto.description());
        p.setStatus(dto.status());

        if (dto.categoryId() != null) {
            Long categoryId = dto.categoryId();
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            p.setCategory(category);
        }

        return productRepository.save(p);
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found" + " " + id));
        productRepository.deleteById(id);
    }

    @Override
    public Product updateStatus(Long id, StatusProduct status) {
        var p = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found" + id));
        p.setStatus(status);
        return productRepository.save(p);
    }

    @Override
    public List<Product> findAllByStatus(StatusProduct status) {
        return productRepository.findAllByStatus(status);
    }

    @Override
    public List<Product> findByCategory(Long categoryId) {
        return productRepository.findByCategory_Id(categoryId);
    }
}
