package com.example.models.view;

import java.math.BigDecimal;

public class ProductView {

    private Long id;
    private String name;
    private BigDecimal price;

    public ProductView() {
    }

    public Long getId() {
        return id;
    }

    public ProductView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductView setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductView setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
