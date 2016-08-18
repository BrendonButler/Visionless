package net.sparkzz.visionless.game;

import net.sparkzz.modest.io.config.Config;
import net.sparkzz.modest.io.config.JSONConfig;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.visionless.entity.Enemy;
import net.sparkzz.visionless.entity.Player;
import net.sparkzz.visionless.entity.enemies.Zombie;

import java.io.File;
import java.util.List;

/**
 * @author Brendon Butler
 */
public class Game {

	public static Config config;
	public static Player player;

	public static Player getPlayer() {
		return player;
	}

	public static void loadGame() {
		setup();

		String name = player.getName();

		player.setHealth(config.getInteger(name + ".Health"));
		player.setMaxHealth(config.getInteger(name + ".Max_Health"));
		player.setStrength(config.getInteger(name + ".Strength"));
		player.setMagic(config.getInteger(name + ".Magic"));
		player.setSpeed(config.getInteger(name + ".Speed"));
		player.setAccuracy(config.getInteger(name + ".Accuracy"));
		player.setEvasiveness(config.getInteger(name + ".Evasiveness"));

		for (String attack : (List<String>) config.getList(name + ".Attacks"))
			player.addAttack(Attacks.get(attack));

		Menu.gameMenu();
	}

	public static void newGame() {
		setup();

		player.setHealth(20);
		player.setMaxHealth(20);
		player.setStrength(6);
		player.setMagic(2);
		player.setSpeed(6);
		player.setAccuracy(80);
		player.setEvasiveness(20);
		player.addAttack(Attacks.get("punch"));

		Enemy zombie = new Zombie();

		zombie.setHealth(15);
		zombie.setMaxHealth(15);
		zombie.setStrength(6);
		zombie.setSpeed(4);
		zombie.setAccuracy(60);
		zombie.setEvasiveness(5);
		zombie.addAttack(Attacks.get("punch"));

		Battle battle = new Battle();

		battle.startBattle(player, zombie);

		player.setHealth(player.getMaxHealth());

		Menu.gameMenu();
	}

	public static void play(boolean newGame) {
		if (newGame) newGame();
		else loadGame();
	}

	public static void save() {
		config.set(String.format("%s.Attacks", player.getName()), player.getAttacks());
		config.save();
	}

	private static void createAttacks() {
		Attacks.add(new Attack("punch", 60, 95));
	}

	private static void setup() {
		Console.clear();

		String username = Console.prompt("What is your name?%n> ");
		config = new JSONConfig(new File(System.getProperty("user.dir") + "/saves"), username.trim());
		player = new Player(username);

		createAttacks();
	}
}