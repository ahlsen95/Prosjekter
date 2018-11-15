package info283.search;
/**
 * An interface that defines essential functions for a problem node
 * @author Bj�rnar
 *
 */
public interface ProblemNode {

	ProblemNode copy();

	int getCost();

}
