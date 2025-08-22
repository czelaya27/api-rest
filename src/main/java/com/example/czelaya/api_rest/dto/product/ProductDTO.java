package com.example.czelaya.api_rest.dto.product;

import com.example.czelaya.api_rest.entity.StatusProduct;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotNull(message = "Amount cannot be empty")
    @Min(value = 1, message = "Amount must be greater than 0")
    private int amount;

    @Size(min = 10, max = 200, message = "Description must be between 10 and 200 characters")
    private String description;

    @NotNull(message = "Price cannot be empty")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Double price;

    @NotNull(message = "Status cannot be empty")
    private StatusProduct status;

    @NotNull(message = "Category cannot be empty")
    private Long idCategory;

}
