package com.blancash.webapi.controller;

import com.blancash.webapi.model.Card;
import com.blancash.webapi.service.CardService;
import com.blancash.webapi.service.exception.CardNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/card/{cardId}")
    public ResponseEntity<Card> getCardById(@PathVariable int cardId) {
        return ResponseEntity.ok(cardService.getCardById(cardId));
    }

    @PostMapping("/card/{userId}")
    public ResponseEntity<String> addNewCard(@RequestBody Card card, @PathVariable int userId) {
        try {
            cardService.addNewCard(card, userId);
            return ResponseEntity.ok("New card added");
        } catch (CardNotValidException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
