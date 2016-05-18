package net.sparkzz.visionless;

import net.sparkzz.modest.ModestGame;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.visionless.enemy.Zombie;

import java.util.Random;

/**
 * Created by Brendon Butler on 3/23/2016.
 */
public class Visionless extends ModestGame {

	public Visionless() {
		postInit();
	}

    public static void main(String[] args) {
		new Visionless();
    }

	@Override
	public void postInit() {
		Zombie zombie = new Zombie();

		String input = Console.prompt("A Zombie appeared! What will you do?%n> ");

		if (input.equalsIgnoreCase("attack")) {
			Random random = new Random();
			int damage = random.nextInt(20);
			Console.outf("Zombie (%sHP)", zombie.getHealth() - damage);
		}
	}
}