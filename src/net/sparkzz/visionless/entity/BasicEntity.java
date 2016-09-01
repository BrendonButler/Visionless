package net.sparkzz.visionless.entity;

import net.sparkzz.visionless.game.Attack;
import net.sparkzz.visionless.game.Attacks;
import net.sparkzz.visionless.game.Menu;

import java.util.*;

import static net.sparkzz.visionless.utils.MathHelper.*;

/**
 * @author Brendon Butler
 */
public class BasicEntity {

	protected final int BASE_ACCURACY, BASE_HP, BASE_SPEED, BASE_STRENGTH;

	private double accuracy, health, maxHealth, speed, strength;
	private int xp;
	private Set<Attack> attacks = new HashSet<>();
	private String name;

	public BasicEntity(String name, int level, int HP, int strength, int speed, int accuracy, List<String> attacks) {
		this.name = name;

		BASE_ACCURACY = accuracy;
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

		return getAttack(random.nextInt(attacks.size()));
	}

	public Attack getAttack(int attack) {
		List<Attack> availableAttacks = new ArrayList<>(attacks);

		return availableAttacks.get(attack);
	}

	public double getBaseAccuracy() {
		return BASE_ACCURACY;
	}

	public double getBaseHealth() {
		return BASE_HP;
	}

	public double getBaseSpeed() {
		return BASE_SPEED;
	}

	public double getBaseStrength() {
		return BASE_STRENGTH;
	}

	public double getAccuracy() {
		return accuracy;
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

	public Enemies.Enemy getType() {
		return Enemies.getEnemyType(name);
	}

	public int getLevel() {
		return findLevel(xp);
	}

	public int getXP() {
		return xp;
	}

	public Set<Attack> getAttacks() {
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

		setHealth(findHPStat(level, BASE_HP));
		setMaxHealth(findStat(level, BASE_HP));
		setStrength(findStat(level, BASE_STRENGTH));
		setSpeed(findStat(level, BASE_SPEED));
		setAccuracy(BASE_ACCURACY);
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
		if (xp < 0) xp = 0;

		this.xp = xp;
	}
}