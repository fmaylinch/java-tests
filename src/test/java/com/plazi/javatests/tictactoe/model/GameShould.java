package com.plazi.javatests.tictactoe.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class GameShould {

    @Test
    public void switch_player_after_each_move() {

        final Game game = new Game();
        assertThat(game.getNextPlayer(), is(Tile.X));
        game.placeTile(0, 0);
        assertThat(game.getNextPlayer(), is(Tile.O));
        game.placeTile(1, 1);
        assertThat(game.getNextPlayer(), is(Tile.X));
        game.placeTile(2, 2);
        assertThat(game.getNextPlayer(), is(Tile.O));
    }

    @Test
    public void start_with_empty_board() {

        final Game game = new Game();

        for (int x = 0; x < Game.BOARD_SIZE; x++) {
            for (int y = 0; y < Game.BOARD_SIZE; y++) {
                assertThat(game.tileAt(x, y), is(nullValue()));
            }
        }
    }

    @Test
    public void place_tile() {

        final Game game = new Game();
        game.placeTile(1, 2);
        assertThat(game.tileAt(1, 2), is(Tile.X));
    }

    @Test
    public void place_tiles() {

        final Game game = new Game();
        game.placeTile(1, 1);
        game.placeTile(0, 0);
        game.placeTile(0, 1);
        assertThat(game.tileAt(1, 1), is(Tile.X));
        assertThat(game.tileAt(0, 0), is(Tile.O));
        assertThat(game.tileAt(0, 1), is(Tile.X));
    }
}