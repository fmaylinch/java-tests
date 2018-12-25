package com.plazi.javatests.tictactoe.model;

public class Game {

    public static final int BOARD_SIZE = 3;

    private Tile nextPlayer;
    private Tile placedTile;

    public Game() {
        this.nextPlayer = Tile.X;
    }

    public Tile getNextPlayer() {
        return nextPlayer;
    }

    public void placeTile(int x, int y) {
        placedTile = nextPlayer;
        nextPlayer = Tile.values()[ 1 - nextPlayer.ordinal() ];
    }

    public Tile tileAt(int x, int y) {
        return placedTile;
    }
}
