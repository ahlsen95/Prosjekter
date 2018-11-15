package inf101.games.invaders.sprites;

import inf101.games.invaders.IImage;
import inf101.games.invaders.Images;
import inf101.games.invaders.InvadersGame;

public class BulletSpawner implements ISpawner {

	private boolean alien;
	private int speed;

	/**
	 * Create a spawner that makes bullets.
	 * 
	 * @param belongsToAlien
	 *            True if the bullets belong to the alien (will hit the player
	 *            and pass through aliens)
	 * @param speed Speed of the created bullets
	 */
	public BulletSpawner(boolean belongsToAlien, int speed) {
		this.alien = belongsToAlien;
		this.speed = speed;
	}

	@Override
	public ISprite newSprite(int x, int y) {
		IImage img = alien ? Images.bullets.get(InvadersGame.getRandom().nextInt(3)) : Images.bullet4;
		int direction = alien ? 1 : -1;
		ISprite bullet = new Bullet(img, x, y, direction * speed, alien);
		return bullet;
	}

}
