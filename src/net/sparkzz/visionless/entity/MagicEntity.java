package net.sparkzz.visionless.entity;

import java.util.List;

/**
 * @author Brendon Butler
 */
public class MagicEntity extends BasicEntity {

	private double magic;

	public MagicEntity(String name, int HP, int strength, int magic, int speed, int accuracy, int evasiveness, List<String> attacks) {
		super(name, HP, strength, speed, accuracy, evasiveness, attacks);
		setMagic(magic);
	}

	public double getMagic() {
		return magic;
	}

	public void setMagic(double magic) {
		this.magic = magic;
	}
}