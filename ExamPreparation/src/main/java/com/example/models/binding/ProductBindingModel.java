package com.example.models.binding;

import com.example.models.entities.Category;
import com.example.models.entities.enums.CategoryNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductBindingModel {

    private String name;
    private String description;
    private LocalDateTime neededBefore;
    private BigDecimal price;
    private CategoryNameEnum category;

    public ProductBindingModel() {
    }

    @NotBlank(message = "Name cannot be empty string")
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters")
    public String getName() {
        return name;
    }

    public ProductBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotBlank(message = "Description cannot be empty string")
    @Size(min = 5, message = "Description min length must be minimum 5 characters")
    public String getDescription() {
        return description;
    }

    public ProductBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past!")
    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public ProductBindingModel setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
        return this;
    }

    @DecimalMin(value = "0",message = "Price must be a positive number")
    public BigDecimal getPrice() {
        return price;
    }

    public ProductBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @NotNull (message = "You must select the category")
    public CategoryNameEnum getCategory() {
        return category;
    }

    public ProductBindingModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }
}
