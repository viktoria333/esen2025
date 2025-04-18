package com.esen.bookstore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;


@Entity
public class Bookstore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String location;
    private Double priceModifier;
    private Double moneyInCahRegister;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<Book,Integer> inventory;

    public Bookstore() {
    }

    public Bookstore(Long id, String location, Double priceModifier, Double moneyInCahRegister, Map<Book, Integer> inventory) {
        this.id = id;
        this.location = location;
        this.priceModifier = priceModifier;
        this.moneyInCahRegister = moneyInCahRegister;
        this.inventory = inventory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPriceModifier() {
        return priceModifier;
    }

    public void setPriceModifier(Double priceModifier) {
        this.priceModifier = priceModifier;
    }

    public Double getMoneyInCahRegister() {
        return moneyInCahRegister;
    }

    public void setMoneyInCahRegister(Double moneyInCahRegister) {
        this.moneyInCahRegister = moneyInCahRegister;
    }

    public Map<Book, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Book, Integer> inventory) {
        this.inventory = inventory;
    }
}
