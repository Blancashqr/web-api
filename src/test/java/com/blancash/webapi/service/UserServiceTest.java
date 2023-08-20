package com.blancash.webapi.service;

import com.blancash.webapi.model.Card;
import com.blancash.webapi.model.Cart;
import com.blancash.webapi.model.User;
import com.blancash.webapi.model.Wishlist;
import com.blancash.webapi.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    UserRepo userRepo;

    UserService userService;

    @BeforeEach
    void setup() {
        userRepo = mock(UserRepo.class);
        userService = new UserService(userRepo);
    }


    @Test
    void checkGetUserById() {

        int id = 1;
        User user = new User();

        doReturn(user).when(userRepo).findUserById(anyInt());

        userService.getUserById(id);

        verify(userRepo).findUserById(id);

    }

    @Test
    void checkGetAllUsers() {

        User user = new User();
        List<User> userList = List.of(user);

        doReturn(userList).when(userRepo).findAll();

        userService.getAllUsers();

        verify(userRepo).findAll();

    }

    @Test
    void checkAddNewUser() {

        User user = new User();

        userService.addNewUser(user);

        verify(userRepo).save(user);

    }

    @Test
    void deleteUser() {

        int id = 1;
        User user = new User();

        doReturn(user).when(userRepo).findUserById(anyInt());

        userService.deleteUser(id);

        verify(userRepo).delete(user);

    }

    @Test
    void updateEmail() {

        int id = 1;
        String email = "blanca@gmail.com";
        User user = new User(id, "blanca", "blancasaez@gmail.com", new Cart(), new ArrayList<>(),
                new Card(), new Wishlist());

        doReturn(user).when(userRepo).findUserById(anyInt());

        User result = userService.updateEmail(id, email);

        assertEquals("blanca@gmail.com", result.getEmail());

        verify(userRepo).findUserById(id);

    }

}
