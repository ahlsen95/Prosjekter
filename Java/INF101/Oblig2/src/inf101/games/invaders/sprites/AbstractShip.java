package inf101.games.invaders.sprites;

import inf101.games.invaders.IGame;
import inf101.games.invaders.IImage;

public abstract class AbstractShip extends AbstractSprite implements ISprite {
	protected int coolDown = 0;
	private final int coolPeriod;
	private final BulletSpawner bulletSpawner;

	public AbstractShip(IImage image, int x, int y) {
		super(image, x, y);
		this.coolPeriod = 50;
		this.bulletSpawner = new BulletSpawner(isAlien(), 4);
	}

	@Override
	public void step(IGame game) {
		if (coolDown > 0)
			coolDown--;
	}

	protected void fire(IGame game) {
		if (coolDown <= 0) {
			spawn(game, bulletSpawner, true);
			coolDown = coolPeriod;
		}
	}
}