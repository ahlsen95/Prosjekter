package inf101.games.invaders;

public interface IImage {
	/**
	 * @return Name of the image file
	 */
	String getFileName();

	/**
	 * This is used if several sprites share the same image file.
	 * 
	 * @return The sub-area of the image file to display
	 */
	IRectangle getSourceBox();

	/**
	 * The bounding box for an image is a rectangle with position 0,0 and
	 * width,height equal to the width and height of the image.
	 * 
	 * @return The image's bounding box rectangle
	 */
	IRectangle getBoundingBox();
}
