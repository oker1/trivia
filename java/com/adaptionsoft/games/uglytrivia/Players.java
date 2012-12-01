package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    ArrayList<String> players = new ArrayList<String>();
    int currentPlayer = 0;

    public Players() {
    }

    void addPlayer(String playerName) {
        players.add(playerName);
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
}