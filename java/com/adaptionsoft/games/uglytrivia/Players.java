package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    ArrayList<Player> players = new ArrayList<Player>();
    private int currentPlayer;
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
        currentPlayer++;
        if (currentPlayer() == howManyPlayers()) currentPlayer = 0;

        current = players.get(currentPlayer);
    }

    int currentPlayer() {
        return currentPlayer;
    }

    String getPlayerName(int player) {
        return players.get(player).getName();
    }

    public boolean didPlayerWin() {
        Player player = players.get(currentPlayer());
        return (!player.inPenaltyBox() || player.isGettingOutOfPenaltyBox()) && player.hasEnoughCoins();
    }

    public Player getCurrent() {
        return current;
    }
}