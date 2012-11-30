package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.LinkedList;

public class Questions {
    private HashMap<String, LinkedList<String>> questions = new HashMap<String, LinkedList<String>>();

    public Questions() {

        questions.put("Pop", new LinkedList<String>());
        questions.put("Science", new LinkedList<String>());
        questions.put("Sports", new LinkedList<String>());
        questions.put("Rock", new LinkedList<String>());

        for (String category : questions.keySet()) {
            for (int i = 0; i < 50; i++) {
                questions.get(category).addLast(category + " Question " + i);
            }
        }
    }

    public String getQuestion(String category) {
        return questions.get(category).removeFirst();
    }
}