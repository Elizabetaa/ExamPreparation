package com.example.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {


    private com.example.models.entities.enums.CategoryNameEnum name;
    private String description;

    public Category() {
    }

    public Category(com.example.models.entities.enums.CategoryNameEnum name) {
        this.name = name;
    }


    @Enumerated(value = EnumType.STRING)
    public com.example.models.entities.enums.CategoryNameEnum getName() {
        return name;
    }

    public Category setName(com.example.models.entities.enums.CategoryNameEnum category) {
        this.name = category;
        return this;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }
}
