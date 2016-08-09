package net.sparkzz.visionless.entity;

/**
 * @author Brendon Butler
 */
public interface BasicEntity {

	String getName();

	int getHealth();

	int getMaxHealth();

	int getSpeed();

	int getStrength();

	void setName(String name);

	void setHealth(int health);

	void setMaxHealth(int maxHealth);

	void setSpeed(int speed);

	void setStrength(int strength);
}