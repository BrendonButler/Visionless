package net.sparkzz.visionless.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brendon Butler
 */
public class Enemies {

	private static Map<String, BasicEntity> enemies = new HashMap();

	public enum Enemy {
		ZOMBIE("zombie"), BAT("bat"), MAGIC_BAT("magic_bat");

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

	public static void addEnemy(MagicEntity entity) {
		enemies.put(entity.getName(), entity);
	}

	public static void addEnemy(BasicEntity entity) {
		enemies.put(entity.getName(), entity);
	}
}