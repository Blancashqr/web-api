package com.blancash.webapi.controller;

import com.blancash.webapi.model.User;
import com.blancash.webapi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    UserService userService;

    UserController userController;

    @BeforeEach
    void setup() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    void checkGetUserById() {

        int id = 1;
        User user = new User();

        doReturn(user).when(userService).getUserById(anyInt());

        userController.getUserById(id);

        verify(userService).getUserById(id);

    }

    @Test
    void checkGetAllUsers() {

        User user = new User();
        List<User> userList = List.of(user);

        doReturn(userList).when(userService).getAllUsers();

        userController.getAllUsers();

        verify(userService).getAllUsers();

    }

    @Test
    void checkAddNewUser() {

        User user = new User();

        doReturn(user).when(userService).addNewUser(any());

        userController.addNewUser(user);

        verify(userService).addNewUser(user);

    }

    @Test
    void checkDeleteUser() {

        int id = 1;
        User user = new User();

        doReturn(user).when(userService).deleteUser(anyInt());

        userController.deleteUser(id);

        verify(userService).deleteUser(id);

    }

    @Test
    void updateEmail() {

        int id = 1;
        String email = "blanca@gmail.com";
        User user = new User();

        doReturn(user).when(userService).updateEmail(anyInt(), anyString());

        userController.updateEmail(id, email);

        verify(userService).updateEmail(id, email);

    }

}
