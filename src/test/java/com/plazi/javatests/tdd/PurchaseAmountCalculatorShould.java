package com.plazi.javatests.tdd;

import org.junit.Test;

import static org.junit.Assert.*;

public class PurchaseAmountCalculatorShould {

    @Test
    public void return_zero_when_there_are_no_amounts() {

        PurchaseAmountCalculator calculator = new PurchaseAmountCalculator();

        assertEquals(0, calculator.getTotal(), 0);
    }

    @Test
    public void return_sum_of_amounts() {

        PurchaseAmountCalculator calculator = new PurchaseAmountCalculator();
        calculator.addAmount(10.8);
        calculator.addAmount(5);
        calculator.addAmount(20.7);

        assertEquals(36.5, calculator.getTotal(), 0);
    }

    @Test
    public void apply_specified_discount() {

        PurchaseAmountCalculator calculator = new PurchaseAmountCalculator();
        calculator.setDiscount(0.10);

        calculator.addAmount(20);
        calculator.addAmount(30);

        assertEquals(45, calculator.getTotal(), 0);
    }

    @Test
    public void apply_specified_discount_when_total_is_big() {

        PurchaseAmountCalculator calculator = new PurchaseAmountCalculator();
        calculator.setDiscount(0.10);
        calculator.setMinAmountForDiscount(100);

        calculator.addAmount(50);
        calculator.addAmount(50);

        assertEquals(90, calculator.getTotal(), 0);
    }

    @Test
    public void not_apply_specified_discount_when_total_is_not_big() {

        PurchaseAmountCalculator calculator = new PurchaseAmountCalculator();
        calculator.setDiscount(0.10);
        calculator.setMinAmountForDiscount(50);

        calculator.addAmount(25);
        calculator.addAmount(24);

        assertEquals(49, calculator.getTotal(), 0);
    }
}