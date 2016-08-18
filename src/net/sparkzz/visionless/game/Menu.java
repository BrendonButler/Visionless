package net.sparkzz.visionless.game;

import net.sparkzz.modest.io.console.Alignment;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Validate;
import net.sparkzz.visionless.entity.Player;

/**
 * @author Brendon Butler
 */
public class Menu {

	private static String lastMenu;

	public static void gameMenu() {
		lastMenu = "game";

		Player player = Game.getPlayer();
		Console.clear();
		Console.fillLine('=');
		Console.align(Alignment.CENTER, String.format("Visionless - %s", player.getName()));
		Console.fillLine('=');
		Console.outln("1) Battle");
		Console.outln("2) Stats");
		Console.outln("3) Main Menu");

		int responseID = 0;
		String response = Console.prompt();

		if (Validate.isNumber(response))
			responseID = Integer.parseInt(response);
		else gameMenu();


		switch (responseID) {
			case 1:
				// Start battle
				break;
			case 2:
				statsMenu();
				break;
			default:
				mainMenu();
				break;
		}
	}

	public static void loadMenu() {
		Console.clear();
		Console.fillLine('=');
		Console.align(Alignment.CENTER, "Visionless");
		Console.fillLine('-');
		Console.outln("What would you like to do?");
		Console.fillLine('=');
		Console.outln("1) New Game");
		Console.outln("2) Load Game");
		Console.outln("3) Main Menu");

		int responseID = 0;
		String response = Console.prompt();

		if (Validate.isNumber(response))
			responseID = Integer.parseInt(response);
		else loadMenu();

		switch (responseID) {
			case 1:
				Game.play(true);
				break;
			case 2:
				Game.play(false);
				break;
			default:
				mainMenu();
				break;
		}
	}

	public static void mainMenu() {
		lastMenu = "main";

		Console.clear();
		Console.setMaxChars(48);
		Console.fillLine('=');
		Console.align(Alignment.CENTER, "Visionless");
		Console.fillLine('-');
		Console.outln("What would you like to do?");
		Console.fillLine('=');
		Console.outln("1) Play");
		Console.outln("2) Quit");

		int responseID = 0;
		String response = Console.prompt();

		if (Validate.isNumber(response))
			responseID = Integer.parseInt(response);
		else mainMenu();

		switch (responseID) {
			case 1:
				loadMenu();
				break;
			default:
				Game.save();
				Console.quit();
				break;
		}
	}

	public static void statsMenu() {
		Player player = Game.getPlayer();

		Console.clear();
		Console.fillLine('=');
		Console.align(Alignment.CENTER, String.format("Visionless - %s", player.getName()));
		Console.fillLine('-');
		Console.outf("HP: %s/%s%n", (int) player.getHealth(), (int) player.getMaxHealth());
		Console.outf("STR: %s%n", (int) player.getStrength());
		Console.outf("MGK: %s%n", (int) player.getMagic());
		Console.outf("SPD: %s%n", (int) player.getSpeed());
		Console.fillLine('=');
		Console.outln("1) Back");

		int responseID = 0;
		String response = Console.prompt();

		if (Validate.isNumber(response))
			responseID = Integer.parseInt(response);
		else gameMenu();


		switch (responseID) {
			case 1:
				if (lastMenu.equalsIgnoreCase("game")) gameMenu();
				if (lastMenu.equalsIgnoreCase("main")) mainMenu();
				break;
			default:
				mainMenu();
				break;
		}
	}
}