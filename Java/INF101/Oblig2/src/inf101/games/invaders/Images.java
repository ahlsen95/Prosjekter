package inf101.games.invaders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Images {
	public static final IImage bullet1 = new BulletImage(0*8);
	public static final IImage bullet2 = new BulletImage(1*8);
	public static final IImage bullet3 = new BulletImage(2*8);
	public static final IImage bullet4 = new BulletImage(3*8);
	public static final List<IImage> bullets = Collections.unmodifiableList(Arrays.asList(bullet1, bullet2, bullet3, bullet4));

	public static final IImage block = new GenericImage("block.png", 8, 8);

	public static final IImage alien1 = new AlienImage(0*39);
	public static final IImage alien2 = new AlienImage(1*39);
	public static final IImage alien3 = new AlienImage(2*39);
	public static final IImage alien4 = new AlienImage(3*39);
	public static final IImage alien5 = new AlienImage(4*39);
	public static final IImage alien6 = new AlienImage(5*39);
	public static final IImage alien7 = new AlienImage(6*39);
	public static final IImage alien8 = new AlienImage(7*39);
	public static final IImage alien9 = new AlienImage(8*39);
	public static final IImage alien10 = new AlienImage(9*39);
	public static final IImage alien11 = new AlienImage(10*39);
	public static final IImage alien12 = new AlienImage(11*39);
	public static final IImage ship = new AlienImage(12*39);
	public static final List<IImage> aliens = Collections.unmodifiableList(Arrays.asList(alien1, alien2, alien3, alien4, alien5, alien6, alien7, alien8, alien9, alien10, alien11, alien12));

	static class GenericImage implements IImage {
		private final IRectangle bBox;
		private final String file;

		public GenericImage(String string, int w, int h) {
			bBox = new Rectangle(0, 0, w, h);
			file = string;
		}

		@Override
		public String getFileName() {
			return file;
		}

		@Override
		public IRectangle getSourceBox() {
			return bBox;
		}

		@Override
		public IRectangle getBoundingBox() {
			return bBox;
		}
		
	}
	static class AlienImage implements IImage {
		
		private IRectangle srcBox;
		private IRectangle bBox;
		
		AlienImage(int x) {
			srcBox = new Rectangle(x+2, 4, 37, 35);    
			bBox = new Rectangle(2, 4, 37, 35);
		}
		@Override
		public String getFileName() {
			return "aliensprite.png";
		}

		@Override
		public IRectangle getSourceBox() {
			return srcBox;
		}
		@Override
		public IRectangle getBoundingBox() {
			return bBox;
		}
		
	}
	
	static class BulletImage implements IImage {
		
		private IRectangle srcBox;
		private IRectangle bBox;
		
		BulletImage(int x) {
			srcBox = new Rectangle(x, 0, 8, 18);    
			bBox = new Rectangle(0, 0, 8, 18);
		}
		@Override
		public String getFileName() {
			return "bullet.png";
		}

		@Override
		public IRectangle getSourceBox() {
			return srcBox;
		}
		@Override
		public IRectangle getBoundingBox() {
			return bBox;
		}
		
	}

}
