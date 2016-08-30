package net.sparkzz.visionless.game;

import net.sparkzz.visionless.entity.Enemies;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Brendon Butler
 */
public class Stats implements Serializable {

	private static Map<String, Achievement> achievements = new LinkedHashMap<>();
	private static Set<Enemies.Enemy> killedEnemies = new HashSet<>();

	public static void addAchievement(String title, Achievement achievement) {
		achievements.put(title, achievement);
	}

	public static void addKilledEnemy(Enemies.Enemy enemyType) {
		killedEnemies.add(enemyType);
	}
}