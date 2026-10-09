package com.sagar.product_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sagar.product_management.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
