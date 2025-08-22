package com.example.czelaya.api_rest.controller;

import com.example.czelaya.api_rest.entity.Category;
import com.example.czelaya.api_rest.service.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category){
        Category newCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        Category category = categoryService.findById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category){
            Category updatedCategory = new Category();
            updatedCategory.setName(category.getName());
            return new ResponseEntity<>(categoryService.updateCategory(id, updatedCategory), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
