package com.blancash.webapi.controller;

import com.blancash.webapi.model.Purchase;
import com.blancash.webapi.model.User;
import com.blancash.webapi.service.PurchaseService;
import com.blancash.webapi.service.exception.CardNotValidException;
import com.blancash.webapi.service.exception.EmptyCartException;
import com.blancash.webapi.service.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PurchaseControllerTest {

    @Mock
    PurchaseService purchaseService;

    PurchaseController purchaseController;

    @BeforeEach
    void setup() {
        purchaseService = mock(PurchaseService.class);
        purchaseController = new PurchaseController(purchaseService);
    }

    @Test
    void checkGetPurchaseById() {

        int id = 1;
        Purchase purchase = new Purchase();

        doReturn(purchase).when(purchaseService).getPurchaseById(anyInt());

        purchaseController.getPurchaseById(id);

        verify(purchaseService).getPurchaseById(id);

    }

    @Test
    void checkGetAllPurchases() {

        List<Purchase> purchaseList = new ArrayList<>();

        doReturn(purchaseList).when(purchaseService).getAllPurchases();

        purchaseController.getAllPurchases();

        verify(purchaseService).getAllPurchases();

    }

    @Test
    void checkNewPurchase() throws EmptyCartException, UserNotFoundException, CardNotValidException {

        int userId = 1;
        Purchase purchase = new Purchase(1, 10.0, new ArrayList<>(), new User());

        doReturn(purchase).when(purchaseService).newPurchase(anyInt());

        ResponseEntity<String> result = purchaseController.newPurchase(userId);

        assertEquals("Purchase with id 1 was created correctly", result.getBody());

        verify(purchaseService).newPurchase(userId);

    }

    @Test
    void checkNewPurchaseWhenUserNotFound() throws UserNotFoundException, EmptyCartException, CardNotValidException {

        int userId = 1;

        doThrow(new UserNotFoundException("User not found")).when(purchaseService)
                .newPurchase(anyInt());

        ResponseEntity<String> result = purchaseController.newPurchase(userId);

        assertEquals("User not found", result.getBody());

        verify(purchaseService).newPurchase(userId);

    }

    @Test
    void checkNewPurchaseWhenCardExpired() throws EmptyCartException, UserNotFoundException, CardNotValidException {

        int userId = 1;

        doThrow(new CardNotValidException("Card expired")).when(purchaseService)
                .newPurchase(anyInt());

        ResponseEntity<String> result = purchaseController.newPurchase(userId);

        assertEquals("Card expired", result.getBody());

        verify(purchaseService).newPurchase(userId);

    }

    @Test
    void checkNewPurchaseWhenCartIsEmpty() throws EmptyCartException, UserNotFoundException, CardNotValidException {

        int userId = 1;

        doThrow(new EmptyCartException("No products found in cart")).when(purchaseService)
                .newPurchase(anyInt());

        ResponseEntity<String> result = purchaseController.newPurchase(userId);

        assertEquals("No products found in cart", result.getBody());

        verify(purchaseService).newPurchase(userId);

    }

}
