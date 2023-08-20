package com.blancash.webapi.service;

import com.blancash.webapi.model.Cart;
import com.blancash.webapi.model.Product;
import com.blancash.webapi.model.User;
import com.blancash.webapi.repo.CartRepo;
import com.blancash.webapi.repo.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    @Mock
    CartRepo cartRepo;

    @Mock
    ProductRepo productRepo;

    CartService cartService;

    @BeforeEach
    void setup() {
        cartRepo = mock(CartRepo.class);
        productRepo = mock(ProductRepo.class);
        cartService = new CartService(cartRepo, productRepo);
    }

    @Test
    void checkGetCartById() {

        int id = 1;
        Cart cart = new Cart();

        doReturn(cart).when(cartRepo).findCartById(anyInt());

        cartService.getCartById(id);

        verify(cartRepo).findCartById(id);

    }

    @Test
    void checkAddNewProductsToCart() {

        int cartId = 1;
        List<Integer> productIdList = new ArrayList<>();
        productIdList.add(1);
        productIdList.add(2);

        Product product1 = new Product(productIdList.get(0), "book", 12.5, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        Product product2 = new Product(productIdList.get(1), "toy", 13.5, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        List<Product> productList = List.of(product1, product2);
        Cart cart2 = new Cart(cartId, new User(), new ArrayList<>());


        doReturn(cart2).when(cartRepo).findCartById(anyInt());
        doReturn(productList).when(productRepo).findProductByIdIn(anyList());

        List<Product> result = cartService.addNewProductsToCart(cartId, productIdList);

        assertEquals(2, result.size());

        verify(cartRepo).findCartById(cartId);
        verify(productRepo).findProductByIdIn(productIdList);
        verify(cartRepo).save(cart2);

    }

}
