package com.plazi.javatests.tictactoe.model;

public class Game {

    public static final int BOARD_SIZE = 3;

    private Tile nextPlayer;
    private Tile[][] board;

    public Game() {
        this.nextPlayer = Tile.X;
        this.board = new Tile[BOARD_SIZE][BOARD_SIZE];
    }

    public Tile getNextPlayer() {
        return nextPlayer;
    }

    public void placeTile(int x, int y) {
        board[x][y] = nextPlayer;
        nextPlayer = Tile.values()[ 1 - nextPlayer.ordinal() ];
    }

    public Tile tileAt(int x, int y) {
        return board[x][y];
    }
}
