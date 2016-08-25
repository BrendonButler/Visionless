package net.sparkzz.visionless.entity;

import java.util.List;

import static net.sparkzz.visionless.utils.MathHelper.findStat;

/**
 * @author Brendon Butler
 */
public class MagicEntity extends BasicEntity {

	private final int BASE_ACCURACY, BASE_EVASIVENESS, BASE_HP, BASE_MAGIC, BASE_SPEED, BASE_STRENGTH;
	protected double magic;

	public MagicEntity(String name, int level, int HP, int strength, int magic, int speed, int accuracy, int evasiveness, List<String> attacks) {
		super(name, level, HP, strength, speed, accuracy, evasiveness, attacks);

		BASE_ACCURACY = accuracy;
		BASE_EVASIVENESS = evasiveness;
		BASE_HP = HP;
		BASE_MAGIC = magic;
		BASE_SPEED = speed;
		BASE_STRENGTH = strength;

		determineStats();
	}

	public double getMagic() {
		return magic;
	}

	@Override
	public void determineStats() {
		setHealth(findStat(getLevel(), BASE_HP));
		setMaxHealth(findStat(getLevel(), BASE_HP));
		setStrength(findStat(getLevel(), BASE_STRENGTH));
		setMagic(findStat(getLevel(), BASE_MAGIC));
		setSpeed(findStat(getLevel(), BASE_SPEED));
		setAccuracy(BASE_ACCURACY);
		setEvasiveness(BASE_EVASIVENESS);
	}

	public void setMagic(double magic) {
		this.magic = magic;
	}
}