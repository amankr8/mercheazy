package com.mercheazy.product_service.service.impl;

import com.mercheazy.product_service.entity.Product;
import com.mercheazy.product_service.entity.ProductRequestDto;
import com.mercheazy.product_service.repository.ProductRepository;
import com.mercheazy.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> searchProducts(String query) {
        return List.of();
    }

    @Override
    public Product createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setStock(productRequestDto.getStock());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setStock(productRequestDto.getStock());
        return productRepository.save(product);
    }

    @Override
    public Product updateProductStock(Long productId, Integer changeInStock) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        int newStock = product.getStock() + changeInStock;
        product.setStock(newStock);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }
}
