package inf101.games.invaders.sprites;

import inf101.games.invaders.IGame;
import inf101.games.invaders.IImage;
import inf101.games.invaders.IRectangle;

public abstract class AbstractSprite implements ISprite {
	private final IImage image;
	protected IRectangle box;

	public AbstractSprite(IImage image, int x, int y) {
		this.image = image;
		this.box = image.getBoundingBox().moveTo(x, y);
	}

	@Override
	public IRectangle getBoundingBox() {
		return box;
	}

	@Override
	public IImage getImage() {
		return image;
	}

	@Override //ser om det actuallet rektanglet overlapper et annet gitt rektangel.
	public boolean isCollidedWith(ISprite other) { 
		if (this.getBoundingBox().overlaps(other.getBoundingBox())){
			if (this.isAlien() != other.isAlien()) { //Om det er du forskjellige, alien og ikke alien returneres true.
				return true;	
			}
		}
		return false;
		
	}
	

	@Override
	public void hit(ISprite other, IGame game) {
		game.remove(this);
	}

	/**
	 * Spawn a new sprite and add it to the game at the current sprite's location.
	 * 
	 * @param game The game
	 * @param spawner Factory for new sprites
	 * @param center True if the new sprite's location should be centered on this sprite
	 */
	protected void spawn(IGame game, ISpawner spawner, boolean center) {
		int x = getBoundingBox().getX();
		int y = getBoundingBox().getY();
		
		if(center) {
			x = x + getBoundingBox().getWidth() / 2;
			y = y + getBoundingBox().getHeight() / 2;
		}

		ISprite spawn = spawner.newSprite(x, y);
		game.add(spawn);
	}

}