package com.blancash.webapi.controller;

import com.blancash.webapi.model.Wishlist;
import com.blancash.webapi.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/wishlist/{id}")
    public Wishlist getWishlistById(@PathVariable int id) {
        return wishlistService.getWishListById(id);
    }

    @PutMapping("/wishlist/add/product/{productId}/{wishlistId}")
    public Wishlist addNewProductToWishlist(@PathVariable int productId, @PathVariable int wishlistId) {
        return wishlistService.addProduct(productId, wishlistId);
    }



}
