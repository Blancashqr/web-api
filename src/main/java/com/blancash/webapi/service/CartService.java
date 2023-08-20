package com.blancash.webapi.service;

import com.blancash.webapi.model.Cart;
import com.blancash.webapi.model.Product;
import com.blancash.webapi.repo.CartRepo;
import com.blancash.webapi.repo.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CartService {

    private final CartRepo cartRepo;
    private final ProductRepo productRepo;

    @Autowired
    public CartService(CartRepo cartRepo, ProductRepo productRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    public Cart getCartById(int id) {
        return cartRepo.findCartById(id);
    }

    public List<Product> addNewProductsToCart(int cartId, List<Integer> productIdList) {
        log.info("Adding product with id={} to cart with id={}.", productIdList, cartId);

        Cart cart = cartRepo.findCartById(cartId);
        List<Product> productList = productRepo.findProductByIdIn(productIdList);

        List<Product> cartProducts = cart.getProducts();

        cartProducts.addAll(productList);

        cartRepo.save(cart);

        log.info("Successfully added product with id={} to cart with id={}.", productIdList, cartId);

        return cart.getProducts();

    }

}
