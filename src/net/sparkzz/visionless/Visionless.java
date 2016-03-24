package net.sparkzz.visionless;

import java.io.IOException;

/**
 * Created by Brendon Butler on 3/23/2016.
 */
public class Visionless {

    private static String input;

    public static void main(String[] args) {
        Game game = new Game();

        try {
            game.post("What will you do?");
            input = game.prompt();

            game.post(input);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}