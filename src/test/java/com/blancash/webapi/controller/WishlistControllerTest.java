package com.blancash.webapi.controller;

import com.blancash.webapi.model.Wishlist;
import com.blancash.webapi.service.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class WishlistControllerTest {

    @Mock
    WishlistService wishlistService;

    WishlistController wishlistController;

    @BeforeEach
    void setup() {
        wishlistService = mock(WishlistService.class);
        wishlistController = new WishlistController(wishlistService);
    }

    @Test
    void checkGetWishlistById() {

        int id = 1;
        Wishlist wishlist = new Wishlist();

        doReturn(wishlist).when(wishlistService).getWishListById(anyInt());

        wishlistController.getWishlistById(id);

        verify(wishlistService).getWishListById(id);

    }

    @Test
    void checkAddProductToWishlist() {

        int productId = 1;
        int wishlistId = 2;
        Wishlist wishlist = new Wishlist();

        doReturn(wishlist).when(wishlistService).addProduct(anyInt(), anyInt());

        wishlistController.addNewProductToWishlist(productId, wishlistId);

        verify(wishlistService).addProduct(productId, wishlistId);

    }

}
