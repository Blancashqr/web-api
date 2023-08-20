package com.blancash.webapi.service;

import com.blancash.webapi.model.Product;
import com.blancash.webapi.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product getProductById(int id) {
        return productRepo.findProductById(id);
    }

    public Product createNewProduct(Product product) {

        productRepo.save(product);

        return product;

    }

}
