import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * A representation of the EightGame problem space
 * @author Bjørnar Tessem
 *
 */
public class EightGameSpace implements ProblemSpace {

  private int[][] goalPos = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
  private EightGameNode goal = new EightGameNode(goalPos);
  HashSet<EightGameNode> visited = new HashSet<EightGameNode>();
  private boolean checkVisited = true;

  @Override
  public boolean goal(ProblemNode last) {
    if (last instanceof EightGameNode) {
      if (goal.equals((EightGameNode) last))
        return true;
      else
        return false;
    } else
      return false;
  }

  @Override
  public List<Path> getMoves(Path path) {
    List<Path> result = new ArrayList<Path>();
    EightGameNode last = (EightGameNode) path.getLast();
    if (!isVisited(last)) {
      addMove(path, result, last.moveLeft());
      addMove(path, result, last.moveRight());
      addMove(path, result, last.moveUp());
      addMove(path, result, last.moveDown());
      if (checkVisited)
        visited.add(last);
    }
    return result;
  }



  private void addMove(Path path, List<Path> result, EightGameNode node) {

    if (node != null
        && ((checkVisited && !isVisited(node)) || (!checkVisited && !path.contains(node)))) {
      Path newPath = path.augment(node);
      result.add(newPath);
    }
  }



  private boolean isVisited(EightGameNode node) {
    if (visited.contains(node))
      return true;
    return false;
  }

  public int getVisitedCount() {
    return visited.size();
  }

  public void doNotCheckVisited() {
    checkVisited = false;
  }

  public List<Path> getAStarMoves(AStarPath path) {
    List<Path> result = new ArrayList<Path>();
    EightGameNode last = (EightGameNode) path.getLast();
    // System.out.println("Expanding \n"+last);
    if (!isVisited(last)) {
      addAStarMove(path, result, last.moveLeft());
      addAStarMove(path, result, last.moveRight());
      addAStarMove(path, result, last.moveUp());
      addAStarMove(path, result, last.moveDown());
      if (checkVisited)
        visited.add(last);
    }
    return result;
  }

  private void addAStarMove(AStarPath path, List<Path> result, EightGameNode node) {
    if (node != null && !isVisited(node) && !path.contains(node)) {
      AStarPath newPath = (AStarPath) path.augment(node);
      int h = h(node);
      System.out.println("h-verdi = " + h);
      newPath.setHValue(h);
      result.add(newPath);
    }
  }

  private int h(EightGameNode node) {
    // return 0;
    // return node.mittEgetEstimat(goal);
    return node.manhattanDistance(goal);
    // return node.hammingDistance(goal);
  }

}
