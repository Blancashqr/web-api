package com.blancash.webapi.service;

import com.blancash.webapi.model.*;
import com.blancash.webapi.repo.CartRepo;
import com.blancash.webapi.repo.PurchaseRepo;
import com.blancash.webapi.repo.UserRepo;
import com.blancash.webapi.repo.WishlistRepo;
import com.blancash.webapi.service.exception.CardNotValidException;
import com.blancash.webapi.service.exception.EmptyCartException;
import com.blancash.webapi.service.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class PurchaseService {

    private final PurchaseRepo purchaseRepo;

    private final UserRepo userRepo;

    private final CardService cardService;

    private final CartRepo cartRepo;

    private final WishlistRepo wishlistRepo;

    @Autowired
    public PurchaseService(PurchaseRepo purchaseRepo, UserRepo userRepo, CardService cardService, CartRepo cartRepo,
                           WishlistRepo wishlistRepo) {
        this.purchaseRepo = purchaseRepo;
        this.userRepo = userRepo;
        this.cardService = cardService;
        this.cartRepo = cartRepo;
        this.wishlistRepo = wishlistRepo;
    }

    public Purchase getPurchaseById(int id) {
        return purchaseRepo.findPurchaseById(id);
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepo.findAll();
    }

    public Purchase newPurchase(int userId) throws CardNotValidException, EmptyCartException, UserNotFoundException {
        log.info("Creating new purchase for client with id={}...", userId);

        User user = userRepo.findUserById(userId);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        Card card = user.getCard();

        LocalDate expiryDate = cardService.getExpiryDate(card.getExpiryDate());

        if (expiryDate.isBefore(LocalDate.now())) {
            throw new CardNotValidException("Card expired");
        }

        Cart cart = user.getCart();

        List<Product> cartProducts = new ArrayList<>(cart.getProducts());

        cart.setProducts(new ArrayList<>());
        cartRepo.save(cart);

        if (cartProducts.isEmpty()) {
            throw new EmptyCartException("No products found in cart");
        }

        double totalValue = cartProducts.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        Purchase newPurchase = new Purchase(totalValue, cartProducts, user);

        purchaseRepo.save(newPurchase);

        Wishlist wishlist = user.getWishlist();
        Set<Product> wishlistProducts = new HashSet<>(wishlist.getProducts());

        cartProducts.forEach(i -> {
                    if (wishlistProducts.stream()
                            .anyMatch(product -> product.equals(i))) {
                        wishlistProducts.remove(i);
                    }
                });

        wishlist.setProducts(wishlistProducts);

        wishlistRepo.save(wishlist);

        log.info("Created new purchase for client with id={}", userId);

        return newPurchase;

    }

}
