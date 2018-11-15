package inf101.games.invaders;

import java.util.Random;

import inf101.games.invaders.gui.IGUIPanel;
import inf101.games.invaders.sprites.ISprite;

/**
 * Interface for games.
 * 
 * This is used both by the GUI to figure out what should be shown on-screen,
 * and by the various sprites when they need to manipulate the game state (e.g.,
 * when a character dies, or if another sprite should be added because someone
 * just shot a bullet).
 * 
 */
public interface IGame {

	/**
	 * Add a sprite to the game
	 * 
	 * @param sprite
	 *            The sprite to be added
	 */
	void add(ISprite sprite);

	/**
	 * @return The file name of a background image
	 */
	String background();

	/**
	 * @return A rectangle defining the area of the display screen
	 */
	IRectangle getBoundingBox();


	/**
	 * Called when the user hits a key on the keyboard
	 * 
	 * @param keyCode
	 *            The key that was pressed
	 */
	void keyDown(int keyCode);

	/**
	 * Called when the user releases a key on the keyboard
	 * 
	 * @param keyCode
	 *            The key that was released
	 */
	void keyUp(int keyCode);

	/**
	 * Remove a sprite from the game
	 * 
	 * @param sprite
	 *            The sprite to be removed
	 */
	void remove(ISprite sprite);

	/**
	 * @return All sprites in the game, to be iterated over
	 */
	Iterable<? extends ISprite> sprites();

	/**
	 * @return A status text for display on the screen
	 */
	String status();

	/**
	 * Do one step of the game (moving sprites, etc.)
	 */
	void step();

	/**
	 * Add points to the current score
	 * 
	 * @param points
	 */
	void addScore(int points);

	void setGUIPanel(IGUIPanel guiPanel);
}
