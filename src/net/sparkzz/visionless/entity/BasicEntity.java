package net.sparkzz.visionless.entity;

import net.sparkzz.visionless.game.Attack;

/**
 * @author Brendon Butler
 */
public interface BasicEntity {

	Attack attack();

	double getAccuracy();

	double getEvasiveness();

	double getHealth();

	double getMaxHealth();

	double getSpeed();

	double getStrength();

	String getName();

	void addAttack(Attack attack);

	void hit(double damage);

	void removeAttack(Attack attack);

	void setAccuracy(double accuracy);

	void setEvasiveness(double evasiveness);

	void setHealth(double health);

	void setMaxHealth(double maxHealth);

	void setName(String name);

	void setSpeed(double speed);

	void setStrength(double strength);
}