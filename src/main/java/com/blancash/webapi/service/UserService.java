package com.blancash.webapi.service;

import com.blancash.webapi.model.User;
import com.blancash.webapi.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUserById(int id) {
        log.info("Finding user with userId={}...", id);
        return userRepo.findUserById(id);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User addNewUser(User user) {
        userRepo.save(user);

        return user;
    }

    public User deleteUser(int id) {
        log.info("Deleting user with userId={}...", id);

        User user = userRepo.findUserById(id);

        userRepo.delete(user);

        log.info("Successfully deleted user with userId={}...", id);

        return user;

    }

    public User updateEmail(int id, String email) {
        log.info("Updating email for user with userId={}...", id);

        User user = userRepo.findUserById(id);

        user.setEmail(email);

        userRepo.save(user);

        log.info("Successfully updated email for user with userId={}...", id);

        return user;

    }

}
