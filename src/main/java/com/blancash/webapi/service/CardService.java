package com.blancash.webapi.service;

import com.blancash.webapi.model.Card;
import com.blancash.webapi.model.User;
import com.blancash.webapi.repo.CardRepo;
import com.blancash.webapi.repo.UserRepo;
import com.blancash.webapi.service.exception.CardNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

@Slf4j
@Service
public class CardService {

    private final CardRepo cardRepo;

    private final UserRepo userRepo;

    @Autowired
    public CardService(CardRepo cardRepo, UserRepo userRepo) {
        this.cardRepo = cardRepo;
        this.userRepo = userRepo;
    }

    public Card getCardById(int cardId) {
        return cardRepo.findCardById(cardId);
    }

    public void addNewCard(Card card, int userId)
            throws CardNotValidException {
        log.info("Adding card to user with id={}.", userId);

        User user = userRepo.findUserById(userId);

        if (getExpiryDate(card.getExpiryDate()).isAfter(LocalDate.now()) && validCardNumber(
                card.getNumber(), card.getName(), user)) {
            cardRepo.save(card);
            user.setCard(card);
            userRepo.save(user);
        } else {
            throw new CardNotValidException("Card expired or not valid");
        }

        log.info("Added card with id={} to user with id={}.", card.getId(), userId);

    }

    private Boolean validCardNumber(String cardNumber, String cardName, User user) {

        String[] cardNumberArray = cardNumber.split("");

        Collections.reverse(Arrays.asList(cardNumberArray));

        int[] reversedArray = Arrays.stream(cardNumberArray)
                .mapToInt(Integer::parseInt)
                .toArray();

        boolean result = false;

        int sum = 0;

        if (user.getName().equals(cardName) && cardNumber.length() == 16) {
            for (int i = 0; i <= reversedArray.length - 1; i++) {
                if (i % 2 == 0) {
                    sum += reversedArray[i];
                } else {
                    if (reversedArray[i] * 2 > 9) {
                        sum  += ((reversedArray[i] * 2) % 10) + 1;
                    } else {
                        sum += reversedArray[i] * 2;
                    }
                }
            }

            result = sum % 10 == 0;

        }

        return result;

    }

    public LocalDate getExpiryDate(String expiryDate) {

        LocalDate result = LocalDate.now();

        if (expiryDate.length() == 6) {
            String month = expiryDate.substring(0, 2);
            int monthInt = Integer.parseInt(month);

            String year = expiryDate.substring(2, 6);
            int yearInt = Integer.parseInt(year);

            result = LocalDate.of(yearInt, monthInt, 1);
        }

        return result;

    }

}
