package com.example.czelaya.api_rest.controller;

import com.example.czelaya.api_rest.dto.product.CreateProductDto;
import com.example.czelaya.api_rest.dto.product.UpdateProductDto;
import com.example.czelaya.api_rest.entity.Product;
import com.example.czelaya.api_rest.entity.StatusProduct;
import com.example.czelaya.api_rest.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody CreateProductDto createProductDto) {
        Product newProduct = productService.save(createProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductDto dto) {
        try{
            return ResponseEntity.ok(productService.update(id, dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<Product>> getProductByStatus(@PathVariable StatusProduct status) {
        List<Product> products = productService.findAllByStatus(status);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<?> getProductByNameLike(@PathVariable String name) {
        Optional<Product> product = productService.findByName(name);
        return product.isPresent() ? ResponseEntity.ok(product.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody StatusProduct status) {
        try {
            Product updatedProduct = productService.updateStatus(id, status);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/search/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable Long categoryId) {
        List<Product> products = productService.findByCategory(categoryId);
        return ResponseEntity.ok(products);
    }
}
