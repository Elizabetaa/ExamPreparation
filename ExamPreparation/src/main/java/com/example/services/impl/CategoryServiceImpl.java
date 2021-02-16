package com.example.services.impl;

import com.example.models.entities.Category;
import com.example.models.entities.enums.CategoryNameEnum;
import com.example.repositories.CategoryRepository;
import com.example.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void initCategories() {
        if (categoryRepository.count()==0){
            Arrays.stream(com.example.models.entities.enums.CategoryNameEnum.values()).forEach(c -> {
                Category category = new Category(c);
                category.setDescription("Description for "+ c.name());
                this.categoryRepository.save(category);
            });
        }
    }

    @Override
    public Category findByName(CategoryNameEnum name) {
        return this.categoryRepository.findByName(name);
    }
}
