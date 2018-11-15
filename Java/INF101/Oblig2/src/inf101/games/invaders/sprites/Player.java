package inf101.games.invaders.sprites;

import inf101.games.invaders.IGame;
import inf101.games.invaders.IImage;

public class Player extends AbstractShip {
	private int delta = 0;
	private int speed = 2;
	private boolean shooting = false;

	public Player(IImage image) {
		this(image, 0, 0);
	}

	public Player(IImage image, int x, int y) {
		super(image, x, y);
	}


	@Override
	public void step(IGame game) {
		super.step(game);

		box = box.move(delta, 0);

		if (box.getX() < 0)
			box = box.moveTo(0, box.getY());
		else if (box.getX() + box.getWidth() > game.getBoundingBox().getWidth())
			box = box.moveTo(game.getBoundingBox().getWidth() - box.getWidth(),
					box.getY());

		if (shooting)
			fire(game);

	}

	public void moveLeft(boolean moving) {
		if (moving)
			delta = -speed;
		else if (delta < 0)
			delta = 0;
	}

	public void moveRight(boolean moving) {
		if (moving)
			delta = speed;
		else if (delta > 0)
			delta = 0;
	}

	public void shoot(boolean shooting) {
		this.shooting = shooting;
//		if(!shooting)
//			coolDown = 0;           
	}

	@Override
	public void hit(ISprite other, IGame game) {
		System.out.println("BOOM!");
		game.remove(this);
	}

	@Override
	public boolean isAlien() {
		return false;
	}
}
