package net.sparkzz.visionless.game;

/**
 * @author Brendon Butler
 */
public class Attack {

	private int accuracy, damage;
	private String name;

	public Attack(String name, int damage, int accuracy) {
		this.name = name;
		this.damage = damage;
		this.accuracy = accuracy;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public int getDamage() {
		return damage;
	}

	public String getName() {
		return name;
	}
}