package inf101.games.invaders.sprites;

import inf101.games.invaders.IGame;
import inf101.games.invaders.IImage;
import inf101.games.invaders.IRectangle;

/**
 * Note: the handling of bullets (or laser beams...) is overly naive: since
 * collision detection only takes into account whether something belongs to the
 * "alien team" or not, the player's bullets can collide with the alien bullets
 * and destroy them – if this is not desirable, one should have more collision
 * groups (e.g., alien ship, player ship, alien bullet, player bullet), instead
 * of just using a simple boolean.
 * 
 */
public class Bullet implements ISprite {
	private final IImage image;
	private IRectangle box;
	private int delta = 2;
	private final boolean alien;

	public Bullet(IImage image, int x, int y, int yDelta, boolean alien) {
		this.image = image;
		this.box = image.getBoundingBox().moveTo(x, y);
		this.delta = yDelta;
		this.alien = alien;
	}

	@Override
	public IRectangle getBoundingBox() {
		return box;
	}

	@Override
	public IImage getImage() {
		return image;
	}

	@Override
	public void step(IGame game) {
		box = box.move(0, delta);
		if (!game.getBoundingBox().contains(box))
			game.remove(this);
	}

	@Override
	public boolean isCollidedWith(ISprite other) {
		return isAlien() != other.isAlien()
				&& box.overlaps(other.getBoundingBox());
	}

	@Override
	public void hit(ISprite other, IGame game) {
		game.remove(this);
	}

	@Override
	public boolean isAlien() {
		return alien;
	}

}
