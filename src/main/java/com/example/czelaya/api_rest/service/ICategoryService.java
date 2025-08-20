package com.example.czelaya.api_rest.service;

import com.example.czelaya.api_rest.entity.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(Category category);
    List<Category> findAll();
    boolean findByName(String nameCategory);
    Category findById(Long idCategory);
    Category updateCategory(Long idCategory, Category category);
    void deleteCategoryById(Long idCategory);
}
