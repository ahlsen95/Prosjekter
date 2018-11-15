package info283.search;

import java.util.List;
/**
 * An interface that defines essential functions for a problem space
 * @author Bjørnar
 *
 */
public interface ProblemSpace {

	boolean goal(ProblemNode last);

	List<Path> getMoves(Path path);

}
