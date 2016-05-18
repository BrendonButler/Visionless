package net.sparkzz.visionless.enemy;

/**
 * @author Brendon Butler
 */
public class Zombie implements Enemy {

	private final String name = "Zombie";
	private int health = 20;

	public Zombie() {

	}

	public Zombie(int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	public String getName() {
		return name;
	}
}
