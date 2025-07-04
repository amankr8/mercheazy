package com.mercheazy.product_service.service;

import com.mercheazy.product_service.entity.Product;
import com.mercheazy.product_service.entity.ProductRequestDto;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product getProductById(Long id);

    List<Product> searchProducts(String query);

    Product createProduct(ProductRequestDto productRequestDto);

    Product updateProduct(Long id, ProductRequestDto productRequestDto);

    Product updateProductStock(Long productId, Integer stock);

    void deleteProduct(Long id);

    void deleteAllProducts();
}
