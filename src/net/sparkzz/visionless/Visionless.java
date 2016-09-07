package net.sparkzz.visionless;

import net.sparkzz.modest.ModestGame;
import net.sparkzz.modest.utils.Languages;
import net.sparkzz.visionless.game.Menu;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brendon Butler on 3/23/2016.
 */
public class Visionless extends ModestGame {

	public Visionless() {
		postInit();
	}

	public static void main(String[] args) {
		new Visionless();
	}

	@Override
	public void postInit() {
		localization();
		Menu.mainMenu();
	}

	private void localization() {
		// localization
		Map<String, String> english = new HashMap<>();

		// achievements
		english.put("ach_first_kill", "First Kill!");
		english.put("ach_first_kill_desc", "Get your first kill.");

		// entities
		english.put("bat", "Bat");
		english.put("magic_bat", "Magic Bat");
		english.put("skeleton", "Skeleton");
		english.put("wizard", "Wizard");
		english.put("zombie", "Zombie");

		// attacks
		english.put("bite", "Bite");
		english.put("punch", "Punch");
		english.put("sludge", "Sludge");

		// tags
		english.put("tag_acy", "ACY");
		english.put("tag_hp", "HP");
		english.put("tag_lvl", "LVL");
		english.put("tag_mgk", "MGK");
		english.put("tag_pwr", "PWR");
		english.put("tag_spd", "SPD");
		english.put("tag_str", "STR");
		english.put("tag_xp", "XP");

		// menu items
		english.put("menu_attacks", "Attacks");
		english.put("menu_back", "Back");
		english.put("menu_battle", "Battle");
		english.put("menu_load_game", "Load Game");
		english.put("menu_main", "Main Menu");
		english.put("menu_new_game", "New Game");
		english.put("menu_play", "Play");
		english.put("menu_quit", "Quit");
		english.put("menu_stats", "Stats");

		// questions
		english.put("would_like_to_do", "What would you like to do?");
		english.put("what_is_your_name", "What is your name?");

		// statements/exclamations
		english.put("attack_missed", "%s's attack missed!");
		english.put("leveled_up", "You have leveled up!");
		english.put("must_heal_up", "You must heal up before battling again!");
		english.put("player_won", "%s won!");
		english.put("players_health", "%s's Health");
		english.put("used_attack", "%s used %s and dealt %s damage!");
		english.put("xp_gained", "You have gained %s XP!");
		english.put("xp_needed", "%s tag_xp needed for next level");

		// etc
		english.put("saves", "saves");

		Languages.addLanguage("english", english);
		Languages.setLanguage("english");
	}
}