package inf101.games.invaders.sprites;
import java.util.Random;
import inf101.games.invaders.IGame;
import inf101.games.invaders.IImage;

public class Alien extends AbstractShip implements ISprite {
	final int speed = 1;
	int dir = 1;
	
	public Alien(IImage image, int x, int y) {	
		super(image,x, y);
	}
	
	public void step(IGame game){
		//Generer tilfeldig tall så Aliens kan skyte en gang i blandt.
		Random r = new Random();
		int rand = r.nextInt(5000);
		if(rand < 1)
			fire(game);
		
		super.step(game); 
		if (game.getBoundingBox().contains(box)){ //Bevegelse, dir bestemmer om Aliens skal gå venstre eller hoyre.
				box = box.move(speed*dir, 0);
		}else{
			dir = dir * -1;
			box = box.move(speed*dir,box.getHeight());
			
			}
	}
	@Override	
	public void hit(ISprite other, IGame game) {
		game.remove(this);
		game.addScore(1);
		
	}
	
	public boolean isAlien() {
		return true;
	}
	

}
