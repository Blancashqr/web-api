package com.blancash.webapi.controller;

import com.blancash.webapi.model.Card;
import com.blancash.webapi.model.Cart;
import com.blancash.webapi.model.User;
import com.blancash.webapi.model.Wishlist;
import com.blancash.webapi.service.CardService;
import com.blancash.webapi.service.exception.CardNotValidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class CardControllerTest {

    @Mock
    CardService cardService;

    CardController cardController;

    @BeforeEach
    void setup() {
        this.cardService = mock(CardService.class);
        cardController = new CardController(cardService);
    }

    @Test
    void checkGetCardById() {

        int id = 1;
        Card card = new Card();

        doReturn(card).when(cardService).getCardById(anyInt());

        cardController.getCardById(id);

        verify(cardService).getCardById(id);

    }

    @Test
    void checkAddNewCard() throws CardNotValidException {

        int userId = 1;
        User user = new User(userId, "blanca", "blanca@gmail.com", new Cart(), new ArrayList<>(),
                new Card(), new Wishlist());
        Card card = new Card("blanca", "4916844037092893", 123, "122028", user);

        cardController.addNewCard(card, userId);

        verify(cardService).addNewCard(card, userId);

    }

    @Test
    void checkAddNewCardWhenServiceThrowsException() throws CardNotValidException {

        int userId = 1;
        User user = new User(userId, "blanca", "blanca@gmail.com", new Cart(), new ArrayList<>(),
                new Card(), new Wishlist());
        Card card = new Card("blanca", "1", 123, "122027", user);

        doThrow(new CardNotValidException("Card expired or not valid")).when(cardService)
                .addNewCard(any(), anyInt());

        ResponseEntity<String> result = cardController.addNewCard(card, userId);

        assertEquals("Card expired or not valid", result.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

        verify(cardService).addNewCard(card, userId);

    }

}
