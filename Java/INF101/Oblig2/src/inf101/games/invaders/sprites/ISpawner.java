package inf101.games.invaders.sprites;

/**
 * Interface for sprite factories – objects that create new sprites
 * 
 * @author Anya Helene Bagge
 *
 */
public interface ISpawner {
	/**
	 * Make a new sprite at position (x, y)
	 * 
	 * The caller is responsible for adding the sprite to the game.
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @return a new sprite
	 */
	ISprite newSprite(int x, int y);
}
