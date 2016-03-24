package net.sparkzz.visionless;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Brendon Butler on 3/23/2016.
 */
public class Game {

    private BufferedReader reader;

    public static final String TITLE = "Visionless";

    public Game() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String prompt() throws IOException {
        System.out.printf("> ");
        return reader.readLine();
    }

    public void post(String message) {
        System.out.printf("%s%n", message);
    }
}
