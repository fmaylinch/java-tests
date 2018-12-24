package com.plazi.javatests.tdd;

import java.util.ArrayList;
import java.util.List;

public class PurchaseAmountCalculator {

    private List<Double> amounts;

    public PurchaseAmountCalculator() {
        this.amounts = new ArrayList<>();
    }

    public double getTotal() {

        double result = 0;

        for (Double amount : amounts) {
            result += amount;
        }

        return result;
    }

    public void addAmount(double amount) {
        this.amounts.add(amount);
    }
}
