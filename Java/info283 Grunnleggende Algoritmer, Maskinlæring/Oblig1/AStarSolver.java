import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 * Defined the importand functions for an A* solver in a problem space search.
 * @author Bjørnar Tessem
 *
 */
public class AStarSolver extends BestFirstSolver {

	public AStarSolver(ProblemSpace g, ProblemNode s) {
		super(g, s);
	}
	
	@Override
	public Collection<Path> initializeFrontier() {
		AStarPath p = new AStarPath();
		p.add(start);
		frontier.add(p);
		return frontier;
	}

	@Override
	public Path selectAndRemove() {
		return (AStarPath)((PriorityQueue<Path>)frontier).remove();
	}
	
	@Override
	public void updateFrontier(Path path) {
		List<Path> moves = space.getAStarMoves((AStarPath)path);
		for (Path m : moves) {
			((PriorityQueue<Path>)frontier).add(m);
		}
	}

}
