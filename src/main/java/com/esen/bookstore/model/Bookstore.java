package com.esen.bookstore.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Bookstore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String location;
    private Double priceModifier;
    private Double moneyInCahRegister;

}
