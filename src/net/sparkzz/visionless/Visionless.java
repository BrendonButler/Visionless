package net.sparkzz.visionless;

import net.sparkzz.modest.ModestGame;
import net.sparkzz.modest.io.console.Console;

/**
 * Created by Brendon Butler on 3/23/2016.
 */
public class Visionless extends ModestGame {

	public Visionless() {
		run();
	}

    public static void main(String[] args) {
		new Visionless();
    }

	@Override
	public void postInit() {
		Console.out("Working!");
	}
}