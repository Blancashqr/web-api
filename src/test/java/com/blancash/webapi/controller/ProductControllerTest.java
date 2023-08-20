package com.blancash.webapi.controller;

import com.blancash.webapi.model.Product;
import com.blancash.webapi.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    ProductService productService;

    ProductController productController;

    @BeforeEach
    void setup() {
        productService = mock(ProductService.class);
        productController = new ProductController(productService);
    }

    @Test
    void checkGetProductById() {

        int id = 1;
        Product product = new Product();

        doReturn(product).when(productService).getProductById(anyInt());

        productController.getProductById(id);

        verify(productService).getProductById(id);

    }

    @Test
    void checkCreateNewProduct() {

        Product product = new Product();

        doReturn(product).when(productService).createNewProduct(any());

        productController.createNewProduct(product);

        verify(productService).createNewProduct(product);

    }

}
