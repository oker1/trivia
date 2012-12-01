package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    ArrayList<Player> players = new ArrayList<Player>();
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    boolean[] inPenaltyBox = new boolean[6];

    public Players() {
    }

    void addPlayer(String playerName) {
        players.add(new Player(playerName));

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + howManyPlayers());
    }

    public int howManyPlayers() {
        return players.size();
    }

    void nextPlayer() {
        currentPlayer++;
        if (currentPlayer() == howManyPlayers()) currentPlayer = 0;
    }

    int currentPlayer() {
        return currentPlayer;
    }

    String getPlayerName(int player) {
        return players.get(player).getName();
    }

    public void addCoin(int player) {
        players.get(player).addCoin();
    }

    public boolean didPlayerWin() {
        return (!inPenaltyBox() || isGettingOutOfPenaltyBox()) && players.get(currentPlayer()).hasEnoughCoins();
    }

    public boolean inPenaltyBox() {
        return inPenaltyBox[currentPlayer()];
    }

    public void putInPenaltyBox() {
        inPenaltyBox[currentPlayer()] = true;
        System.out.println(getPlayerName(currentPlayer()) + " was sent to the penalty box");
    }

    public void stayInPenaltyBox() {
        isGettingOutOfPenaltyBox = false;
        System.out.println(getPlayerName(currentPlayer()) + " is not getting out of the penalty box");
    }

    public void getOutOfPenaltyBox() {
        isGettingOutOfPenaltyBox = true;
        System.out.println(getPlayerName(currentPlayer()) + " is getting out of the penalty box");
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }
}