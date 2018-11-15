import java.util.Collection;
import java.util.List;
import java.util.Stack;

/**
 * a depth first implementation of the general solver
 * using a stack to represent the frontier
 * @author Bjørnar
 *
 */
public class DepthFirstSolver extends ProblemSolver {

	public DepthFirstSolver(ProblemSpace g, ProblemNode s) {
		super(g,s);
		frontier = new Stack<Path>();
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
		return ((Stack<Path>)frontier).pop();
	}

	@Override
	public void updateFrontier(Path path) {
		List<Path> moves = space.getMoves(path);
		for (Path m : moves) {
			((Stack<Path>)frontier).push(m);
		}
	}

}
