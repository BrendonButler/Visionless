package net.sparkzz.visionless.game;

import net.sparkzz.modest.io.console.Console;

/**
 * @author Brendon Butler
 */
public class Game {

	public static void loadGame() {
		Console.outln("Loading Game..");
	}

	public static void newGame() {
		Console.outln("Starting a New Game..");
	}

	public static void play(boolean newGame) {
		if (newGame) newGame();
		else loadGame();
	}
}