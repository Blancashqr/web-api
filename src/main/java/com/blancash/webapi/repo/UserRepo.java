package com.blancash.webapi.repo;

import com.blancash.webapi.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    List<User> findAll();

    User findUserById(int id);

}
