package net.sparkzz.visionless.entity.enemies;

import net.sparkzz.visionless.entity.Enemy;

/**
 * @author Brendon Butler
 */
public class Zombie implements Enemy {

	private String name;
	private int health, maxHealth, speed, strength;

	public Zombie() {
		name = "Zombie";
	}

	public Zombie(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
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

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void choseAttack() {

	}
}