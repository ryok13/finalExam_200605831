// Ryo Kato, 200605831

package com.georgiancollege.finalexam_200605831;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<Product> purchases = new ArrayList<>();

    public Customer() {}
    public Customer(int id, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Product> getPurchases() {
        return purchases;
    }

    // Question 4.1
    public double getTotalPurchases() {
        if (purchases == null) {
            return 0.0;
        }
        return purchases.stream()
                .mapToDouble(Product::getSalePrice)
                .sum();
    }

    // Question 4.2
    public double getTotalSaved() {
        if (purchases == null) {
            return 0.0;
        }
        return purchases.stream()
                .mapToDouble(p -> Math.max(0.0, p.getRegularPrice() - p.getSalePrice()))
                .sum();
    }

    // Question 4.3
    public boolean savedFiveOrMore() {
        return getTotalSaved() >= 5.0;
    }

    public String getTotalPurchasesFormatted() {
        return String.format("$%.2f", getTotalPurchases());
    }
}
