package net.sparkzz.visionless.game;

import net.sparkzz.modest.io.console.Console;
import net.sparkzz.visionless.entity.Enemy;
import net.sparkzz.visionless.entity.Player;
import net.sparkzz.visionless.entity.enemies.Zombie;

/**
 * @author Brendon Butler
 */
public class Game {

	public static Player player;

	public static Player getPlayer() {
		return player;
	}

	public static void loadGame() {
		Console.outln("Loading Game..");
	}

	public static void newGame() {
		Console.clear();

		createAttacks();

		player = new Player("Norman");

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

	private static void createAttacks() {
		Attacks.add(new Attack("punch", 60, 95));
	}
}