package net.sparkzz.visionless.entity;

import net.sparkzz.visionless.game.Attack;
import net.sparkzz.visionless.game.Attacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.sparkzz.visionless.utils.MathHelper.*;

/**
 * @author Brendon Butler
 */
public class BasicEntity {

	protected double accuracy, evasiveness, health, maxHealth, speed, strength;

	private final int BASE_ACCURACY, BASE_EVASIVENESS, BASE_HP, BASE_SPEED, BASE_STRENGTH;
	private int xp;
	private List<Attack> attacks = new ArrayList<>();
	private String name;

	public BasicEntity(String name, int level, int HP, int strength, int speed, int accuracy, int evasiveness, List<String> attacks) {
		this.name = name;

		BASE_ACCURACY = accuracy;
		BASE_EVASIVENESS = evasiveness;
		BASE_HP = HP;
		BASE_SPEED = speed;
		BASE_STRENGTH = strength;

		setXP(findXP(level));
		determineStats();

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

	public int getLevel() {
		return findLevel(xp);
	}

	public int getXP() {
		return xp;
	}

	public List<Attack> getAttacks() {
		return attacks;
	}

	public List<String> getAttackNames() {
		List<String> attackNames = new ArrayList<>();

		for (Attack attack : attacks)
			attackNames.add(attack.toString());

		return attackNames;
	}

	public String getName() {
		return name;
	}

	public void addAttack(Attack attack) {
		attacks.add(attack);
	}

	public void addXP(int xp) {
		setXP(getXP() + xp);
	}

	public void determineStats() {
		int level = findLevel(xp);

		setHealth(findStat(level, BASE_HP));
		setMaxHealth(findStat(level, BASE_HP));
		setStrength(findStat(level, BASE_STRENGTH));
		setSpeed(findStat(level, BASE_SPEED));
		setAccuracy(BASE_ACCURACY);
		setEvasiveness(BASE_EVASIVENESS);
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

	public void setXP(int xp) {
		if (getLevel() > findLevel(xp)) {
			this.xp = xp;
			determineStats();
			return;
		}
		this.xp = xp;
	}
}