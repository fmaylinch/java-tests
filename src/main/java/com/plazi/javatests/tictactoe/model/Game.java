package com.plazi.javatests.tictactoe.model;

public class Game {

    private Tile nextPlayer;

    public Game() {
        this.nextPlayer = Tile.X;
    }

    public Tile getNextPlayer() {
        return nextPlayer;
    }

    public void placeTile(int x, int y) {
        nextPlayer = Tile.values()[ 1 - nextPlayer.ordinal() ];
    }
}
