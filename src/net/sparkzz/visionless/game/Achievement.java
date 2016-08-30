package net.sparkzz.visionless.game;

/**
 * @author Brendon Butler
 */
public class Achievement {

	private final String title, description;

	public Achievement(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getTitle() {
		return title;
	}
}