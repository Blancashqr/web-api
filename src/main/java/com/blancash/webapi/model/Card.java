package com.blancash.webapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String number;

    private int cvc;

    private String expiryDate;

    @OneToOne(mappedBy = "card")
    @JsonIgnore
    private User user;

    public Card(String name, String number, int cvc, String expiryDate, User user) {
        this.name = name;
        this.number = number;
        this.cvc = cvc;
        this.expiryDate = expiryDate;
        this.user = user;
    }
}
