package net.sparkzz.visionless.entity;

import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Validate;
import net.sparkzz.visionless.game.Attack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brendon Butler
 */
public class Player implements BasicEntity {

	private String name;
	private int accuracy, evasiveness, health, magic, maxHealth, speed, strength;
	private List<Attack> attacks = new ArrayList<>();

	public Player(String name) {
		this.name = name;
		this.health = 0;
	}

	public Attack attack() {
		int i = 1;

		for (Attack attack : attacks) {
			Console.outf("%s) %s%n", i, attack.getName());
			i++;
		}

		int responseID = 0;
		String response = Console.prompt("What attack would you like to use?%n> ");

		if (Validate.isNumber(response))
			responseID = Integer.parseInt(response);

		return attacks.get(responseID - 1);
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