package com.adaptionsoft.games.uglytrivia;

public class Board {
    int[] places = new int[6];

    public Board() {
    }

    void addPlayerToPlaces(int player) {
        places[player] = 0;
    }

    int getPlaceOfPlayer(int player) {
        return places[player];
    }

    void movePlayer(int roll, int player) {
        places[player] = getPlaceOfPlayer(player) + roll;
        if (getPlaceOfPlayer(player) > 11) places[player] = getPlaceOfPlayer(player) - 12;
    }

    String currentCategory(int player) {
        if (getPlaceOfPlayer(player) == 0) return "Pop";
        if (getPlaceOfPlayer(player) == 4) return "Pop";
        if (getPlaceOfPlayer(player) == 8) return "Pop";
        if (getPlaceOfPlayer(player) == 1) return "Science";
        if (getPlaceOfPlayer(player) == 5) return "Science";
        if (getPlaceOfPlayer(player) == 9) return "Science";
        if (getPlaceOfPlayer(player) == 2) return "Sports";
        if (getPlaceOfPlayer(player) == 6) return "Sports";
        if (getPlaceOfPlayer(player) == 10) return "Sports";
        return "Rock";
    }
}