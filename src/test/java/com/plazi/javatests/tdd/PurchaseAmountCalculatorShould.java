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
    public void return_amount_when_there_is_one_amount() {

        PurchaseAmountCalculator calculator = new PurchaseAmountCalculator();
        calculator.addAmount(9.95);

        double total = calculator.getTotal();

        assertEquals(9.95, total, 0);
    }
}