package com.adaptionsoft.games.uglytrivia;

import java.util.Random;

public class Game {
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    boolean isGettingOutOfPenaltyBox;
    final Questions questions = new Questions();
    final Board board = new Board();
    final Players players = new Players();

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
        players.addPlayer(playerName);
        board.addPlayerToPlaces(players.howManyPlayers());
        purses[players.howManyPlayers()] = 0;
        inPenaltyBox[players.howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.howManyPlayers());
        return true;
    }

    public void roll(int roll) {
        System.out.println(players.getPlayerName(players.currentPlayer()) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[players.currentPlayer()]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(players.getPlayerName(players.currentPlayer()) + " is getting out of the penalty box");
                board.movePlayer(roll, players.currentPlayer());

                System.out.println(players.getPlayerName(players.currentPlayer())
                        + "'s new location is "
                        + board.getPlaceOfPlayer(players.currentPlayer()));

                askQuestion();
            } else {
                System.out.println(players.getPlayerName(players.currentPlayer()) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {

            board.movePlayer(roll, players.currentPlayer());

            System.out.println(players.getPlayerName(players.currentPlayer())
                    + "'s new location is "
                    + board.getPlaceOfPlayer(players.currentPlayer()));

            askQuestion();
        }
    }

    public void wasCorrectlyAnswered() {
        if (inPenaltyBox[players.currentPlayer()]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                addCoin(players.currentPlayer());
            }
        } else {
            System.out.println("Answer was corrent!!!!");
            addCoin(players.currentPlayer());
        }
    }

    public void wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.getPlayerName(players.currentPlayer()) + " was sent to the penalty box");
        inPenaltyBox[players.currentPlayer()] = true;
    }

    private void nextPlayer() {
        players.nextPlayer();
    }

    private boolean didPlayerWin() {
        return (!inPenaltyBox[players.currentPlayer()] || isGettingOutOfPenaltyBox) && purses[players.currentPlayer()] == 6;
    }

    private void askQuestion() {
        System.out.println("The category is " + board.currentCategory(players.currentPlayer()));
        System.out.println(questions.getQuestion(board.currentCategory(players.currentPlayer())));
    }

    private void addCoin(int player) {
        purses[player]++;
        System.out.println(players.getPlayerName(player)
                + " now has "
                + purses[player]
                + " Gold Coins.");
    }
}