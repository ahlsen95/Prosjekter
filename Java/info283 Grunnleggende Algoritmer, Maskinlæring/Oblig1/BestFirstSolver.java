import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A best first version of the general problem solver
 * using a priority queue to represent the frontier
 * @author Bjørnar
 *
 */
public class BestFirstSolver extends ProblemSolver {

	public BestFirstSolver(ProblemSpace g, ProblemNode s) {
		super(g,s);
		frontier = new PriorityQueue<Path>();
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
		return ((PriorityQueue<Path>)frontier).remove();
	}

	@Override
	public void updateFrontier(Path path) {
		List<Path> moves = space.getMoves(path);
		for (Path m : moves) {
			((PriorityQueue<Path>)frontier).add(m);
		}
	}

}
