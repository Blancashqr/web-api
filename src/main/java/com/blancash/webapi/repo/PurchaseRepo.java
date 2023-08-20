package com.blancash.webapi.repo;

import com.blancash.webapi.model.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepo extends CrudRepository<Purchase, Long> {

    Purchase findPurchaseById(int id);

    List<Purchase> findAll();

}
