package com.example.czelaya.api_rest.service.impl;

import com.example.czelaya.api_rest.dto.product.ProductDTO;
import com.example.czelaya.api_rest.entity.Category;
import com.example.czelaya.api_rest.entity.Product;
import com.example.czelaya.api_rest.entity.StatusProduct;
import com.example.czelaya.api_rest.exceptions.ResourceNotFoundException;
import com.example.czelaya.api_rest.mapper.ProductMapper;
import com.example.czelaya.api_rest.repository.ICategoryRepository;
import com.example.czelaya.api_rest.repository.IProductRepository;
import com.example.czelaya.api_rest.service.IProductService;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(IProductRepository productRepository, ICategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Category categoryId = categoryRepository.findById(productDTO.getIdCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Product product = productMapper.toEntity(productDTO);
        product.setCategory(categoryId);
        Product productSave = productRepository.save(product);
        return productMapper.toDto(productSave);
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toDto).toList();
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found " + id));
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO findByName(String name) {
        Product product = productRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Product not found " + name));
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product " + id + " not found"));
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setAmount(productDTO.getAmount());
        product.setDescription(productDTO.getDescription());
        product.setStatus(productDTO.getStatus());

        if (productDTO.getIdCategory() != null) {
            Long categoryId = productDTO.getIdCategory();
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            product.setCategory(category);
        }

        Product updatedProduct = productRepository.save(product);

        return productMapper.toDto(updatedProduct);
    }

    @Override
    @SneakyThrows
    public ResponseEntity<ProductDTO> delete(Long id) {
        productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found" + " " + id));
        productRepository.deleteById(id);
        return null;
    }

    @Override
    public ProductDTO updateStatus(Long id, StatusProduct status) {
        var product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found" + id));
        product.setStatus(status);

        Product updatedProduct = productRepository.save(product);
        return productMapper.toDto(updatedProduct);
    }

    @Override
    public List<ProductDTO> findAllByStatus(StatusProduct status) {
        List<Product> products = productRepository.findAllByStatus(status);
        return products.stream().map(productMapper::toDto).toList();
    }

    @Override
    public List<ProductDTO> findByCategory(Long categoryId) {
        List<Product> products = productRepository.findByCategory_Id(categoryId);
        return products.stream().map(productMapper::toDto).toList();
    }
}
