package com.plazi.javatests.tdd;

import org.junit.Test;

import static org.junit.Assert.*;

public class PurchaseAmountCalculatorShould {

    @Test
    public void return_zero_when_there_are_no_amounts() {

        PurchaseAmountCalculator calculator = new PurchaseAmountCalculator();

        double total = calculator.getTotal();

        assertEquals(0, total, 0);
    }

    @Test
    public void return_sum_of_amounts() {

        PurchaseAmountCalculator calculator = new PurchaseAmountCalculator();
        calculator.addAmount(10.8);
        calculator.addAmount(5);
        calculator.addAmount(20.7);

        double total = calculator.getTotal();

        assertEquals(36.5, total, 0);
    }
}