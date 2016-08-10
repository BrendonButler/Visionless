package net.sparkzz.visionless.game;

import net.sparkzz.modest.io.console.Alignment;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Validate;

/**
 * @author Brendon Butler
 */
public class Menu {

	public static void mainMenu() {
		Console.setMaxChars(48);
		Console.fillLine('=');
		Console.align(Alignment.CENTER, "Visionless");
		Console.fillLine('-');
		Console.outln("What would you like to do?");
		Console.outln("1) Play");
		Console.outln("2) Quit");
		Console.fillLine('=');

		int responseID = 0;
		String response = Console.prompt();

		if (Validate.isNumber(response))
			responseID = Integer.parseInt(response);

		switch (responseID) {
			case 1:
				Game.play(true);
				break;
			default:
				Console.quit();
				break;
		}
	}

	public static void gameMenu() {

	}
}