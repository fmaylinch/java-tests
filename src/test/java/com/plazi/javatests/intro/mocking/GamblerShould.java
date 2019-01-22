package com.plazi.javatests.intro.mocking;

import com.plazi.javatests.intro.mocking.gambler.Dice;
import com.plazi.javatests.intro.mocking.gambler.Gambler;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class GamblerShould {

    private Dice dice;
    private Gambler gambler;

    @Before
    public void init() {
        dice = mock(Dice.class);
        gambler = new Gambler(4, dice);
    }

    @Test
    public void win_when_dice_number_is_greater_than_min_to_win() {

        given(dice.roll()).willReturn(5); // given

        boolean gamblerWins = gambler.play(); // when

        assertThat(gamblerWins, is(true)); // then
    }

    @Test
    public void win_when_dice_number_is_equal_to_min_to_win() {

        given(dice.roll()).willReturn(4);
        assertThat(gambler.play(), is(true));
    }

    @Test
    public void loose_when_dice_number_is_less_than_min_to_win() {

        given(dice.roll()).willReturn(3);
        assertThat(gambler.play(), is(false));
    }

    @Test
    public void use_the_dice() {

        given(dice.roll()).willReturn(5); // given

        gambler.play(); // when

        then(dice).should(times(1)).roll(); // then
    }
}