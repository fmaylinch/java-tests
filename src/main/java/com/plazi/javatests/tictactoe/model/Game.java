package com.plazi.javatests.tictactoe.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Game {

    public static final int BOARD_SIZE = 3;

    private Tile nextPlayer;
    private Tile[][] board;
    private Tile winner;

    public Game() {
        this.nextPlayer = Tile.X;
        this.board = new Tile[BOARD_SIZE][BOARD_SIZE];
        this.winner = Tile.EMPTY;
    }

    public Tile getNextPlayer() {
        return nextPlayer;
    }

    public void placeTile(int x, int y) {

        if (winner != Tile.EMPTY) {
            throw new IllegalStateException("Game has already a winner: " + winner);
        }

        if (board[x][y] != null) {
            throw new IllegalArgumentException("Position occupied: " + x + ", " + y);
        }

        board[x][y] = nextPlayer;
        calculateWinner();
        nextPlayer = Tile.values()[ 1 - nextPlayer.ordinal() ];
    }

    public Tile tileAt(int x, int y) {
        final Tile tile = board[x][y];
        return tile != null ? tile : Tile.EMPTY;
    }

    public Tile getWinner() {
        return winner;
    }

    private void calculateWinner() {

        final List<Integer> indexes = IntStream.range(0, BOARD_SIZE).boxed().collect(toList());

        final List<Stream<Tile>> combinations = new ArrayList<>();

        indexes.forEach(y -> combinations.add( indexes.stream().map(x -> tileAt(x, y)) )); // horizontals [-]
        indexes.forEach(x -> combinations.add( indexes.stream().map(y -> tileAt(x, y)) )); // verticals [|]
        combinations.add( indexes.stream().map(xy -> tileAt(xy, xy)) ); // diagonal [\]
        combinations.add( indexes.stream().map(xy -> tileAt(xy, BOARD_SIZE - 1 - xy)) ); // diagonal 2 [/]

        for (Stream<Tile> combination : combinations) {
            winner = checkWinner(combination);
            if (winner != Tile.EMPTY) {
                break; // found winner
            }
        }
    }

    private Tile checkWinner(Stream<Tile> combination) {
        return combination.reduce((a,b) -> a != Tile.EMPTY && a == b ? a : Tile.EMPTY).get();
    }
}
