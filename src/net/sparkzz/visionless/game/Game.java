package net.sparkzz.visionless.game;

import net.sparkzz.modest.io.config.Config;
import net.sparkzz.modest.io.config.JSONConfig;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Languages;
import net.sparkzz.visionless.entity.BasicEntity;
import net.sparkzz.visionless.entity.Enemies;
import net.sparkzz.visionless.entity.MagicEntity;
import net.sparkzz.visionless.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brendon Butler
 */
public class Game {

	public static boolean started = false;
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

		Battle battle = new Battle();

		battle.startBattle(player, Enemies.getEnemy(Enemies.Enemy.ZOMBIE));

		player.setHealth(player.getMaxHealth()); // TODO: player's health is not being set here

		Menu.gameMenu();
	}

	public static void play(boolean newGame) {
		started = true;

		if (newGame) newGame();
		else loadGame();
	}

	public static void save() {
		List<String> attackNames = new ArrayList<>();

		for (Attack attack : player.getAttacks())
			attackNames.add(attack.getName());

		config.set("Player.Attacks", attackNames);
		config.save();
	}

	private static void createAttacks() {
		Attacks.add(new Attack("bite", 50, 95, Attacks.AttackType.PHYSICAL));
		Attacks.add(new Attack("punch", 60, 95, Attacks.AttackType.PHYSICAL));
		Attacks.add(new Attack("sludge", 50, 80, Attacks.AttackType.MAGIC));
	}

	private static void createEnemies() {
		// Construct enemies
		BasicEntity bat = new BasicEntity("bat", 1, 12, 4, 10, 90, 30, new ArrayList<String>() {{
			add("bite");
		}});

		BasicEntity skeleton = new BasicEntity("skeleton", 1, 14, 5, 5, 70, 30, new ArrayList<String>() {{
			add("punch");
		}});

		BasicEntity zombie = new BasicEntity("zombie", 1, 15, 6, 4, 60, 5, new ArrayList<String>() {{
			add("punch");
		}});

		MagicEntity magicBat = new MagicEntity("magic_bat", 1, 10, 3, 12, 10, 60, 60, new ArrayList<String>() {{
			add("sludge");
		}});

		MagicEntity wizard = new MagicEntity("wizard", 1, 14, 4, 20, 6, 65, 70, new ArrayList<String>() {{
			add("punch");
			add("sludge");
		}});

		// Store enemies
		Enemies.addEnemy(bat);
		Enemies.addEnemy(magicBat);
		Enemies.addEnemy(skeleton);
		Enemies.addEnemy(wizard);
		Enemies.addEnemy(zombie);
	}

	private static void setup() {
		Console.clear();

		// name localization
		Map<String, String> names = new HashMap<>();

		// entities
		names.put("bat", "Bat");
		names.put("magic_bat", "Magic Bat");
		names.put("skeleton", "Skeleton");
		names.put("wizard", "Wizard");
		names.put("zombie", "Zombie");

		// attacks
		names.put("bite", "Bite");
		names.put("punch", "Punch");
		names.put("sludge", "Sludge");

		Languages.addLanguage("names", names);
		Languages.setLanguage("names");

		String username = Console.prompt("What is your name?%n> ");
		config = new JSONConfig(new File(System.getProperty("user.dir") + "/saves"), username.trim());

		createAttacks();
		createEnemies();

		if (!config.isEmpty())
			player = new Player(username,
					config.getInteger("Player.Stats.XP"), config.getInteger("Player.Health"),
					20, 6, 6, 8, 80, 20,
					new ArrayList<String>() {{
						for (String attack : (List<String>) config.getList("Player.Attacks"))
							add(attack);
					}});
		else player = new Player(username, 0, 20, 20, 6, 6, 8, 80, 20,
					new ArrayList<String>() {{
						add("punch");
					}});
	}
}