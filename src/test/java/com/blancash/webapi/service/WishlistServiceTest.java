package com.blancash.webapi.service;

import com.blancash.webapi.model.Product;
import com.blancash.webapi.model.User;
import com.blancash.webapi.model.Wishlist;
import com.blancash.webapi.repo.ProductRepo;
import com.blancash.webapi.repo.WishlistRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class WishlistServiceTest {

    @Mock
    WishlistRepo wishlistRepo;

    @Mock
    ProductRepo productRepo;

    WishlistService wishlistService;

    @BeforeEach
    void setup() {
        wishlistRepo = mock(WishlistRepo.class);
        productRepo = mock(ProductRepo.class);
        wishlistService = new WishlistService(wishlistRepo, productRepo);
    }

    @Test
    void checkGetWishlistById() {

        int id = 1;
        Wishlist wishlist = new Wishlist();

        doReturn(wishlist).when(wishlistRepo).findWishlistById(anyInt());

        wishlistService.getWishListById(id);

        verify(wishlistRepo).findWishlistById(id);

    }

    @Test
    void checkAddProductToWishlist() {

        int productId = 1;
        int wishlistId = 2;
        Product product = new Product();
        Wishlist wishlist = new Wishlist(wishlistId, new User(), new HashSet<>());

        doReturn(product).when(productRepo).findProductById(anyInt());
        doReturn(wishlist).when(wishlistRepo).findWishlistById(anyInt());

        Wishlist result = wishlistService.addProduct(productId, wishlistId);

        assertEquals(1, result.getProducts().size());
        assertTrue(result.getProducts().contains(product));

        verify(productRepo).findProductById(productId);
        verify(wishlistRepo).findWishlistById(wishlistId);

    }
}
