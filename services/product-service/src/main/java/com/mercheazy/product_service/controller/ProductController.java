package com.mercheazy.product_service.controller;

import com.mercheazy.product_service.entity.ProductRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/products")
public interface ProductController {

    @GetMapping
    ResponseEntity<?> getProducts();

    @GetMapping("/{id}")
    ResponseEntity<?> getProductById(@PathVariable Long id);

    @GetMapping("/search")
    ResponseEntity<?> searchProducts(@RequestParam String query);

    @PostMapping
    ResponseEntity<?> createProduct(@RequestBody ProductRequestDto productRequestDto);

    @PutMapping("/{id}")
    ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestParam ProductRequestDto productRequestDto);

    @PutMapping("/{productId}/stock")
    ResponseEntity<?> updateProductStock(@PathVariable Long productId, @RequestParam Integer stock);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Long id);

    @DeleteMapping
    ResponseEntity<?> deleteAllProducts();
}
