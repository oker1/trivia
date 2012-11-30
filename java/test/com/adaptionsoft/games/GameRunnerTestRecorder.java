package com.adaptionsoft.games;

import com.adaptionsoft.games.trivia.runner.GameRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * @author oker <zsolt@takacs.cc>
 */
public class GameRunnerTestRecorder {
    public static void main(String[] args) throws FileNotFoundException {
        for (long i = 0; i < 1000; i++) {
            File dir = new File(dirName(i));
            dir.mkdir();
            File file = getFileForSeed(i);
            PrintStream printStream = new PrintStream(file);

            System.setOut(printStream);

            GameRunner.main(new String[]{String.valueOf(i)});

            printStream.close();
        }
    }

    public static File getFileForSeed(long i) {
        return new File(dirName(i) + "/" + i + ".out");
    }

    private static String dirName(long i) {
        return "test/recording/" + (i / 100);
    }
}
