package com.adaptionsoft.games.uglytrivia;

import java.util.Random;

public class Game {
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

            winner = aGame.players.didPlayerWin();
            aGame.players.nextPlayer();

        } while (!winner);
    }

    public void add(String playerName) {
        players.addPlayer(playerName);
        board.addPlayerToPlaces(players.howManyPlayers());
    }

    public void roll(int roll) {
        System.out.println(players.getPlayerName(players.currentPlayer()) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (players.getCurrent().inPenaltyBox()) {
            if (roll % 2 != 0) {
                players.getCurrent().getOutOfPenaltyBox();

                movePlayer(roll);

                askQuestion();
            } else {
                players.getCurrent().stayInPenaltyBox();
            }
        } else {

            movePlayer(roll);

            askQuestion();
        }
    }

    private void movePlayer(int roll) {
        board.movePlayer(roll, players.currentPlayer());

        System.out.println(players.getPlayerName(players.currentPlayer())
                + "'s new location is "
                + board.getPlaceOfPlayer(players.currentPlayer()));
    }

    public void wasCorrectlyAnswered() {
        if (players.getCurrent().inPenaltyBox()) {
            if (players.getCurrent().isGettingOutOfPenaltyBox()) {
                System.out.println("Answer was correct!!!!");
                players.getCurrent().addCoin();
            }
        } else {
            System.out.println("Answer was corrent!!!!");
            players.getCurrent().addCoin();
        }
    }

    public void wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        players.getCurrent().putInPenaltyBox();
    }

    private void askQuestion() {
        System.out.println("The category is " + board.currentCategory(players.currentPlayer()));
        System.out.println(questions.getQuestion(board.currentCategory(players.currentPlayer())));
    }
}