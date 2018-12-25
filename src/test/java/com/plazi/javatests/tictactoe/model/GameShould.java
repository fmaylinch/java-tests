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
        assertThat(board(game), is("" +
                "···" +
                "···" +
                "···"));
    }

    @Test
    public void place_tile() {

        final Game game = new Game();
        game.placeTile(1, 2);
        assertThat(board(game), is("" +
                "···" +
                "···" +
                "·X·"));
    }

    @Test
    public void place_tiles() {

        final Game game = new Game();
        game.placeTile(1, 1);
        game.placeTile(0, 0);
        game.placeTile(0, 1);
        game.placeTile(2, 1);
        assertThat(board(game), is("" +
                "O··" +
                "XXO" +
                "···"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void not_allow_placing_2_tiles_at_same_position() {

        final Game game = new Game();
        game.placeTile(1, 1);
        game.placeTile(1, 1);
    }

    private String board(Game game) {

        final StringBuilder result = new StringBuilder();

        for (int y = 0; y < Game.BOARD_SIZE; y++) {
            for (int x = 0; x < Game.BOARD_SIZE; x++) {
                final Tile tile = game.tileAt(x, y);
                result.append(tile != null ? tile : "·");
            }
        }

        return result.toString();
    }
}