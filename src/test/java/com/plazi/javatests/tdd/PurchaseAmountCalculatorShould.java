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
}