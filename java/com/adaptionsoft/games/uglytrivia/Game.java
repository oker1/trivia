package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    ArrayList<String> players = new ArrayList<String>();
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    final Questions questions = new Questions();
    final Board board = new Board();

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
        addPlayer(playerName);
        board.addPlayerToPlaces(howManyPlayers());
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + howManyPlayers());
        return true;
    }

    private void addPlayer(String playerName) {
        players.add(playerName);
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(getPlayerName(currentPlayer()) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer()]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(getPlayerName(currentPlayer()) + " is getting out of the penalty box");
                board.movePlayer(roll, currentPlayer());

                System.out.println(getPlayerName(currentPlayer())
                        + "'s new location is "
                        + board.getPlaceOfPlayer(currentPlayer()));

                askQuestion();
            } else {
                System.out.println(getPlayerName(currentPlayer()) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {

            board.movePlayer(roll, currentPlayer());

            System.out.println(getPlayerName(currentPlayer())
                    + "'s new location is "
                    + board.getPlaceOfPlayer(currentPlayer()));

            askQuestion();
        }
    }

    public void wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer()]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                addCoin(currentPlayer());
            }
        } else {
            System.out.println("Answer was corrent!!!!");
            addCoin(currentPlayer());
        }
    }

    public void wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(getPlayerName(currentPlayer()) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer()] = true;
    }

    private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer() == players.size()) currentPlayer = 0;
    }

    private boolean didPlayerWin() {
        return (!inPenaltyBox[currentPlayer()] || isGettingOutOfPenaltyBox) && purses[currentPlayer()] == 6;
    }

    private void askQuestion() {
        System.out.println("The category is " + board.currentCategory(currentPlayer()));
        System.out.println(questions.getQuestion(board.currentCategory(currentPlayer())));
    }

    private int currentPlayer() {
        return currentPlayer;
    }

    private void addCoin(int player) {
        purses[player]++;
        System.out.println(getPlayerName(player)
                + " now has "
                + purses[player]
                + " Gold Coins.");
    }

    private String getPlayerName(int player) {
        return players.get(player);
    }
}
