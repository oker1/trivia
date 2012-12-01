package com.adaptionsoft.games.uglytrivia;

/**
 * @author oker <zsolt@takacs.cc>
 */
public class Player {
    private String name;
    private int coins = 0;
    private boolean inPenaltyBox = false;
    private boolean isGettingOutOfPenaltyBox = false;

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

    public boolean inPenaltyBox() {
        return inPenaltyBox;
    }

    public void putInPenaltyBox() {
        inPenaltyBox = true;
        System.out.println(name + " was sent to the penalty box");
    }

    public void stayInPenaltyBox() {
        isGettingOutOfPenaltyBox = false;
        System.out.println(name + " is not getting out of the penalty box");
    }

    public void getOutOfPenaltyBox() {
        isGettingOutOfPenaltyBox = true;
        System.out.println(name + " is getting out of the penalty box");
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }
}
