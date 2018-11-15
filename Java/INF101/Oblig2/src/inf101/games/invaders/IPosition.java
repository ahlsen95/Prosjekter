package inf101.games.invaders;

/**
 * A position in 2D cartesian space.
 * 
 * Positions are immutable, e.g., they cannot be changed after they are created.
 */
public interface IPosition {
	/**
	 * @return X coordinate
	 */
	int getX();

	/**
	 * @return Y coordinate
	 */
	int getY();

	/**
	 * Make a new position by adding to x,y
	 * 
	 * @param deltaX
	 *            Change in the X-direction
	 * @param deltaY
	 *            Change in the Y-direction
	 * @return A new position at (getX()+deltaX, getY()+deltaY)
	 */
	IPosition move(int deltaX, int deltaY);
}