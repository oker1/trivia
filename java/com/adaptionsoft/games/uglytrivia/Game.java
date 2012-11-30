package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    ArrayList<String> players = new ArrayList<String>();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    final Questions questions = new Questions();

    public static void run(long seed) {
        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        Random rand = new Random(seed);

        boolean winner;
        do {

            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                aGame.wrongAnswer();
            } else {
                aGame.wasCorrectlyAnswered();
            }

            winner = aGame.didPlayerWin();
            aGame.nextPlayer();

        } while (!winner);
    }

    public boolean add(String playerName) {
        players.add(playerName);
        addPlayerToPlaces(howManyPlayers());
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    private void addPlayerToPlaces(int player) {
        places[player] = 0;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
                movePlayer(roll, currentPlayer);

                System.out.println(players.get(currentPlayer)
                        + "'s new location is "
                        + getPlaceOfPlayer(currentPlayer));
                System.out.println("The category is " + currentCategory(currentPlayer));
                askQuestion();
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {

            movePlayer(roll, currentPlayer);

            System.out.println(players.get(currentPlayer)
                    + "'s new location is "
                    + getPlaceOfPlayer(currentPlayer));
            System.out.println("The category is " + currentCategory(currentPlayer));
            askQuestion();
        }
    }

    private int getPlaceOfPlayer(int player) {
        return places[player];
    }

    public void wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                purses[currentPlayer]++;
                System.out.println(players.get(currentPlayer)
                        + " now has "
                        + purses[currentPlayer]
                        + " Gold Coins.");
            }
        } else {
            System.out.println("Answer was corrent!!!!");
            purses[currentPlayer]++;
            System.out.println(players.get(currentPlayer)
                    + " now has "
                    + purses[currentPlayer]
                    + " Gold Coins.");
        }
    }

    public void wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;
    }

    private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    private boolean didPlayerWin() {
        return (!inPenaltyBox[currentPlayer] || isGettingOutOfPenaltyBox) && purses[currentPlayer] == 6;
    }

    private void movePlayer(int roll, int player) {
        places[player] = getPlaceOfPlayer(player) + roll;
        if (getPlaceOfPlayer(player) > 11) places[player] = getPlaceOfPlayer(player) - 12;
    }

    private void askQuestion() {
        System.out.println(questions.getQuestion(currentCategory(currentPlayer)));
    }

    private String currentCategory(int player) {
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
