package com.blancash.webapi.repo;

import com.blancash.webapi.model.Wishlist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepo extends CrudRepository<Wishlist, Long> {

    Wishlist findWishlistById(int id);

}
