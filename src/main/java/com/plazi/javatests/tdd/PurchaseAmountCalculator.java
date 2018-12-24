package com.plazi.javatests.tdd;

import java.util.ArrayList;
import java.util.List;

public class PurchaseAmountCalculator {

    private List<Double> amounts;

    public PurchaseAmountCalculator() {
        this.amounts = new ArrayList<>();
    }

    public double getTotal() {
        return amounts.stream().reduce(0.0, (a,b) -> a+b);
    }

    public void addAmount(double amount) {
        this.amounts.add(amount);
    }
}
