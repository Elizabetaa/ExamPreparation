package com.example.services.impl;

import com.example.models.entities.Category;
import com.example.models.entities.Product;
import com.example.models.entities.enums.CategoryNameEnum;
import com.example.models.service.ProductAddServiceModel;
import com.example.models.view.ProductView;
import com.example.repositories.ProductRepository;
import com.example.services.CategoryService;
import com.example.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;


    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public void addProduct(ProductAddServiceModel productAddServiceModel) {
        Product product = this.modelMapper.map(productAddServiceModel, Product.class);
        Category category = this.categoryService.findByName(productAddServiceModel.getCategory());
        product.setCategory(category);
        this.productRepository.save(product);

    }

    @Override
    public BigDecimal totalPrice() {

        return this.productRepository.getTotalPrice();
    }

    @Override
    public List<ProductView> getProductsByCategory(CategoryNameEnum nameEnum) {

        List<ProductView> productViews = new ArrayList<>();
        this.productRepository.findAllByCategory_Name(nameEnum).forEach(p -> {
            productViews.add(modelMapper.map(p,ProductView.class));
        });
        return productViews;
    }

    @Override
    public void buyProduct(Long id) {
        this.productRepository.delete(this.productRepository.getOne(id));
    }

    @Override
    public void buyAll() {
        this.productRepository.deleteAll();
    }


}
