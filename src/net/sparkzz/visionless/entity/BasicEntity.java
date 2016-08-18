package net.sparkzz.visionless.entity;

import net.sparkzz.visionless.game.Attack;
import net.sparkzz.visionless.game.Attacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Brendon Butler
 */
public class BasicEntity {

	private List<Attack> attacks = new ArrayList<>();
	private String name;
	protected double accuracy, evasiveness, health, maxHealth, speed, strength;

	public BasicEntity(String name, int HP, int maxHP, int strength, int speed, int accuracy, int evasiveness, List<String> attacks) {
		this.name = name;
		setHealth(HP);
		setMaxHealth(maxHP);
		setStrength(strength);
		setSpeed(speed);
		setAccuracy(accuracy);
		setEvasiveness(evasiveness);

		if (attacks != null)
			for (String attack : attacks)
				addAttack(Attacks.get(attack));
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

	public List<Attack> getAttacks() {
		return attacks;
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