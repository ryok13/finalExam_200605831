package com.georgiancollege.finalexam_200605831;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private final List<Product> purchasedProducts = new ArrayList<>();

    public Customer(int id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public List<Product> getPurchasedProducts() {
        return purchasedProducts;
    }
}
