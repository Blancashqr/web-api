package com.blancash.webapi.repo;

import com.blancash.webapi.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepo extends CrudRepository<Card, Long> {

    Card findCardById(int id);

}
