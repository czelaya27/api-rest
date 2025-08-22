package com.example.czelaya.api_rest.service.impl;

import com.example.czelaya.api_rest.entity.Category;
import com.example.czelaya.api_rest.exceptions.ResourceNotFoundException;
import com.example.czelaya.api_rest.exceptions.BadRequestException;
import com.example.czelaya.api_rest.repository.ICategoryRepository;
import com.example.czelaya.api_rest.service.ICategoryService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    public CategoryServiceImpl(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) {
        if(categoryRepository.existsByName(category.getName())){
            throw new BadRequestException("Category already exists");
        }
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean findByName(String nameCategory) {
        return categoryRepository.existsByName(nameCategory);
    }

    @Override
    public Category findById(Long idCategory) {
        return categoryRepository.findById(idCategory)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found " + idCategory));
    }

    @Override
    public Category updateCategory(Long idCategory, Category category) {
        Category c = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found " + idCategory));
        if(categoryRepository.existsByName(category.getName()) && !category.getName().equals(c.getName())){
            throw new BadRequestException("Category already exists");
        }
        c.setName(category.getName());
        return categoryRepository.save(c);
    }

    @Override
    public void deleteCategoryById(Long idCategory) {
        Optional<Category> category = categoryRepository.findById(idCategory);
        if(category.isEmpty()){
            throw new ResourceNotFoundException("Category not found " + idCategory);
        }
        categoryRepository.deleteById(idCategory);
    }
}
