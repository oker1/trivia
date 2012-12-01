package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;

public class GameRunner {
    public static void main(String[] args) {
        Game.run(Long.parseLong(args[0]));
    }
}
