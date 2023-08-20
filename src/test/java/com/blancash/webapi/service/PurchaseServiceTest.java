package com.blancash.webapi.service;

import com.blancash.webapi.model.*;
import com.blancash.webapi.repo.CartRepo;
import com.blancash.webapi.repo.PurchaseRepo;
import com.blancash.webapi.repo.UserRepo;
import com.blancash.webapi.repo.WishlistRepo;
import com.blancash.webapi.service.exception.CardNotValidException;
import com.blancash.webapi.service.exception.EmptyCartException;
import com.blancash.webapi.service.exception.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PurchaseServiceTest {

    @Mock
    PurchaseRepo purchaseRepo;

    @Mock
    UserRepo userRepo;

    @Mock
    CardService cardService;

    @Mock
    CartRepo cartRepo;

    @Mock
    WishlistRepo wishlistRepo;

    PurchaseService purchaseService;

    @BeforeEach
    void setup() {
        purchaseRepo = mock(PurchaseRepo.class);
        userRepo = mock(UserRepo.class);
        cardService = mock(CardService.class);
        cartRepo = mock(CartRepo.class);
        wishlistRepo = mock(WishlistRepo.class);
        purchaseService = new PurchaseService(purchaseRepo, userRepo, cardService, cartRepo, wishlistRepo);
    }

    @Test
    void checkGetPurchaseById() {

        int id = 1;
        Purchase purchase = new Purchase();

        doReturn(purchase).when(purchaseRepo).findPurchaseById(anyInt());

        purchaseService.getPurchaseById(id);

        verify(purchaseRepo).findPurchaseById(id);

    }

    @Test
    void checkGetAllPurchases() {

        List<Purchase> purchaseList = new ArrayList<>();

        doReturn(purchaseList).when(purchaseRepo).findAll();

        purchaseService.getAllPurchases();

        verify(purchaseRepo).findAll();

    }

    @Test
    void checkNewPurchase() throws EmptyCartException, UserNotFoundException, CardNotValidException {

        int userId = 1;
        Product product1 = new Product(1, "apple", 2.0, new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());
        Product product2 = new Product(2, "pear", 3.0, new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());
        List<Product> productList = List.of(product1, product2);
        Cart cart = new Cart(2, new User(), productList);
        List<Purchase> purchaseList = new ArrayList<>();
        Set<Product> productSet = Set.of(product1);
        Wishlist wishlist = new Wishlist(1, new User(), productSet);
        Card card = new Card(1, "blanca", "123", 123, "12122030", new User());
        User user = new User(userId, "blanca", "blanca@gmail.com", cart, purchaseList, card, wishlist);

        doReturn(user).when(userRepo).findUserById(anyInt());
        doReturn(cart).when(cartRepo).save(any());
        doReturn(new Purchase()).when(purchaseRepo).save(any());
        doReturn(LocalDate.of(2030, 3, 1)).when(cardService).getExpiryDate(anyString());

        Purchase result = purchaseService.newPurchase(userId);

        assertEquals(user, result.getUser());
        assertEquals(5.0, result.getTotalValue());
        assertEquals(0, wishlist.getProducts().size());

        verify(userRepo).findUserById(userId);
        verify(purchaseRepo).save(result);

    }

    @Test
    void checkNewPurchaseWhenUserNotFound() {

        int userId = 1;

        doReturn(null).when(userRepo).findUserById(anyInt());

        UserNotFoundException thrown = Assertions.assertThrows(UserNotFoundException.class,
                () -> purchaseService.newPurchase(userId));

        assertEquals("User not found", thrown.getMessage());

    }

    @Test
    void checkNewPurchaseWhenCardIsExpired() {

        int userId = 1;
        Card card = new Card(1, "blanca", "123", 123, "12122030", new User());
        User user = new User(userId, "blanca", "blanca@gmail.com", new Cart(), new ArrayList<>(), card,
                new Wishlist());

        doReturn(user).when(userRepo).findUserById(anyInt());
        doReturn(LocalDate.of(2010, 3, 1)).when(cardService).getExpiryDate(anyString());

        CardNotValidException thrown = Assertions.assertThrows(CardNotValidException.class,
                () -> purchaseService.newPurchase(userId));

        assertEquals("Card expired", thrown.getMessage());

    }

    @Test
    void checkNewPurchaseWhenCartIsEmpty() {

        int userId = 1;
        Cart cart = new Cart(2, new User(), new ArrayList<>());
        Card card = new Card(1, "blanca", "123", 123, "12122030", new User());
        User user = new User(userId, "blanca", "blanca@gmail.com", cart, new ArrayList<>(), card,
                new Wishlist());

        doReturn(user).when(userRepo).findUserById(anyInt());
        doReturn(LocalDate.of(2030, 3, 1)).when(cardService).getExpiryDate(anyString());

        EmptyCartException thrown = Assertions.assertThrows(EmptyCartException.class,
                () -> purchaseService.newPurchase(userId));

        assertEquals("No products found in cart", thrown.getMessage());

    }

}
