package inf101.games.invaders;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import inf101.games.invaders.gui.GUI;
import inf101.games.invaders.gui.IGUIPanel;
import inf101.games.invaders.sprites.Alien;
import inf101.games.invaders.sprites.ISprite;
import inf101.games.invaders.sprites.Player;

public class InvadersGame implements IGame {
	/**
	 * All sprites in the game
	 */
	private final List<ISprite> sprites = new ArrayList<>();
	/**
	 * List of sprites to remove during this step
	 */
	private final List<ISprite> toRemove = new ArrayList<>();
	/**
	 * List of new sprites to add during this step
	 */
	private final List<ISprite> toAdd = new ArrayList<>();
	/**
	 * The player's sprite
	 */
	private Player player;
	/**
	 * Bounding box of screen
	 */
	private final IRectangle boundingBox;
	/**
	 * Random generator
	 */
	public static final Random RANDOM = new Random();
	/**
	 * Player's current score
	 */
	private int score = 0;
	/**
	 * Current game level (increases when a wave is defeated)
	 */
	private int level = 0;
	/**
	 * True if game is running
	 */
	private boolean alive = true;
	/**
	 * The GUI, in case we need to display something other than the main game
	 * screen
	 */
	private IGUIPanel guiPanel;

	public static void main(String[] args) {
		IGame game = new InvadersGame(1024, 768);

		new GUI(game);
	}

	public InvadersGame(int width, int height) {
		boundingBox = new Rectangle(0, 0, width, height);

		start();
	}

	public void start() {
		alive = true;
		score = 0;
		level = 0;

		newAttackWave();
	}

	public void newAttackWave() {
		sprites.clear();
		level++;

		if (level % 1 == 0) {
			for (int i = 0; i < Images.aliens.size(); i++) {
				for (int j = 0; j < 12; j++) {
					Alien e = new Alien(Images.aliens.get(i), 50 + 45 * j, 50 + 45 * i);
					sprites.add(e);
				}
			}
		}

		player = new Player(Images.ship, boundingBox.getWidth() / 2, boundingBox.getHeight() - 50);
		sprites.add(player);
	}

	@Override
	public void step() {
		if (!alive)
			return;
		// Note: to avoid problems with ConcurrentModificationException, because
		// the list is
		// modified while iterating over it, any changes through add()/remove()
		// will just be recorded,
		// and not performed until sprites() is called. This means that, for
		// example, multiple bullets
		// can collide with the same ship before it is actually removed from the
		// list.
		for (ISprite e : sprites) {
			e.step(this);
		}

		for (ISprite e : sprites) {
			for (ISprite a : sprites) {
				if (e.isCollidedWith(a)) {
					a.hit(e, this);
				}
			}
		}
	}

	@Override
	public Iterable<? extends ISprite> sprites() {
		for (ISprite s : toRemove)
			sprites.remove(s);
		for (ISprite s : toAdd)
			sprites.add(s);
		toRemove.clear();
		toAdd.clear();
		return sprites;
	}

	@Override
	public String status() {
		return alive ? String.valueOf(score) : "YOU DIE!!!! — Final score: " + score + " – Press any key to continue";
	}

	@Override
	public String background() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRectangle getBoundingBox() {
		return boundingBox;
	}

	@Override
	public void keyDown(int keyCode) {
		if (keyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}

		if (!alive) {
			start();
			return;
		}

		if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_A) {
			player.moveLeft(true);
		} else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_D) {
			player.moveRight(true);
		} else if (keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_S) {
			player.shoot(true);
		}
	}

	@Override
	public void keyUp(int keyCode) {
		if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_A) {
			player.moveLeft(false);
		} else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_D) {
			player.moveRight(false);
		} else if (keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_S) {
			player.shoot(false);
		}
	}

	@Override
	public void add(ISprite sprite) {
		toAdd.add(sprite);
	}

	@Override
	public void remove(ISprite sprite) {
		toRemove.add(sprite);
		if (sprite == player) {
			gameOver();
		}
	}

	public void gameOver() {
		alive = false;
	}
	
	@Override
	public void addScore(int points) {
		score += points;
	}

	public static Random getRandom() {
		return RANDOM;
	}

	@Override
	public void setGUIPanel(IGUIPanel guiPanel) {
		this.guiPanel = guiPanel;
	}
}
