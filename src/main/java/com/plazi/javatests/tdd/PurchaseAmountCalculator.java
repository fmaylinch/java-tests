package com.plazi.javatests.tdd;

import java.util.ArrayList;
import java.util.List;

public class PurchaseAmountCalculator {

    private List<Double> amounts;
    private double discount;

    public PurchaseAmountCalculator() {
        this.amounts = new ArrayList<>();
    }

    public double getTotal() {
        double totalBeforeDiscount = amounts.stream().reduce(0.0, (a, b) -> a + b);
        return totalBeforeDiscount * (1 - discount);
    }

    public void addAmount(double amount) {
        this.amounts.add(amount);
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
