package com.example.czelaya.api_rest.service.impl;

import com.example.czelaya.api_rest.dto.category.CategoryDTO;
import com.example.czelaya.api_rest.entity.Category;
import com.example.czelaya.api_rest.exceptions.ResourceNotFoundException;
import com.example.czelaya.api_rest.exceptions.BadRequestException;
import com.example.czelaya.api_rest.mapper.CategoryMapper;
import com.example.czelaya.api_rest.repository.ICategoryRepository;
import com.example.czelaya.api_rest.service.ICategoryService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(ICategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        if(categoryRepository.existsByName(categoryDTO.getName())){
            throw new BadRequestException("Category already exists");
        }
        Category category = categoryMapper.toEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toDto).toList();
    }

    @Override
    public boolean findByName(String nameCategory) {
        return categoryRepository.existsByName(nameCategory);
    }

    @Override
    public CategoryDTO findById(Long idCategory) {
        Category category = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found " + idCategory));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDTO updateCategory(Long idCategory, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found " + idCategory));
        if(categoryRepository.existsByName(categoryDTO.getName()) && !categoryDTO.getName().equals(category.getName())){
            throw new BadRequestException("Category already exists");
        }
        category.setName(categoryDTO.getName());
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(updatedCategory);
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
