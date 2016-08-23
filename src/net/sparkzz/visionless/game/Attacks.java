package net.sparkzz.visionless.game;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brendon Butler
 */
public class Attacks {

	public enum AttackType {
		PHYSICAL, MAGIC
	}

	private static Map<String, Attack> attacks = new HashMap<>();

	public static Attack get(String name) {
		return attacks.get(name);
	}

	public static void add(Attack attack) {
		attacks.put(attack.getName(), attack);
	}
}