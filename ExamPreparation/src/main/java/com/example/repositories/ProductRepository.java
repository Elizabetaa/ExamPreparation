package com.example.repositories;

import com.example.models.entities.Product;
import com.example.models.entities.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT SUM (p.price) FROM Product p ")
    BigDecimal getTotalPrice();


    List<Product> findAllByCategory_Name(CategoryNameEnum nameEnum);
}
