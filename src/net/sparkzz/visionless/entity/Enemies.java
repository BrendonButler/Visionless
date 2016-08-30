package net.sparkzz.visionless.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brendon Butler
 */
public class Enemies {

	private static Map<String, BasicEntity> enemies = new HashMap();

	public enum Enemy {
		BAT("bat"), MAGIC_BAT("magic_bat"), SKELETON("skeleton"),
		WIZARD("wizard"), ZOMBIE("zombie");

		private final String name;

		Enemy(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	enum EnemyType {
		HUMANOID, FLYING
	}

	public static BasicEntity getEnemy(Enemy enemy) {
		return enemies.get(enemy.getName());
	}

	public static BasicEntity getEnemy(String enemy) {
		return enemies.get(enemy);
	}

	public static Enemy getEnemyType(String enemy) {
		for (Enemy curEnemy : Enemy.values())
			if (curEnemy.getName().equalsIgnoreCase(enemy))
				return curEnemy;
		return null;
	}

	public static List<BasicEntity> getAllEnemies() {
		return new ArrayList<>(enemies.values());
	}

	public static void addEnemy(MagicEntity entity) {
		enemies.put(entity.getName(), entity);
	}

	public static void addEnemy(BasicEntity entity) {
		enemies.put(entity.getName(), entity);
	}
}