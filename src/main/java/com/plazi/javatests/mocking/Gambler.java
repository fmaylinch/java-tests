package com.plazi.javatests.mocking;

public class Gambler {

    private final int minNumToWin;
    private final Dice dice;

    public Gambler(int minNumToWin) {
        this.minNumToWin = minNumToWin;
        this.dice = new Dice(6);
    }

    public boolean play() {
        int diceNumber = dice.roll();
        return diceNumber >= minNumToWin;
    }
}
