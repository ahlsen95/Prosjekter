
public class Tile {
	private int value;
	private int graphVertex = -1;
	private Tile north;
	private Tile south;
	private Tile west;
	private Tile east;
	private boolean visited;
	
	
	public int getGraphVertex() {
		return graphVertex;
	}

	public void setGraphVertex(int graphVertex) {
		this.graphVertex = graphVertex;
	}
	public void setVisited(boolean visited){
		this.visited = visited;
	}
	public boolean getVisited(){
		return visited;
	}
	public Tile(int value) {
		if (value == 0 || value == 1) this.value = value;
	}

	public Tile getNorth() {
		return north;
	}

	public void setNorth(Tile north) {
		this.north = north;
	}

	public Tile getSouth() {
		return south;
	}

	public void setSouth(Tile south) {
		this.south = south;
	}

	public Tile getWest() {
		return west;
	}

	public void setWest(Tile west) {
		this.west = west;
	}

	public Tile getEast() {
		return east;
	}

	public void setEast(Tile east) {
		this.east = east;
	}

	public int getValue() {
		return value;
	}
	
	public void connectWest(Tile tile) {
		setWest(tile);
		tile.setEast(this);
	}
	
	public void connectEast(Tile tile) {
		setEast(tile);
		tile.setWest(this);
	}
	
	public void connectNorth(Tile tile) {
		setNorth(tile);
		tile.setSouth(this);
	}
	
	public void connectSouth(Tile tile) {
		setSouth(tile);
		tile.setNorth(this);
	}
}
