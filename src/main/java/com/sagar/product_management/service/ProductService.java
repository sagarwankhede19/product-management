package com.sagar.product_management.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sagar.product_management.dto.ProductPatchRequest;
import com.sagar.product_management.entity.Product;

public interface ProductService {

    Product createProduct(Product product);

    Page<Product> getAllProducts(Pageable pageable);

    Product getProductById(Long id);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);
    
    Product patchProduct(Long id, ProductPatchRequest request);
}