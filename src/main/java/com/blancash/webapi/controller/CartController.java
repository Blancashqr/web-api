package com.blancash.webapi.controller;

import com.blancash.webapi.model.Cart;
import com.blancash.webapi.model.Product;
import com.blancash.webapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable int id) {
        return ResponseEntity.ok(cartService.getCartById(id));
    }

    @PutMapping("/add/products")
    public ResponseEntity<List<Product>> addNewProductsToCart(@RequestParam int cartId, @RequestBody List<Integer> productIdList) {
        return ResponseEntity.ok(cartService.addNewProductsToCart(cartId, productIdList));
    }

}
