package com.blancash.webapi.controller;

import com.blancash.webapi.model.Product;
import com.blancash.webapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/new/product")
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createNewProduct(product));
    }

}
