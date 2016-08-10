package net.sparkzz.visionless.game;

import net.sparkzz.modest.io.console.Console;
import net.sparkzz.visionless.entity.BasicEntity;

import java.util.Random;

/**
 * @author Brendon Butler
 */
public class Battle {

	private boolean isAttackerDead = false;
	private boolean isTargetDead = false;

	public BasicEntity startBattle(BasicEntity attacker, BasicEntity target) {
		boolean continueBattle = true;

		// TODO: clean this up
		while (continueBattle) {
			Attack[] attacks = new Attack[2];
			BasicEntity[] fighters = new BasicEntity[2];
			int first, second;

			fighters[0] = attacker;
			fighters[1] = target;

			attacks[0] = fighters[0].attack();
			attacks[1] = fighters[1].attack();

			if (attacker.getSpeed() > target.getSpeed()) {
				first = 0;
				second = 1;
			} else if (attacker.getSpeed() < attacker.getSpeed()) {
				first = 1;
				second = 0;
			} else {
				Random random = new Random();

				if (random.nextInt(1) == 0) {
					first = 0;
					second = 1;
				} else {
					first = 1;
					second = 0;
				}
			}

			if (isHit(fighters[first], fighters[second], attacks[first])) {
				fighters[second].hit(calculateDamage(fighters[first], fighters[second], attacks[first]));
				Console.outf("%s used %s!", fighters[first].getName(), attacks[first].getName());

				if (fighters[second].getHealth() == 0) return fighters[first];
			} else Console.outf("%s's attack missed!", fighters[first].getName());

			if (isHit(fighters[second], fighters[first], attacks[second])) {
				fighters[first].hit(calculateDamage(fighters[second], fighters[first], attacks[second]));
				Console.outf("%s used %s!", fighters[second].getName(), attacks[second].getName());

				if (fighters[first].getHealth() == 0) return fighters[second];
			} else Console.outf("%s's attack missed!", fighters[second].getName());
		}
		return null;
	}

	private int calculateDamage(BasicEntity attacker, BasicEntity target, Attack attack) {
		int damage = (attack.getDamage() / 100) * attacker.getStrength();

		// if damage dealt is greater than the target's max health, set the damage dealt to the targets current health, else return original damage dealt
		return damage > target.getHealth() ? target.getHealth() : damage;
	}

	private boolean isHit(BasicEntity attacker, BasicEntity target, Attack attack) {
		if (attack.getAccuracy() == 0) return true;

		return (1 < ((attack.getAccuracy() * (attacker.getAccuracy() / target.getEvasiveness()) / 100)));
	}
}