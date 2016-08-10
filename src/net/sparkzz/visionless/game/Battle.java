package net.sparkzz.visionless.game;

import net.sparkzz.modest.io.console.Alignment;
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
		String lastAttacks = "";

		// TODO: clean this up
		while (continueBattle) {
			header(attacker, target, lastAttacks);
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
				int damage = calculateDamage(fighters[first], fighters[second], attacks[first]);
				fighters[second].hit(damage);
				lastAttacks = String.format("%n%s used %s and dealt %s damage!%n", fighters[first].getName(), attacks[first].getName(), damage);

				if (fighters[second].getHealth() == 0) {
					header(attacker, target, lastAttacks);
					Console.outf("%s won!%n", fighters[first].getName());

					return fighters[first];
				}
			} else lastAttacks = String.format("%n%s's attack missed!%n", fighters[first].getName());

			if (isHit(fighters[second], fighters[first], attacks[second])) {
				int damage = calculateDamage(fighters[second], fighters[first], attacks[second]);
				fighters[first].hit(damage);
				lastAttacks += String.format("%s used %s and dealt %s damage!%n", fighters[second].getName(), attacks[second].getName(), damage);

				if (fighters[first].getHealth() == 0) {
					header(attacker, target, lastAttacks);
					Console.outf("%s won!%n", fighters[second].getName());

					return fighters[second];
				}
			} else lastAttacks += String.format("%s's attack missed!%n", fighters[second].getName());
		}
		return null;
	}

	private int calculateDamage(BasicEntity attacker, BasicEntity target, Attack attack) {
		double damage = attacker.getStrength() * attack.getDamage() / 100;

		// if damage dealt is greater than the target's max health, set the damage dealt to the targets current health, else return original damage dealt
		return (int) Math.round((damage > target.getHealth() ? target.getHealth() : damage));
	}

	// TODO: accuracy numbers are always the same & algorithm doesn't work anyways
	private boolean isHit(BasicEntity attacker, BasicEntity target, Attack attack) {
		if (attack.getAccuracy() == 0) return true;

		Console.outln("Accuracy: " + ((attack.getAccuracy() * (attacker.getAccuracy() / target.getEvasiveness()) / 100)));

		return (1 < ((attack.getAccuracy() * (attacker.getAccuracy() / target.getEvasiveness()) / 100)));
	}

	private void header(BasicEntity attacker, BasicEntity target, String lastAttacks) {
		Console.clear();
		Console.fillLine('=');
		Console.align(Alignment.CENTER, attacker.getName() + " vs " + target.getName());
		Console.fillLine('-');
		Console.outf("%s's Health: %s/%s%n", attacker.getName(), (int) attacker.getHealth(), (int) attacker.getMaxHealth());
		Console.outf("%s's Health: %s/%s%n", target.getName(), (int) target.getHealth(), (int) target.getMaxHealth());

		if (lastAttacks != "")
			Console.out(lastAttacks);

		Console.fillLine('=');
	}
}