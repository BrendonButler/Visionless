package net.sparkzz.visionless.entity;

import net.sparkzz.visionless.game.Attack;

/**
 * @author Brendon Butler
 */
public interface BasicEntity {

	Attack attack();

	String getName();

	int getAccuracy();

	int getEvasiveness();

	int getHealth();

	int getMaxHealth();

	int getSpeed();

	int getStrength();

	void addAttack(Attack attack);

	void hit(int damage);

	void removeAttack(Attack attack);

	void setAccuracy(int accuracy);

	void setEvasiveness(int evasiveness);

	void setName(String name);

	void setHealth(int health);

	void setMaxHealth(int maxHealth);

	void setSpeed(int speed);

	void setStrength(int strength);
}