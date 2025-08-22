package com.example.czelaya.api_rest.service;

import com.example.czelaya.api_rest.dto.category.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> findAll();
    boolean findByName(String nameCategory);
    CategoryDTO findById(Long idCategory);
    CategoryDTO updateCategory(Long idCategory, CategoryDTO categoryDTO);
    void deleteCategoryById(Long idCategory);
}
