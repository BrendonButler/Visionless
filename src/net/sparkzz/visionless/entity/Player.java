package net.sparkzz.visionless.entity;

/**
 * @author Brendon Butler
 */
public class Player implements BasicEntity {

	private String name;
	private int health, magic, maxHealth, speed, strength;

	public Player(String name) {
		this.name = name;
		this.health = 0;
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public int getMagic() {
		return magic;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getSpeed() {
		return speed;
	}

	public int getStrength() {
		return strength;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}
}