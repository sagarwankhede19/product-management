package com.sagar.product_management.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sagar.product_management.dto.ApiResponse;
import com.sagar.product_management.dto.ProductMapper;
import com.sagar.product_management.dto.ProductPatchRequest;
import com.sagar.product_management.dto.ProductRequest;
import com.sagar.product_management.dto.ProductResponse;
import com.sagar.product_management.entity.Product;
import com.sagar.product_management.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(
            @Valid @RequestBody ProductRequest request) {

        Product product = ProductMapper.toEntity(request);

        Product savedProduct = productService.createProduct(product);

        ProductResponse response = ProductMapper.toResponse(savedProduct);

        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>(
                true,
                "Product created successfully",
                response
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "productId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ProductResponse> products = productService
                .getAllProducts(pageable)
                .map(ProductMapper::toResponse);

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(
            @PathVariable Long id) {

        Product product = productService.getProductById(id);

        ProductResponse response = ProductMapper.toResponse(product);

        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>(
                true,
                "Product fetched successfully",
                response
        );

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        Product product = ProductMapper.toEntity(request);

        Product updatedProduct = productService.updateProduct(id, product);

        ProductResponse response = ProductMapper.toResponse(updatedProduct);

        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>(
                true,
                "Product updated successfully",
                response
        );

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        ApiResponse<Void> apiResponse = new ApiResponse<>(
                true,
                "Product deleted successfully",
                null
        );

        return ResponseEntity.ok(apiResponse);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> patchProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductPatchRequest request) {

        Product updatedProduct = productService.patchProduct(id, request);

        ProductResponse response = ProductMapper.toResponse(updatedProduct);

        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>(
                true,
                "Product partially updated successfully",
                response
        );

        return ResponseEntity.ok(apiResponse);
    }
}