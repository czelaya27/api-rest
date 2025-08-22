package com.example.czelaya.api_rest.controller;

import com.example.czelaya.api_rest.dto.product.ProductDTO;
import com.example.czelaya.api_rest.entity.StatusProduct;
import com.example.czelaya.api_rest.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO newProduct = productService.save(productDTO);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) {
        return new ResponseEntity<>(productService.update(id, productDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<ProductDTO>> getProductByStatus(@PathVariable StatusProduct status) {
        List<ProductDTO> products = productService.findAllByStatus(status);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<ProductDTO> getProductByNameLike(@PathVariable String name) {
        ProductDTO product = productService.findByName(name);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ProductDTO> updateStatus(@PathVariable Long id, @RequestBody StatusProduct status) {
        return new ResponseEntity<>(productService.updateStatus(id, status), HttpStatus.OK);
    }

    @GetMapping("/search/category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getProductByCategory(@PathVariable Long categoryId) {
        List<ProductDTO> products = productService.findByCategory(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
