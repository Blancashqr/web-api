package com.blancash.webapi.service;

import com.blancash.webapi.model.Card;
import com.blancash.webapi.model.Cart;
import com.blancash.webapi.model.User;
import com.blancash.webapi.model.Wishlist;
import com.blancash.webapi.repo.CardRepo;
import com.blancash.webapi.repo.UserRepo;
import com.blancash.webapi.service.exception.CardNotValidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class CardServiceTest {

    @Mock
    CardRepo cardRepo;

    @Mock
    UserRepo userRepo;

    CardService cardService;

    @BeforeEach
    void setup() {
        this.cardRepo = mock(CardRepo.class);
        this.userRepo = mock(UserRepo.class);
        cardService = new CardService(cardRepo, userRepo);
    }


    @Test
    void checkGetCardById() {

        int id = 1;
        Card card = new Card();

        doReturn(card).when(cardRepo).findCardById(anyInt());

        cardService.getCardById(id);

        verify(cardRepo).findCardById(id);

    }

    @Test
    void checkAddNewCard() throws CardNotValidException {

        int userId = 1;
        User user = new User(userId, "blanca", "blanca@gmail.com", new Cart(), new ArrayList<>(),
                new Card(), new Wishlist());
        Card card = new Card("blanca", "4539148803436467", 123, "122027", user);

        doReturn(user).when(userRepo).findUserById(anyInt());

        cardService.addNewCard(card, userId);

        verify(cardRepo).save(card);

        verify(userRepo).save(user);

        assertEquals("4539148803436467", user.getCard().getNumber());

    }

    @Test
    void checkAddNewCardWhenNumberNotValid() throws CardNotValidException {

        int userId = 1;
        User user = new User(userId, "blanca", "blanca@gmail.com", new Cart(), new ArrayList<>(),
                new Card(), new Wishlist());
        Card card = new Card("blanca", "1111111111111111", 123, "122027", user);

        doReturn(user).when(userRepo).findUserById(anyInt());

        CardNotValidException thrown = Assertions.assertThrows(CardNotValidException.class,
                () -> cardService.addNewCard(card, userId));

        assertEquals("Card expired or not valid", thrown.getMessage());

    }

    @Test
    void checkAddNewCardWhenExpired() throws CardNotValidException {

        int userId = 1;
        User user = new User(userId, "blanca", "blanca@gmail.com", new Cart(), new ArrayList<>(),
                new Card(), new Wishlist());
        Card card = new Card("blanca", "4539148803436467", 123, "122022", user);

        doReturn(user).when(userRepo).findUserById(anyInt());

        CardNotValidException thrown = Assertions.assertThrows(CardNotValidException.class,
                () -> cardService.addNewCard(card, userId));

        assertEquals("Card expired or not valid", thrown.getMessage());

    }

    @Test
    void checkAddNewCardWhenCardNameIsDifferent() throws CardNotValidException {

        int userId = 1;
        User user = new User(userId, "blanca", "blanca@gmail.com", new Cart(), new ArrayList<>(),
                new Card(), new Wishlist());
        Card card = new Card("armando", "4539148803436467", 123, "122028", user);

        doReturn(user).when(userRepo).findUserById(anyInt());

        CardNotValidException thrown = Assertions.assertThrows(CardNotValidException.class,
                () -> cardService.addNewCard(card, userId));

        assertEquals("Card expired or not valid", thrown.getMessage());

    }

}
