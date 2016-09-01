package net.sparkzz.visionless.game;

import net.sparkzz.modest.io.console.Alignment;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.visionless.entity.BasicEntity;
import net.sparkzz.visionless.entity.Enemies;
import net.sparkzz.visionless.entity.MagicEntity;
import net.sparkzz.visionless.entity.Player;

import java.util.List;
import java.util.Random;

import static net.sparkzz.visionless.utils.MathHelper.*;

/**
 * @author Brendon Butler
 */
public class Battle {

	private boolean continueBattle(Player player, BasicEntity target, String lastAttacks) {
		if (player.getHealth() == 0 || target.getHealth() == 0) {
			int xp = 0;
			if (player.getHealth() == 0) {
				lastAttacks += String.format("%n%s won!%n", target.getName());
				header(player, target, "%n" + lastAttacks);
			} else if (target.getHealth() == 0) {
				xp = earnedXP(target);
				lastAttacks += String.format("%s won!%n", player.getName());
				lastAttacks += String.format("You have gained %s XP!%n", xp);
				header(player, target, "%n" + lastAttacks);
			}

			Console.prompt();
			player.addXP(xp + 10000);
			player.setHealth(player.getMaxHealth());
			target.setHealth(target.getMaxHealth());
			Menu.gameMenu();
			return false;
		}
		return true;
	}

	private boolean isHit(BasicEntity attacker, Attack attack) {
		Random chance = new Random();

		// if random number between 0 & 100 is less than the average of the attacker's & the attack's accuracy, return true
		if (chance.nextInt(100) < average(attacker.getAccuracy(), attack.getAccuracy()))
			return true;
		else return false;
	}

	private int calculateDamage(BasicEntity attacker, BasicEntity target, Attack attack) {
		Random additionalDamage = new Random();

		double damage = attack.getType() == Attacks.AttackType.MAGIC ? ((MagicEntity) attacker).getMagic() * attack.getDamage() / 100 : attacker.getStrength() * attack.getDamage() / 100;

		if (attacker instanceof MagicEntity)
			damage += additionalDamage.nextInt((int) ((MagicEntity) attacker).getMagic() / 4);
		else damage += additionalDamage.nextInt((int) attacker.getStrength() / 4);

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

	private int earnedXP(BasicEntity target) {
		int xp = (int) (10 + target.getLevel() * 5 * .5);

		switch (target.getType()) {
			case MAGIC_BAT:
				xp *= 1.5;
				break;
			case SKELETON:
				xp *= 1.25;
				break;
			case WIZARD:
				xp *= 2;
				break;
			default:
				break;
		}

		return xp;
	}

	private String attack(BasicEntity attacker, BasicEntity target) {
		Attack attack = attacker.attack();
		int damage;

		if (isHit(attacker, attack)) {
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

	public void randomBattle(Player player) {
		Random rand = new Random();
		List<BasicEntity> enemies = Enemies.getAllEnemies();

		startBattle(player, enemies.get(rand.nextInt(enemies.size())));
	}

	public void startBattle(Player player, BasicEntity target) {
		boolean continueBattle = true;
		String lastAttacks = "";

		while (continueBattle) {
			if (lastAttacks.equals("")) header(player, target, lastAttacks);
			else header(player, target, "%n" + lastAttacks);

			if (calculateFasterEntity(player, target) == 0)
				lastAttacks = attack(player, target);
			else if (calculateFasterEntity(player, target) == 1)
				lastAttacks = attack(target, player);
			else {
				Random nextAttacker = new Random();

				if (nextAttacker.nextInt(1) == 0)
					lastAttacks = attack(player, target);
				else lastAttacks = attack(target, player);
			}

			if (!continueBattle(player, target, lastAttacks)) break;

			if (calculateFasterEntity(player, target) == 1)
				lastAttacks += attack(player, target);
			else if (calculateFasterEntity(player, target) == 0)
				lastAttacks += attack(target, player);
			else {
				Random nextAttacker = new Random();

				if (nextAttacker.nextInt(1) == 1)
					lastAttacks += attack(player, target);
				else lastAttacks += attack(target, player);
			}

			if (!continueBattle(player, target, lastAttacks)) break;
		}
	}
}