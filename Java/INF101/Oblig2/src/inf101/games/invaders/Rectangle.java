package inf101.games.invaders;

/**
 * @author Eirik
 *
 */

public class Rectangle implements IRectangle {

	 final int width;
	 final int height;
	 final IPosition position;
	
	public Rectangle(int x, int y, int width, int height){
		if(height >= 0  && width >= 0){
			this.width = width;
			this.height = height;
			this.position = new Position(x,y);
		}else{
			throw new IllegalArgumentException("Hoyde og bredde maa vaere storre eller lik null.");
		}

	}
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public IPosition getPosition(){
		return position ;
	}

	@Override
	public boolean contains(IPosition pos) {
		int x = pos.getX();
		int y = pos.getY();
		if(x >= this.getX() && x <= this.getX() + width){
			if(y >= this.getY() && y <= this.getY() + height){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean contains(IRectangle other) {
        IPosition pos = other.getPosition();
        
        if (pos.getX() < this.getX()) {
            return false;
        }
        if (pos.getY() < this.getY()) {
            return false;
        }
        if (pos.getX() + other.getWidth() > this.getX() + this.getWidth()) {
            return false;
 
        }
        if (pos.getY() + other.getHeight() > this.getY() + this.getHeight()) {
            return false;
        }
 
        return true;
    }
	@Override
	public int getX() {
	return position.getX();
	}
	@Override
	public int getY() {
		return position.getY();
	}
	@Override
	public IRectangle move(int deltaX, int deltaY) {

		return new Rectangle(deltaX + getX(), deltaY + getY(), this.width, this.height);
	}
	@Override
	public IRectangle moveTo(int newX, int newY) {

		return new Rectangle(newX, newY, this.width, this.height);
	}
	@Override
	public boolean overlaps(IRectangle rect) {
		int x = position.getX();
		int y = position.getY();
		if(x < rect.getX() + rect.getWidth() && x + width > rect.getX() && 
		y < rect.getY() + rect.getHeight() && y + height > rect.getY()){
			return true;
		}
		return false;
	}
	 
}
