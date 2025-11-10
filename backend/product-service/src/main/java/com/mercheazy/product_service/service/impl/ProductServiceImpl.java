package com.mercheazy.product_service.service.impl;

import com.mercheazy.product_service.entity.Product;
import com.mercheazy.product_service.dto.ProductRequestDto;
import com.mercheazy.product_service.repository.ProductRepository;
import com.mercheazy.product_service.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    @Cacheable(value = "products")
    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Cacheable(value = "products", key = "#id")
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> searchProducts(String query) {
        SearchSession searchSession = Search.session(entityManager);
        return searchSession.search(Product.class)
                .where(f -> f.wildcard()
                        .fields("name", "description")
                        .matching("*" + query.toLowerCase() + "*")
                )
                .fetchHits(20);
    }

    @CachePut(value = "products", key = "#result.id")
    @Override
    public Product createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setStock(productRequestDto.getStock());
        return productRepository.save(product);
    }

    @CachePut(value = "products", key = "#result.id")
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

    @CachePut(value = "products", key = "#result.id")
    @Override
    public Product updateProductStock(Long productId, Integer changeInStock) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        int newStock = product.getStock() + changeInStock;
        product.setStock(newStock);
        return productRepository.save(product);
    }

    @CacheEvict(value = "products", key = "#id")
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @CacheEvict(value = "products", allEntries = true)
    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }
}
