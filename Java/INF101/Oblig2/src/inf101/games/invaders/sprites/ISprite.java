package inf101.games.invaders.sprites;

import inf101.games.invaders.IGame;
import inf101.games.invaders.IImage;
import inf101.games.invaders.IRectangle;

/**
 * A game object to be shown on-screen
 */
public interface ISprite {
	/**
	 * The bounding box is a rectangle indicating the sprite's position and size
	 * on screen.
	 * 
	 * @return The sprite's bounding box
	 */
	IRectangle getBoundingBox();

	/**
	 * @return Image to be displayed for the sprite
	 */
	IImage getImage();

	/**
	 * Test for collision
	 * 
	 * @param other
	 *            Another sprite
	 * @return True if this sprite overlaps with the other sprite
	 */
	boolean isCollidedWith(ISprite other);

	/**
	 * Move the sprite one time step
	 * 
	 * @param game
	 *            The current game
	 */
	void step(IGame game);

	/**
	 * Notify the sprite that it has hit another sprite
	 * 
	 * Typically, the current sprite should be removed, e.g., with
	 * <code>game.remove(this)</code>, but there could also be some form of hit
	 * point system or other computation involved.
	 * 
	 * @param other
	 *            Another sprite, for which isCollidedWIth(other) is true
	 * @param game
	 *            The current game
	 */
	void hit(ISprite other, IGame game);

	/**
	 * Used in collision detection
	 * 
	 * Normally, sprites that are on the same "team" shouldn't hit each other,
	 * so we should disable collision when isAlien == other.isAlien().
	 * 
	 * @return True if this sprite belongs to the alien team, false if it
	 *         belongs to the player
	 */
	boolean isAlien();
}
