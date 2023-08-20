package com.blancash.webapi.service;

import com.blancash.webapi.model.Product;
import com.blancash.webapi.model.Wishlist;
import com.blancash.webapi.repo.ProductRepo;
import com.blancash.webapi.repo.WishlistRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Slf4j
@Service
public class WishlistService {

    private final WishlistRepo wishlistRepo;

    private final ProductRepo productRepo;

    @Autowired
    public WishlistService(WishlistRepo wishlistRepo, ProductRepo productRepo) {
        this.wishlistRepo = wishlistRepo;
        this.productRepo = productRepo;
    }

    public Wishlist getWishListById(int id) {

        return wishlistRepo.findWishlistById(id);

    }

    public Wishlist addProduct(int productId, int wishlistId) {
        log.info("Adding product with id={} to wishlist with id={}", productId, wishlistId);

        Product product = productRepo.findProductById(productId);
        Wishlist wishlist = wishlistRepo.findWishlistById(wishlistId);

        Set<Product> products = wishlist.getProducts();

        products.add(product);

        wishlistRepo.save(wishlist);

        log.info("Product with id={} added to wishlist with id={}", productId, wishlistId);

        return wishlist;
    }
}
