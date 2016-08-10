package net.sparkzz.visionless.entity;

import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Validate;
import net.sparkzz.visionless.game.Attack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brendon Butler
 */
public class Player implements MagicEntity {

	private String name;
	private double accuracy, evasiveness, health, magic, maxHealth, speed, strength;
	private List<Attack> attacks = new ArrayList<>();

	public Player(String name) {
		this.name = name;
		this.health = 0;
	}

	public Attack attack() {
		int i = 1;

		Console.outln("What attack would you like to use?");

		for (Attack attack : attacks) {
			Console.outf("%s) %s%n", i, attack.getName());
			i++;
		}

		int responseID = 0;
		String response = Console.prompt("%n> ");

		if (Validate.isNumber(response))
			responseID = Integer.parseInt(response);

		return attacks.get(responseID - 1);
	}

	public String getName() {
		return name;
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

	public double getMagic() {
		return magic;
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

	public void setMagic(double magic) {
		this.magic = magic;
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