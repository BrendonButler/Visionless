package net.sparkzz.visionless.entity;

import java.util.List;

import static net.sparkzz.visionless.utils.MathHelper.findStat;

/**
 * @author Brendon Butler
 */
public class MagicEntity extends BasicEntity {

	private final int BASE_MAGIC;
	protected double magic;

	public MagicEntity(String name, int level, int HP, int strength, int magic, int speed, int accuracy, List<String> attacks) {
		super(name, level, HP, strength, speed, accuracy, attacks);

		BASE_MAGIC = magic;

		determineStats();
	}

	@Override
	public double getBaseAccuracy() {
		return BASE_ACCURACY;
	}

	@Override
	public double getBaseHealth() {
		return BASE_HP;
	}

	public double getBaseMagic() {
		return BASE_MAGIC;
	}

	@Override
	public double getBaseSpeed() {
		return BASE_SPEED;
	}

	@Override
	public double getBaseStrength() {
		return BASE_STRENGTH;
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
	}

	public void setMagic(double magic) {
		this.magic = magic;
	}
}