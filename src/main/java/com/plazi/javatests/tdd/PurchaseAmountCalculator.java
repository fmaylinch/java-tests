package com.plazi.javatests.tdd;

public class PurchaseAmountCalculator {

    private double amount;

    public double getTotal() {
        return amount;
    }

    public void addAmount(double amount) {
        this.amount = amount;
    }
}
