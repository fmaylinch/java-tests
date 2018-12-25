package com.plazi.javatests.tictactoe.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class GameShould {

    @Test
    public void start_with_player_X() {

        final Game game = new Game();
        assertThat(game.getNextPlayer(), is(Tile.X));
    }

    @Test
    public void switch_to_player_O_after_first_move() {

        final Game game = new Game();
        game.placeTile(0, 0);
        assertThat(game.getNextPlayer(), is(Tile.O));
    }
}