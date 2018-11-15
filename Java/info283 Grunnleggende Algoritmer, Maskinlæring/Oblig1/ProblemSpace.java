import java.util.List;
/**
 * An interface that defines essential functions for a problem space
 * @author Bj�rnar
 *
 */
public interface ProblemSpace {

	boolean goal(ProblemNode last);

	List<Path> getMoves(Path path);

	List<Path> getAStarMoves(AStarPath path);

}
