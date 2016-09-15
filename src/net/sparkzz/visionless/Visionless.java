package net.sparkzz.visionless;

import net.sparkzz.modest.ModestGame;
import net.sparkzz.modest.utils.Languages;
import net.sparkzz.visionless.game.Menu;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
		localization();
		Menu.mainMenu();
	}

	@Override
	protected void init() {}

	private void localization() {
		Languages.loadAllFromFile("res/lang");
		Languages.setLanguage("en");
	}
}