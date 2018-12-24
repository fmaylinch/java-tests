package com.plazi.javatests.tdd;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class PurchaseAmountCalculatorShould {

    private PurchaseAmountCalculator calculator;

    @Before
    public void setup() {
        calculator = new PurchaseAmountCalculator();
    }

    @Test
    public void total_zero_when_no_amounts() {

        assertThat(calculator.getTotal(), is(0.0));
    }

    @Test
    public void total_sum_of_amounts() {

        calculator.addAmounts(10.8, 5.0, 20.7);

        assertThat(calculator.getTotal(), is(36.5));
    }

    @Test
    public void apply_specified_discount() {

        calculator.setDiscount(0.10);
        calculator.addAmounts(20.0, 30.0);

        assertThat(calculator.getTotal(), is(45.0));
    }

    @Test
    public void apply_specified_discount_when_total_is_big() {

        calculator.setDiscount(0.10);
        calculator.setMinAmountForDiscount(100);
        calculator.addAmounts(50.0, 50.0);

        assertThat(calculator.getTotal(), is(90.0));
    }

    @Test
    public void not_apply_specified_discount_when_total_is_not_big() {

        calculator.setDiscount(0.10);
        calculator.setMinAmountForDiscount(50);
        calculator.addAmounts(25.0, 24.0);

        assertThat(calculator.getTotal(), is(49.0));
    }
}