package com.adaptionsoft.games.uglytrivia;

/**
 * @author oker <zsolt@takacs.cc>
 */
public class Player {
    private String name;
    private int coins = 0;
    private boolean inPenaltyBox = false;
    private boolean gettingOutOfPenaltyBox = false;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean hasEnoughCoins() {
        return coins == 6;
    }

    public void addCoin() {
        coins++;
        System.out.println(name + " now has " + coins + " Gold Coins.");
    }
}
