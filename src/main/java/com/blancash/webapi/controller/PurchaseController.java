package com.blancash.webapi.controller;

import com.blancash.webapi.model.Purchase;
import com.blancash.webapi.service.PurchaseService;
import com.blancash.webapi.service.exception.CardNotValidException;
import com.blancash.webapi.service.exception.EmptyCartException;
import com.blancash.webapi.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/purchase/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable int id) {
        return ResponseEntity.ok(purchaseService.getPurchaseById(id));
    }

    @GetMapping("/purchase/all")
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        return ResponseEntity.ok(purchaseService.getAllPurchases());
    }

    @PostMapping("/purchase/{userId}")
    public ResponseEntity<String> newPurchase(@PathVariable int userId) {
        try {
            Purchase newPurchase = purchaseService.newPurchase(userId);
            return ResponseEntity.ok(String.format("Purchase with id %d was created correctly", newPurchase.getId()));
        } catch (EmptyCartException | CardNotValidException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
