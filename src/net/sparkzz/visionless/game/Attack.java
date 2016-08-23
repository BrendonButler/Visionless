package net.sparkzz.visionless.game;

/**
 * @author Brendon Butler
 */
public class Attack {

	private Attacks.AttackType attackType;
	private int accuracy, damage;
	private String name;

	public Attack(String name, int damage, int accuracy, Attacks.AttackType attackType) {
		this.name = name;
		this.damage = damage;
		this.accuracy = accuracy;
		this.attackType = attackType;
	}

	public Attacks.AttackType getType() {
		return attackType;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public int getDamage() {
		return damage;
	}

	public String getName() {
		return name;
	}
}