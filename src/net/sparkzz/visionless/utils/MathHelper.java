package net.sparkzz.visionless.utils;

import static java.lang.Math.*;

/**
 * Created by Brendon Butler on 8/24/2016.
 */
public class MathHelper {

	/*
	 * 100  - base xp
	 * 2.05 - factor
	 *
	 * ref: http://gamedev.stackexchange.com/questions/8965/how-to-implement-an-experience-system
	 * ref: http://staff.argyll.epsb.ca/jreed/math30p/logarithms/growth.htm
	 */
	public static int findXP(int level) {
		return (int) floor(100 * pow(level, 2));
	}

	public static int findStat(int level, int base) {
		return (int) floor((base * 2 * level + 50) / 10);
	}

	/*
	 * TODO: use 2.05 as the power (having trouble finding the nth power of a number.. need a custom math method)
	 * method in the second reference almost works, numbers are too precise?
	 * level 2.99999999988 instead of level 3 && with xp less than needed for next level, level == 2.9999999888 or similar so I can't round down :/
	 *
	 * 100  - base xp
	 * 2.05 - factor
	 *
	 * ref: http://gamedev.stackexchange.com/questions/8965/how-to-implement-an-experience-system
	 * ref: http://staff.argyll.epsb.ca/jreed/math30p/logarithms/growth.htm
	 */
	public static int findLevel(int xp) {
		if (xp >= findXP(100)) return 100; // 100 - level
		return (int) floor(sqrt(xp / 100)) + 1;
	}
}