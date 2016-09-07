package net.sparkzz.visionless.game;

import net.sparkzz.modest.io.console.Alignment;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Validate;
import net.sparkzz.visionless.entity.Player;

import static net.sparkzz.visionless.utils.MathHelper.findHPStat;
import static net.sparkzz.visionless.utils.MathHelper.findLevel;
import static net.sparkzz.visionless.utils.MathHelper.findStat;

/**
 * @author Brendon Butler
 */
public class Menu {

	private static Menus lastMenu;

	public enum Menus {
		GAME, MAIN
	}

	public static void attacksMenu() {
		Player player = Game.getPlayer();

		Console.clear();
		Console.fillLine('=');
		Console.align(Alignment.CENTER, String.format("Visionless - %s", player.getName()));
		Console.fillLine('-');
		for (Attack attack : player.getAttacks())
			Console.outf("%s: %stag_pwr | %stag_acy%n", attack.getName(), attack.getDamage(), attack.getAccuracy());
		Console.fillLine('=');
		Console.outln("1) menu_back");

		int responseID = 0;
		String response = Console.prompt();

		if (Validate.isNumber(response))
			responseID = Integer.parseInt(response);
		else attacksMenu();


		switch (responseID) {
			case 1:
				statsMenu();
				break;
			default:
				mainMenu();
				break;
		}
	}

	public static void gameMenu() {
		lastMenu = Menus.GAME;

		Player player = Game.getPlayer();
		Console.clear();
		Console.fillLine('=');
		Console.align(Alignment.CENTER, String.format("Visionless - %s", player.getName()));
		Console.fillLine('=');
		Console.outln("1) menu_battle");
		Console.outln("2) menu_stats");
		Console.outln("3) menu_main");

		int responseID = 0;
		String response = Console.prompt();

		if (Validate.isNumber(response))
			responseID = Integer.parseInt(response);
		else gameMenu();


		switch (responseID) {
			case 1:
				if (player.getHealth() == 0) {
					Console.outln("must_heal_up");
					player.setHealth(player.getMaxHealth());
					gameMenu();
				} else {
					Battle battle = new Battle();

					battle.randomBattle(player);
				}
				break;
			case 2:
				statsMenu();
				break;
			default:
				mainMenu();
				break;
		}
	}

	public static void levelUpMenu(int xp) {
		Player player = Game.getPlayer();

		Console.clear();
		Console.fillLine('=');
		Console.align(Alignment.CENTER, String.format("%s", player.getName()));
		Console.align(Alignment.CENTER, String.format("tag_lvl %s", findLevel(player.getXP() + xp)));
		Console.fillLine('-');
		Console.outln("leveled_up");
		Console.fillLine('-');
		Console.outf("tag_hp: %s + %s%n", (int) player.getMaxHealth(), (int) (findHPStat(findLevel(player.getXP() + xp), (int) player.getBaseHealth()) - player.getMaxHealth()));
		Console.outf("tag_str: %s + %s%n", (int) player.getStrength(), (int) (findStat(findLevel(player.getXP() + xp), (int) player.getBaseStrength()) - player.getStrength()));
		Console.outf("tag_mgk: %s + %s%n", (int) player.getMagic(), (int) (findStat(findLevel(player.getXP() + xp), (int) player.getBaseMagic()) - player.getMagic()));
		Console.outf("tag_spd: %s + %s%n", (int) player.getSpeed(), (int) (findStat(findLevel(player.getXP() + xp), (int) player.getBaseSpeed()) - player.getSpeed()));
		Console.fillLine('=');

		Console.prompt();

		player.setXP(player.getXP() + xp);
		player.determineStats();

		Console.clear();
		Console.fillLine('=');
		Console.align(Alignment.CENTER, String.format("%s", player.getName()));
		if (player.getLevel() < 100)
			Console.align(Alignment.CENTER, String.format("tag_lvl %s | xp_needed", player.getLevel(), player.getXPNeeded()));
		else Console.align(Alignment.CENTER, String.format("tag_lvl %s", player.getLevel()));
		Console.fillLine('-');
		Console.outf("tag_hp: %s%n", (int) player.getMaxHealth());
		Console.outf("tag_str: %s%n", (int) player.getStrength());
		Console.outf("tag_mgk: %s%n", (int) player.getMagic());
		Console.outf("tag_spd: %s%n", (int) player.getSpeed());
		Console.fillLine('=');

		Console.prompt();
	}

	public static void loadMenu() {
		Console.clear();
		Console.fillLine('=');
		Console.align(Alignment.CENTER, "Visionless");
		Console.fillLine('-');
		Console.outln("would_like_to_do");
		Console.fillLine('=');
		Console.outln("1) menu_new_game");
		Console.outln("2) menu_load_game");
		Console.outln("3) menu_main");

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
		lastMenu = Menus.MAIN;

		Console.clear();
		Console.setMaxChars(48);
		Console.fillLine('=');
		Console.align(Alignment.CENTER, "Visionless");
		Console.fillLine('-');
		Console.outln("would_like_to_do");
		Console.fillLine('=');
		Console.outln("1) menu_play");
		Console.outln("2) menu_quit");

		int responseID = 0;
		String response = Console.prompt();

		if (Validate.isNumber(response))
			responseID = Integer.parseInt(response);
		else mainMenu();

		switch (responseID) {
			case 1:
				if (Game.started) {
					gameMenu();
					break;
				}
				loadMenu();
				break;
			default:
				if (Game.started)
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
		if (player.getLevel() < 100)
			Console.align(Alignment.CENTER, String.format("tag_lvl %s | %s XP needed for next level", player.getLevel(), player.getXPNeeded()));
		else Console.align(Alignment.CENTER, String.format("tag_lvl %s", player.getLevel()));
		Console.fillLine('-');
		Console.outf("tag_xp: %s%n", player.getXP());
		Console.outf("tag_hp: %s/%s%n", (int) player.getHealth(), (int) player.getMaxHealth());
		Console.outf("tag_str: %s%n", (int) player.getStrength());
		Console.outf("tag_mgk: %s%n", (int) player.getMagic());
		Console.outf("tag_spd: %s%n", (int) player.getSpeed());
		Console.fillLine('=');
		Console.outln("1) menu_attacks");
		Console.outln("2) menu_back");

		int responseID = 0;
		String response = Console.prompt();

		if (Validate.isNumber(response))
			responseID = Integer.parseInt(response);
		else statsMenu();


		switch (responseID) {
			case 1:
				attacksMenu();
				break;
			case 2:
				if (lastMenu == Menus.GAME) gameMenu();
				if (lastMenu == Menus.MAIN) mainMenu();
				break;
			default:
				mainMenu();
				break;
		}
	}
}