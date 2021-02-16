package com.example.services;

import com.example.models.entities.enums.CategoryNameEnum;
import com.example.models.service.ProductAddServiceModel;
import com.example.models.view.ProductView;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(ProductAddServiceModel map);

    BigDecimal totalPrice();

    List<ProductView> getProductsByCategory(CategoryNameEnum nameEnum);

    void buyProduct(Long id);

    void buyAll();

}
