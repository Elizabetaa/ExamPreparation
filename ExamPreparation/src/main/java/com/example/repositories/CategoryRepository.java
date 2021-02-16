package com.example.repositories;

import com.example.models.entities.Category;
import com.example.models.entities.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(CategoryNameEnum name);
}
