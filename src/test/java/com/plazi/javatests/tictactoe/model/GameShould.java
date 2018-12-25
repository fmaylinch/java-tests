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
}