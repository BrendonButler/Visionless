package net.sparkzz.visionless.game;

import net.sparkzz.modest.io.console.Alignment;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.visionless.entity.BasicEntity;
import net.sparkzz.visionless.entity.Enemies;
import net.sparkzz.visionless.entity.MagicEntity;

import java.util.List;
import java.util.Random;

import static net.sparkzz.visionless.utils.MathHelper.*;

/**
 * @author Brendon Butler
 */
public class Battle {

	private boolean continueBattle(BasicEntity attacker, BasicEntity target, String lastAttacks) {
		BasicEntity[] entities = new BasicEntity[2];
		entities[0] = attacker;
		entities[1] = target;

		for (int i = 0; i < entities.length; i++) {
			if (entities[i].getHealth() == 0) {
				header(attacker, target, "%n" + lastAttacks);

				if (i == 0) Console.outf("%s won!%n", target.getName());
				else Console.outf("%s won!%n", attacker.getName());

				target.setHealth(target.getMaxHealth());

				Console.prompt("%nType any key to continue:%n> ");
				Menu.gameMenu();
				return false;
			}
		}
		return true;
	}

	private boolean isHit(BasicEntity attacker, BasicEntity target, Attack attack) {
		Random chance = new Random();
		int hit = chance.nextInt(100);

		Console.outln(hit + "");

		// if random number between 0 & 100 is less than the average of the attacker's & the attack's accuracy, return true
		if (hit < average(attacker.getAccuracy(), attack.getAccuracy()))
			return true;
		else return false;
	}

	private int calculateDamage(BasicEntity attacker, BasicEntity target, Attack attack) {
		Random additionalDamage = new Random();

		double damage = attack.getType() == Attacks.AttackType.MAGIC ? ((MagicEntity) attacker).getMagic() * attack.getDamage() / 100 : attacker.getStrength() * attack.getDamage() / 100;

		damage += additionalDamage.nextInt((int) attacker.getStrength() / 4);

		// if damage dealt is greater than the target's max health, set the damage dealt to the targets current health, else return original damage dealt
		return (int) Math.round((damage > target.getHealth() ? target.getHealth() : damage));
	}

	private int calculateFasterEntity(BasicEntity first, BasicEntity second) {
		if (first.getSpeed() > second.getSpeed())
			return 0;
		else if (first.getSpeed() < second.getSpeed())
			return 1;
		else {
			Random random = new Random();

			if (random.nextInt(1) == 0)
				return 0;
			else return 1;
		}
	}

	private String attack(BasicEntity attacker, BasicEntity target) {
		Attack attack = attacker.attack();
		int damage;

		if (isHit(attacker, target, attack)) {
			damage = calculateDamage(attacker, target, attack);
			target.hit(damage);
			return String.format("%s used %s and dealt %s damage!%n", attacker.getName(), attack.getName(), damage);
		} else return String.format("%s's attack missed!%n", attacker.getName());
	}

	private void header(BasicEntity attacker, BasicEntity target, String lastAttacks) {
		Console.clear();
		Console.fillLine('=');
		Console.align(Alignment.CENTER, attacker.getName() + " vs " + target.getName());
		Console.fillLine('-');
		Console.outf("%s's Health: %s/%s%n", attacker.getName(), (int) attacker.getHealth(), (int) attacker.getMaxHealth());
		Console.outf("%s's Health: %s/%s%n", target.getName(), (int) target.getHealth(), (int) target.getMaxHealth());

		if (!lastAttacks.equals(""))
			Console.out(lastAttacks);

		Console.fillLine('=');
	}

	public void randomBattle(BasicEntity attacker) {
		Random rand = new Random();
		List<BasicEntity> enemies = Enemies.getAllEnemies();

		startBattle(attacker, enemies.get(rand.nextInt(enemies.size())));
	}

	public void startBattle(BasicEntity attacker, BasicEntity target) {
		boolean continueBattle = true;
		String lastAttacks = "";

		while (continueBattle) {
			if (lastAttacks.equals("")) header(attacker, target, lastAttacks);
			else header(attacker, target, "%n" + lastAttacks);

			if (calculateFasterEntity(attacker, target) == 0)
				lastAttacks = attack(attacker, target);
			else if (calculateFasterEntity(attacker, target) == 1)
				lastAttacks = attack(target, attacker);
			else {
				Random nextAttacker = new Random();

				if (nextAttacker.nextInt(1) == 0)
					lastAttacks = attack(attacker, target);
				else lastAttacks = attack(target, attacker);
			}

			if (!continueBattle(attacker, target, lastAttacks)) break;

			if (calculateFasterEntity(attacker, target) == 1)
				lastAttacks += attack(attacker, target);
			else if (calculateFasterEntity(attacker, target) == 0)
				lastAttacks += attack(target, attacker);
			else {
				Random nextAttacker = new Random();

				if (nextAttacker.nextInt(1) == 1)
					lastAttacks += attack(attacker, target);
				else lastAttacks += attack(target, attacker);
			}

			if (!continueBattle(attacker, target, lastAttacks)) break;
		}
	}
}