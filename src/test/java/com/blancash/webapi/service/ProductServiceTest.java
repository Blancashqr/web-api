package com.blancash.webapi.service;

import com.blancash.webapi.model.Product;
import com.blancash.webapi.repo.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    ProductRepo productRepo;

    ProductService productService;

    @BeforeEach
    void setup() {
        productRepo = mock(ProductRepo.class);
        productService = new ProductService(productRepo);
    }

    @Test
    void checkGetProductById() {

        int id = 1;
        Product product = new Product();

        doReturn(product).when(productRepo).findProductById(anyInt());

        productService.getProductById(id);

        verify(productRepo).findProductById(id);

    }

    @Test
    void checkCreateNewProduct() {

        Product product = new Product();

        productService.createNewProduct(product);

        verify(productRepo).save(product);

    }

}
