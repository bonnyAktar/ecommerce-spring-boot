package com.example.ecommerce.repository;

import com.example.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product,Integer> {

    public List<Product> findByCategory(String category);

    List<Product> findByPriceGreaterThan(double price);

    }


