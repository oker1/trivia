package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    ArrayList<Player> players = new ArrayList<Player>();
    private int currentPlayerId;
    Player current;

    public Players() {
    }

    void addPlayer(String playerName) {
        Player player = new Player(playerName);

        if (howManyPlayers() == 0) {
            current = player;
        }
        players.add(player);

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + howManyPlayers());
    }

    public int howManyPlayers() {
        return players.size();
    }

    void nextPlayer() {
        currentPlayerId++;
        if (getCurrentPlayerId() == howManyPlayers()) currentPlayerId = 0;

        current = players.get(currentPlayerId);
    }

    int getCurrentPlayerId() {
        return currentPlayerId;
    }

    String getPlayerName(int player) {
        return players.get(player).getName();
    }

    public boolean didPlayerWin() {
        return (!current.inPenaltyBox() || current.isGettingOutOfPenaltyBox()) && current.hasEnoughCoins();
    }

    public Player getCurrent() {
        return current;
    }
}