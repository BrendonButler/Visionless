package net.sparkzz.visionless.entity.enemies;

import net.sparkzz.visionless.entity.Enemy;
import net.sparkzz.visionless.game.Attack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Brendon Butler
 */
public class Zombie implements Enemy {

	private List<Attack> attacks = new ArrayList<>();
	private String name;
	private int accuracy, evasiveness, health, maxHealth, speed, strength;

	public Zombie() {
		name = "Zombie";
	}

	public Zombie(String name) {
		this.name = name;
	}

	public Attack attack() {
		Random random = new Random();

		int randAttack = random.nextInt(attacks.size());

		return attacks.get(randAttack);
	}

	public String getName() {
		return name;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public int getEvasiveness() {
		return evasiveness;
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

	public void addAttack(Attack attack) {
		attacks.add(attack);
	}

	public void hit(int damage) {
		health -= damage;
	}

	public void removeAttack(Attack attack) {
		attacks.remove(attack);
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public void setEvasiveness(int evasiveness) {
		this.evasiveness = evasiveness;
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
}