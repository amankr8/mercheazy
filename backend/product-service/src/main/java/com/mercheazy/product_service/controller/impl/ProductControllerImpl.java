package com.mercheazy.product_service.controller.impl;

import com.mercheazy.product_service.controller.ProductController;
import com.mercheazy.product_service.entity.Product;
import com.mercheazy.product_service.entity.ProductRequestDto;
import com.mercheazy.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok(productService.getProducts().stream().map(Product::toResponseDto).toList());
    }

    @Override
    public ResponseEntity<?> getProductById(Long id) {
        return ResponseEntity.ok(productService.getProductById(id).toResponseDto());
    }

    @Override
    public ResponseEntity<?> searchProducts(String query) {
        return ResponseEntity.ok(productService.searchProducts(query).stream().map(Product::toResponseDto).toList());
    }

    @Override
    public ResponseEntity<?> createProduct(ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.createProduct(productRequestDto).toResponseDto());
    }

    @Override
    public ResponseEntity<?> updateProduct(Long id, ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.updateProduct(id, productRequestDto).toResponseDto());
    }

    @Override
    public ResponseEntity<?> updateProductStock(Long productId, Integer stock) {
        return ResponseEntity.ok(productService.updateProductStock(productId, stock).toResponseDto());
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @Override
    public ResponseEntity<?> deleteAllProducts() {
        productService.deleteAllProducts();
        return ResponseEntity.ok("All products deleted successfully");
    }
}
