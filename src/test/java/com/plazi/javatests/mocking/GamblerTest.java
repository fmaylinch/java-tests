package com.plazi.javatests.mocking;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GamblerTest {

    @Test
    public void win() {

        Dice dice = mock(Dice.class);
        when(dice.roll()).thenReturn(5);
        Gambler gambler = new Gambler(4, dice);
        assertThat(gambler.play(), is(true));
    }

    @Test
    public void winWithMinimum() {

        Dice dice = mock(Dice.class);
        when(dice.roll()).thenReturn(4);
        Gambler gambler = new Gambler(4, dice);
        assertThat(gambler.play(), is(true));
    }

    @Test
    public void loose() {

        Dice dice = mock(Dice.class);
        when(dice.roll()).thenReturn(3);
        Gambler gambler = new Gambler(4, dice);
        assertThat(gambler.play(), is(false));
    }

    @Test
    public void diceIsUsed() {

        Dice dice = mock(Dice.class);
        when(dice.roll()).thenReturn(5);
        Gambler gambler = new Gambler(4, dice);
        gambler.play();
        verify(dice, times(1)).roll();
    }
}