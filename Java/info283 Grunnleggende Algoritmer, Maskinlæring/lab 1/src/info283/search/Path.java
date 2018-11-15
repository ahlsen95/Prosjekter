package info283.search;

import java.util.ArrayList;

/**
 * A class tha represent a path in a problem space
 * @author Bjørnar
 *
 */
public class Path implements Comparable<Path>{
	

	/**
	 * 
	 * the local representation of the path
	 */
	private ProblemNode theLast;
	private Path pathToHere;
	
	/**
	 * an initial cost for the path
	 */
	private int cost = 0;

	/**
	 * How to print the path
	 */
	public void print() {
		if (pathToHere == null) {
			// do nothing
		} else {
			pathToHere.print();
		}
		System.out.println(theLast);
	}

	/**
	 * returns the last node in the path
	 * @return a problem node
	 */
	public ProblemNode getLast() {
		return theLast;
	}
	
	/**
	 * puts a problem node at the end of the path
	 * @param p a problem node
	 */
	public void add(ProblemNode p) {
		theLast = p;
		cost += p.getCost();
	}
	
	/**
	 * @return the cost of the path
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * creates a new path as a copy of a previous one
	 * @return a path
	 */
	public Path augment(ProblemNode p) {
		Path copy = new Path();
		copy.theLast = p;
		copy.pathToHere = this;
		copy.cost = this.cost + p.getCost();
		return copy;
	}

	@Override
	/**
	 * compares the cost of two paths
	 */
	public int compareTo(Path other) {
		return getCost() - other.getCost();
	}

}
