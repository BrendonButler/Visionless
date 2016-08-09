package net.sparkzz.visionless;

import net.sparkzz.modest.ModestGame;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.visionless.entity.Player;

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
		Player player = new Player("Norman");

		player.setHealth(20);
		player.setMaxHealth(20);
		player.setStrength(6);
		player.setMagic(4);
		player.setSpeed(5);

		Console.outf("Name: %s", player.getName());
		Console.outf("HP: %s/%s", player.getHealth(), player.getMaxHealth());
		Console.outf("STR: %s", player.getStrength());
		Console.outf("MGK: %s", player.getMagic());
		Console.outf("SPD: %s", player.getSpeed());
	}
}