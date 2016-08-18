package net.sparkzz.visionless.entity;

import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Validate;
import net.sparkzz.visionless.game.Attack;

import java.util.ArrayList;
import java.util.List;

import static net.sparkzz.visionless.game.Game.config;

/**
 * @author Brendon Butler
 */
public class Player implements MagicEntity {

	private String name;
	private double accuracy, evasiveness, health, magic, maxHealth, speed, strength;
	private List<Attack> attacks = new ArrayList<>();

	public Player(String name) {
		this.name = name;
	}

	public Attack attack() {
		int i = 1;

		Console.outln("What attack would you like to use?");

		for (Attack attack : attacks) {
			Console.outf("%s) %s%n", i, attack.getName());
			i++;
		}

		int responseID = 0;
		do {
			String response = Console.prompt("%n> ");

			if (Validate.isNumber(response))
				responseID = Integer.parseInt(response);

		} while (responseID == 0 || responseID > attacks.size());
		return attacks.get(responseID - 1);
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

	public List getAttacks() {
		List<String> attackNames = new ArrayList();

		for (Attack attack : attacks)
			attackNames.add(attack.getName());

		return attackNames;
	}

	public String getName() {
		return name;
	}

	public void addAttack(Attack attack) {
		attacks.add(attack);
	}

	public void hit(double damage) {
		setHealth(health - damage);
	}

	public void removeAttack(Attack attack) {
		// TODO: config.remove(String.format("%s.Attack", name), attack.getName());
		attacks.remove(attack);
	}

	public void setAccuracy(double accuracy) {
		config.set(String.format("%s.Accuracy", name), accuracy);
		this.accuracy = accuracy;
	}

	public void setEvasiveness(double evasiveness) {
		config.set(String.format("%s.Evasiveness", name), evasiveness);
		this.evasiveness = evasiveness;
	}

	public void setHealth(double health) {
		config.set(String.format("%s.Health", name), health);
		this.health = health;
	}

	public void setMagic(double magic) {
		config.set(String.format("%s.Magic", name), magic);
		this.magic = magic;
	}

	public void setMaxHealth(double maxHealth) {
		config.set(String.format("%s.Max_Health", name), maxHealth);
		this.maxHealth = maxHealth;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSpeed(double speed) {
		config.set(String.format("%s.Speed", name), speed);
		this.speed = speed;
	}

	public void setStrength(double strength) {
		config.set(String.format("%s.Strength", name), strength);
		this.strength = strength;
	}
}