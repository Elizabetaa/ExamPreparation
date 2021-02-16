package com.example.services;


import com.example.models.entities.Category;
import com.example.models.entities.enums.CategoryNameEnum;

public interface CategoryService {

    void initCategories();

    Category findByName(CategoryNameEnum name);
}
