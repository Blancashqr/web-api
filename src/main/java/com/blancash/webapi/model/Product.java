package com.blancash.webapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double price;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<Cart> carts;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<Purchase> purchases;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<Wishlist> wishlists;

}
