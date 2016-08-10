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
	private double accuracy, evasiveness, health, maxHealth, speed, strength;

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

	public double getAccuracy() {
		return accuracy;
	}

	public double getEvasiveness() {
		return evasiveness;
	}

	public double getHealth() {
		return health;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public double getSpeed() {
		return speed;
	}

	public double getStrength() {
		return strength;
	}

	public String getName() {
		return name;
	}

	public void addAttack(Attack attack) {
		attacks.add(attack);
	}

	public void hit(double damage) {
		health -= damage;
	}

	public void removeAttack(Attack attack) {
		attacks.remove(attack);
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public void setEvasiveness(double evasiveness) {
		this.evasiveness = evasiveness;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setStrength(double strength) {
		this.strength = strength;
	}
}