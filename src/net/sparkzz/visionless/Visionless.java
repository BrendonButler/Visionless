package net.sparkzz.visionless;

import java.io.IOException;

/**
 * Created by Brendon Butler on 3/23/2016.
 */
public class Visionless {

    private static boolean running = false;
    private static Game game;
    private static String input;

    public static void main(String[] args) {
        game = new Game();

        running = true;

        try {
            run();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // game loop
    private static void run() throws IOException {
        while (running) {
            game.post("What will you do?");
            input = game.prompt();
            game.post(input);

            if (input.equalsIgnoreCase("quit")) {
                game.post("Thanks for playing!");
                running = false;
            }
        }
    }
}