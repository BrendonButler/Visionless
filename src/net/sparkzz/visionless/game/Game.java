package net.sparkzz.visionless.game;

import net.sparkzz.modest.io.config.Config;
import net.sparkzz.modest.io.config.JSONConfig;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.visionless.entity.BasicEntity;
import net.sparkzz.visionless.entity.Player;

import java.io.File;
import java.util.ArrayList;
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

		BasicEntity zombie = new BasicEntity("Zombie", 15, 15, 6, 4, 60, 5, new ArrayList<String>() {{
			add("punch");
		}});

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
		config.set("Player.Attacks", player.getAttacks());
		config.save();
	}

	private static void createAttacks() {
		Attacks.add(new Attack("punch", 60, 95));
	}

	private static void createEnemies() {

	}

	private static void setup() {
		Console.clear();

		String username = Console.prompt("What is your name?%n> ");
		config = new JSONConfig(new File(System.getProperty("user.dir") + "/saves"), username.trim());

		createAttacks();

		if (!config.isEmpty())
			player = new Player(username,
					config.getInteger("Player.Health"),
					config.getInteger("Player.Max_Health"),
					config.getInteger("Player.Strength"),
					config.getInteger("Player.Magic"),
					config.getInteger("Player.Speed"),
					config.getInteger("Player.Accuracy"),
					config.getInteger("Player.Evasiveness"),
					new ArrayList<String>() {{
						for (String attack : (List<String>) config.getList("Player.Attacks")) {
							add(attack);
						}
					}});
		else player = new Player(username);
	}
}