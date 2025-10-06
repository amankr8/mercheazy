package com.mercheazy.order_service.feign;

import com.mercheazy.order_service.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductInterface {

    @GetMapping("/api/products/{id}")
    ResponseEntity<Product> getProductById(@PathVariable Long id);
}
