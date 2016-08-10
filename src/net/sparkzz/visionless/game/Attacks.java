package net.sparkzz.visionless.game;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brendon Butler
 */
public class Attacks {

	private Map<String, Attack> attacks = new HashMap<>();

	public void addAttack(Attack attack) {
		attacks.put(attack.getName(), attack);
	}

	public void getAttack(String name) {
		attacks.get(name);
	}
}