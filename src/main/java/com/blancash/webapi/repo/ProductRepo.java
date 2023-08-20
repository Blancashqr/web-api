package com.blancash.webapi.repo;

import com.blancash.webapi.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

    Product findProductById(int id);

    List<Product> findProductByIdIn(List<Integer> productIdList);

}
