package com.blancash.webapi.controller;

import com.blancash.webapi.model.Cart;
import com.blancash.webapi.model.Product;
import com.blancash.webapi.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class CartControllerTest {

    @Mock
    CartService cartService;

    CartController cartController;

    @BeforeEach
    void setup() {
        cartService = mock(CartService.class);
        cartController = new CartController(cartService);
    }

    @Test
    void checkGetCartById() {

        int id = 1;
        Cart cart = new Cart();

        doReturn(cart).when(cartService).getCartById(anyInt());

        cartController.getCartById(id);

        verify(cartService).getCartById(id);

    }

    @Test
    void checkAddNewProductToCart() {

        int cartId = 1;
        List<Integer> productIdList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();

        doReturn(productList).when(cartService).addNewProductsToCart(anyInt(), anyList());

        cartController.addNewProductsToCart(cartId, productIdList);

        verify(cartService).addNewProductsToCart(cartId, productIdList);

    }

}
