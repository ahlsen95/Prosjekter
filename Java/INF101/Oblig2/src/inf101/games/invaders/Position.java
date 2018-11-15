package inf101.games.invaders;

public class Position implements IPosition {
	private final int x;
	private final int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public IPosition move(int deltaX, int deltaY) {
		return new Position(x + deltaX, y + deltaY);
	}

}
