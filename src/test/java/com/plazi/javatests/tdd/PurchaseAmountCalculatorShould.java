package com.plazi.javatests.tdd;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PurchaseAmountCalculatorShould {

    private PurchaseAmountCalculator calculator;

    @Before
    public void setup() {
        calculator = new PurchaseAmountCalculator();
    }

    @Test
    public void return_zero_when_there_are_no_amounts() {

        assertEquals(0, calculator.getTotal(), 0);
    }

    @Test
    public void return_sum_of_amounts() {

        calculator.addAmounts(10.8, 5.0, 20.7);

        assertEquals(36.5, calculator.getTotal(), 0);
    }

    @Test
    public void apply_specified_discount() {

        calculator.setDiscount(0.10);
        calculator.addAmounts(20.0, 30.0);

        assertEquals(45, calculator.getTotal(), 0);
    }

    @Test
    public void apply_specified_discount_when_total_is_big() {

        calculator.setDiscount(0.10);
        calculator.setMinAmountForDiscount(100);
        calculator.addAmounts(50.0, 50.0);

        assertEquals(90, calculator.getTotal(), 0);
    }

    @Test
    public void not_apply_specified_discount_when_total_is_not_big() {

        calculator.setDiscount(0.10);
        calculator.setMinAmountForDiscount(50);
        calculator.addAmounts(25.0, 24.0);

        assertEquals(49, calculator.getTotal(), 0);
    }
}