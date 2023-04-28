package com.tms.webshop.model;

import java.time.LocalDate;
import java.util.Map;

public class Order {
    private LocalDate date;
    private int id;
    private Map<Product, Integer> products;

    public Order(LocalDate date, Map<Product, Integer> products) {
        this.date = date;
        this.products = products;
    }

    public Order() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }
}
