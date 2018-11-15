package inf101.games.invaders.gui;

public interface IGUIPanel {
	/**
	 * Display/update a multiline string of text on the screen.
	 * 
	 * The text may be displayed on top of whatever is on the screen.
	 * 
	 * @param text The text to display
	 * @param center True if the text should be centered
	 */
	void displayTextOverlay(String text, boolean center);
	
	/**
	 * Stop displaying the text overlay.
	 */
	void removeTextOverlay();
}