package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    ArrayList<String> players = new ArrayList<String>();
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];
    boolean isGettingOutOfPenaltyBox;
    int currentPlayer = 0;

    public Players() {
    }

    void addPlayer(String playerName) {
        players.add(playerName);
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;
    }

    public int howManyPlayers() {
        return players.size();
    }

    void nextPlayer() {
        currentPlayer++;
        if (currentPlayer() == players.size()) currentPlayer = 0;
    }

    int currentPlayer() {
        return currentPlayer;
    }

    String getPlayerName(int player) {
        return players.get(player);
    }

    private boolean hasEnoughCoins() {
        return purses[currentPlayer()] == 6;
    }

    public void addCoin(int player) {
        purses[player]++;
        System.out.println(getPlayerName(player)
                + " now has "
                + purses[player]
                + " Gold Coins.");
    }

    public boolean didPlayerWin() {
        return (!inPenaltyBox[currentPlayer()] || isGettingOutOfPenaltyBox) && hasEnoughCoins();
    }

    public boolean inPenaltyBox() {
        return inPenaltyBox[currentPlayer()];
    }

    public void putInPenaltyBox() {
        inPenaltyBox[currentPlayer()] = true;
    }

    public void stayInPenaltyBox() {
        isGettingOutOfPenaltyBox = false;
    }

    public void getOutOfPenaltyBox() {
        isGettingOutOfPenaltyBox = true;
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }
}