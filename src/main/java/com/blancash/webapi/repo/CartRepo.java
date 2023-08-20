package com.blancash.webapi.repo;

import com.blancash.webapi.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends CrudRepository<Cart, Long> {

    Cart findCartById(int id);

}
