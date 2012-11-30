package com.adaptionsoft.games;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author oker <zsolt@takacs.cc>
 */
@RunWith(Parameterized.class)
public class GameRunnerTest {
    private long seed;
    private String expectedOutput;

    public GameRunnerTest(long seed, String expectedOutput) {
        this.seed = seed;
        this.expectedOutput = expectedOutput;
    }

    @Parameterized.Parameters
    public static Collection recordingProvider() throws IOException {
        ArrayList<Object[]> data = new ArrayList<Object[]>();

        for (long i = 0; i < 1000; i++) {
            String output = FileUtils.readFileToString(GameRunnerTestRecorder.getFileForSeed(i));
            Object[] row = new Object[2];
            row[0] = i;
            row[1] = output;

            data.add(row);
        }

        return data;
    }

    @Test
    public void testMain() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        GameRunner.main(new String[]{String.valueOf(seed)});

        assertEquals(expectedOutput, byteArrayOutputStream.toString());
    }
}
