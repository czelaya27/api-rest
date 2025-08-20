package com.example.czelaya.api_rest.dto.product;

import com.example.czelaya.api_rest.entity.StatusProduct;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateProductDto(
        @NotBlank String name,
        @NotNull Double price,
        @NotBlank String description,
        @NotNull Integer amount,
        @NotNull
        StatusProduct status,
        Long categoryId
) {}
