package com.plazi.javatests.tictactoe.model;

public class Game {

    private Tile nextPlayer;

    public Game() {
        this.nextPlayer = Tile.X;
    }

    public Tile getNextPlayer() {
        return nextPlayer;
    }
}
