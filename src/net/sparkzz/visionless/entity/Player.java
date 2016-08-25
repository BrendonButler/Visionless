package net.sparkzz.visionless.entity;

import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Validate;
import net.sparkzz.visionless.game.Attack;
import net.sparkzz.visionless.game.Attacks;

import java.util.ArrayList;
import java.util.List;

import static net.sparkzz.visionless.game.Game.config;
import static net.sparkzz.visionless.utils.MathHelper.*;

/**
 * @author Brendon Butler
 */
public class Player extends MagicEntity {

	private List<Attack> attacks = new ArrayList<>();

	public Player(String name, int xp, int health, int maxHealth, int strength, int magic, int speed, int accuracy, int evasiveness, List<String> attacks) {
		super(name, findLevel(xp), maxHealth, strength, magic, speed, accuracy, evasiveness, null);

		setHealth(health);

		for (String attack : attacks)
			addAttack(Attacks.get(attack));
	}

	@Override
	public Attack attack() {
		int i = 1;

		Console.outln("What attack would you like to use?");

		for (Attack attack : attacks) {
			Console.outf("%s) %s%n", i, attack.getName());
			i++;
		}

		int responseID = 0;
		do {
			String response = Console.prompt("%n> ");

			if (Validate.isNumber(response))
				responseID = Integer.parseInt(response);

		} while (responseID == 0 || responseID > attacks.size());
		return attacks.get(responseID - 1);
	}

	public int getXPNeeded() {
		return (int) Math.floor(findXP(getLevel() + 1) - getXP());
	}

	@Override
	public List<Attack> getAttacks() {
		return attacks;
	}

	@Override
	public void addAttack(Attack attack) {
		// TODO: config.add("Player.Attacks", attack.getName());
		attacks.add(attack);
	}

	public void addXP(int xp) {
		setXP(getXP() + xp);
	}

	@Override
	public void removeAttack(Attack attack) {
		// TODO: config.remove("Player.Attack", attack.getName());
		attacks.remove(attack);
	}

	@Override
	public void setAccuracy(double accuracy) {
		config.set("Player.Accuracy", (int) accuracy);
		super.setAccuracy(accuracy);
	}

	@Override
	public void setEvasiveness(double evasiveness) {
		config.set("Player.Evasiveness", (int) evasiveness);
		super.setEvasiveness(evasiveness);
	}

	@Override
	public void setHealth(double health) {
		config.set("Player.Health", (int) health);
		super.setHealth(health);
	}

	@Override
	public void setMagic(double magic) {
		config.set("Player.Magic", (int) magic);
		super.setMagic(magic);
	}

	@Override
	public void setMaxHealth(double maxHealth) {
		config.set("Player.Max_Health", (int) maxHealth);
		super.setMaxHealth(maxHealth);
	}

	@Override
	public void setSpeed(double speed) {
		config.set("Player.Speed", (int) speed);
		super.setSpeed(speed);
	}

	@Override
	public void setStrength(double strength) {
		config.set("Player.Strength", (int) strength);
		super.setStrength(strength);
	}

	@Override
	public void setXP(int xp) {
		config.set("Player.Stats.XP", xp);
		super.setXP(xp);
	}
}