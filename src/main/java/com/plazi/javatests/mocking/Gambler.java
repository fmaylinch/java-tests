package com.plazi.javatests.mocking;

public class Gambler {

    private final int minNumToWin;
    private final Dice dice;

    public Gambler(int minNumToWin, Dice dice) {
        this.minNumToWin = minNumToWin;
        this.dice = dice;
    }

    public boolean play() {
        int diceNumber = dice.roll();
        return diceNumber >= minNumToWin;
    }
}
