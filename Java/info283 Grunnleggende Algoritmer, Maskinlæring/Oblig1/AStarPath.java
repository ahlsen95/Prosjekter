/**
 * 
 * A class that stores paths in A* search and computes the values needed to order A* star paths.
 * @author Bjørnar Tessem
 *
 */
public class AStarPath extends Path {
	
	private int hValue;

	/**
	 * compares the cost of two A star paths
	 */
	public int compareTo(Path other) {
		if (other instanceof AStarPath) {
			return this.f()-((AStarPath)other).f();
		}
		else return Integer.MAX_VALUE;
	}

	/*
	 * The total estimated cost for the path
	 */
	private int f() {
		return getCost() + hValue;
	}

	/*
	 * Sets the h value for the path based on the last node's estimated h-value
	 */
	public void setHValue(int h) {
		hValue = h;
	}
	
	/**
	 * creates a new path as a copy of a previous one
	 * @return a path
	 */
	public Path augment(ProblemNode p) {
		Path copy = new AStarPath();
		copy.theLast = p;
		copy.pathToHere = this;
		copy.cost = this.cost + p.getCost();
		return copy;
	}

}
