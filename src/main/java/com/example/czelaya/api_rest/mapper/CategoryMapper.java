package com.example.czelaya.api_rest.mapper;

import com.example.czelaya.api_rest.dto.category.CategoryDTO;
import com.example.czelaya.api_rest.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    private final ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryDTO toDto(Category category){
        return modelMapper.map(category, CategoryDTO.class);
    }

    public Category toEntity(CategoryDTO categoryDTO){
        return modelMapper.map(categoryDTO, Category.class);
    }
}
