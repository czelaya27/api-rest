package com.example.czelaya.api_rest.mapper;

import com.example.czelaya.api_rest.dto.product.ProductDTO;
import com.example.czelaya.api_rest.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductDTO toDto(Product product){
        return modelMapper.map(product, ProductDTO.class);
    }

    public Product toEntity(ProductDTO productDTO){
        return modelMapper.map(productDTO, Product.class);
    }

    public void toEntity(ProductDTO productDTO, Product product){
        modelMapper.map(productDTO, product);
    }
}
