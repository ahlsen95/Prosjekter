import java.util.Collection;

/**
 * 
 * @author Bjørnar Tessem
 * 
 *         ProblemSolver is an abstract class that defines a generic search
 *         algorithm for classical AI problem solving.
 * 
 */
public abstract class ProblemSolver {

  /**
   * A representation of the problem space
   */
  protected ProblemSpace space;
  /**
   * A representation of the starting position
   */
  protected ProblemNode start;
  /**
   * A collection that maintains all the frontier paths to be investigated
   */
  protected Collection<Path> frontier;

  /**
   * Standard constructor
   */
  public ProblemSolver(ProblemSpace space, ProblemNode start) {
    this.space = space;
    this.start = start;
  }

  /**
   * the generic search algorithm
   * 
   * @return a path that is a solution to the search problem
   */
  private Path search() {
    frontier = initializeFrontier();
    while (!frontier.isEmpty()) {
      Path path = selectAndRemove();
      if (space.goal(path.getLast())) {
        return path;
      }
      updateFrontier(path);
    }
    return null;
  }

  /**
   * The first frontier object
   * 
   * @return the frontier to be investigated
   */
  public abstract Collection<Path> initializeFrontier();

  /**
   * selects and removes a path to be checked from the frontier
   * 
   * @return a path
   */
  public abstract Path selectAndRemove();

  /**
   * updates the frontier with new paths coming from adding children of path
   * 
   * @param path
   *            the path to be developed
   */
  public abstract void updateFrontier(Path path);

  /**
   * standard main function to test the use of an EightGame problem space and
   * Depth-, Breadth-, and Best-first-search-strategies
   * 
   * @param args
   */
  public static void main(String[] args) {
    // int[][] pos = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
    // int[][] pos = {{1, 2, 3}, {4, 0, 6}, {7, 5, 8}};
    // int[][] pos = {{1, 2, 3}, {4, 5, 6}, {0, 7, 8}};
    int[][] pos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
    ProblemNode s;
    try {
      s = new EightGameNode(pos);
      ((EightGameNode) s).setCost(0); // startnode has cost 0
      ProblemSpace g = new EightGameSpace();
      // ProblemSolver solver = new DepthFirstSolver(g, s);
      // ProblemSolver solver = new BreadthFirstSolver(g, s);
      ProblemSolver solver = new AStarSolver(g, s);
      Path solution = solver.search();
      if (solution != null) {
        solution.print();
        System.out.println("Kostnad: " + solution.getCost());
        System.out.println("Gjennomgåtte nodar: " + ((EightGameSpace) g).getVisitedCount());
      } else {
        System.out.println("Problemet har inga løysing.");
      }
    } catch (Exception e) {
      System.out.println("Feil i data til ProblemSolver");
      e.printStackTrace();
    }
  }

}
