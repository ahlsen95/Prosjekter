package inf101.games.invaders;

/**
 * Interface for rectangle.
 *
 * Rectangles are defined by their positions and width / height.
 */
public interface IRectangle  {
	/**
	 * Check if a position is inside the rectangle.
	 * 
	 * A position is inside if it lies within the box defined by
	 * 
	 * (getX(), getY()) to (getX()+getWidth(),getY()+getHeight())
	 * 
	 * @param pos
	 *            A position
	 * @return True if the position lies within the rectangle
	 */
	boolean contains(IPosition pos);

	/**
	 * Check if another rectangle is fully contained within this rectangle.
	 * 
	 * A rectangle is inside if it lies within the box defined by
	 * 
	 * (getX(), getY()) to (getX()+getWidth(),getY()+getHeight())
	 * 
	 * Implies <code>overlaps(other)</code>
	 * 
	 * @param pos
	 *            A position
	 * @return True if the position lies within the rectangle
	 */
	boolean contains(IRectangle other);
	
	/**
	 * @return Height of the rectangle
	 */
	int getHeight();
	
	/**
	 * @return The rectangle's position
	 */
	IPosition getPosition();
	
	/**
	 * @return Width of the rectangle
	 */
	int getWidth();

	/**
	 * @return X coordinate (same as getPosition().getX())
	 */
	int getX();

	/**
	 * @return Y coordinate (same as getPosition().getY())
	 */
	int getY();
	
	/**
	 * Make a new rectangle with the same width/height, moved by deltaX,deltaY
	 * 
	 * @param deltaX
	 *            Change in the X-direction
	 * @param deltaY
	 *            Change in the Y-direction
	 * @return A rectangle positioned at (getX()+deltaX, getY()+deltaY)
	 */
	IRectangle move(int deltaX, int deltaY);
	
	
	/**
	 * Make a new rectangle with the same width/height,, positioned at newX, newY
	 * @param newX
	 * @param newY
	 * @return A rectangle positioned at (newX,newY)
	 */
	IRectangle moveTo(int newX, int newY);
	
	/**
	 * Check if two rectangles overlap.
	 * 
	 * @param rect A rectangle.
	 * @return True if any part of the given rectangle overlaps this rectangle
	 */
	boolean overlaps(IRectangle rect);
}
