package info283.search;

import java.util.Collection;
import java.util.List;
import java.util.LinkedList;

/**
 * A beadthe first solver implementation of the general problem solver
 * using a Queue, as implemented in a LinkedList to represent frontier
 * @author Bjørnar
 *
 */
public class BreadthFirstSolver extends ProblemSolver {

	public BreadthFirstSolver(ProblemSpace g, ProblemNode s) {
		super(g,s);
		frontier = new LinkedList<Path>();
	}

	@Override
	public Collection<Path> initializeFrontier() {
		Path p = new Path();
		p.add(start);
		frontier.add(p);
		return frontier;
	}

	@Override
	public Path selectAndRemove() {
		return ((LinkedList<Path>)frontier).remove();
	}

	@Override
	public void updateFrontier(Path path) {
		List<Path> moves = space.getMoves(path);
		for (Path m : moves) {
			((LinkedList<Path>)frontier).addLast(m);
		}
	}

}
