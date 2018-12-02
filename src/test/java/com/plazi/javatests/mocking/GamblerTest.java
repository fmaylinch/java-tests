package com.plazi.javatests.mocking;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GamblerTest {

    private Dice dice;
    private Gambler gambler;

    @Before
    public void init() {
        dice = mock(Dice.class);
        gambler = new Gambler(4, dice);
    }

    @Test
    public void win() {

        when(dice.roll()).thenReturn(5);
        assertThat(gambler.play(), is(true));
    }

    @Test
    public void winWithMinimum() {

        when(dice.roll()).thenReturn(4);
        assertThat(gambler.play(), is(true));
    }

    @Test
    public void loose() {

        when(dice.roll()).thenReturn(3);
        assertThat(gambler.play(), is(false));
    }

    @Test
    public void diceIsUsed() {

        when(dice.roll()).thenReturn(5);
        gambler.play();
        verify(dice, times(1)).roll();
    }
}