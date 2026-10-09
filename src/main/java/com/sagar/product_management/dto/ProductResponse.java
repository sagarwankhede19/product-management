package com.sagar.product_management.dto;

import java.math.BigDecimal;

public class ProductResponse {

    private Long productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private Integer stock;

    public ProductResponse(Long productId, String productName,
            String description, BigDecimal price, Integer stock) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }
}