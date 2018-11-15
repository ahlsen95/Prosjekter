
import java.util.ArrayList;

/**
 * A class that represent a Game tree node in a game tree for the select-team-game
 * @author Bjørnar Tessem
 *
 */
public class GameTreeNode {

  /**
   * Is this a MAX node or not
   */
  private boolean MAX;

  /**
   * The game status of the node
   */
  GameStatus gameStatus;

  /**
   * the node's depth in tree
   */
  int depth = 0;

  /**
   * the best move from this node
   */
  int bestMove = -1;

  /**
   * the score of the node
   */
  int score;

  /**
   * the computer player that searches this tree
   */
  ComputerPlayer computerPlayer;

  /**
   * Creates a game tree from the status
   * @param status
   */
  public GameTreeNode(GameStatus status) {
    this.gameStatus = status;
  }

  /**
   * Checks if the node is at max depth or is a final node
   * @return true if the node is a leaf in the game tree
   */
  public boolean leaf() {
    if (this.gameStatus.finished())
      return true;
    if (this.depth >= ComputerPlayer.MAX_DEPTH)
      return true;
    return false;
  }

  /**
   * Scores a leaf.  
   * @return if game is finished it returns the final result
   * 		Otherwise it returns the players evaluation of the game status
   */
  public int scoreLeaf() {
    if (this.gameStatus.finished()) {
      if (this.MAX)
        return this.gameStatus.currentScore();
      else
        return -this.gameStatus.currentScore();
    } else
      return evaluateGameStatus(this.gameStatus);
  }

  /**
   * Evaluates the game status as seen from MAX
   * @param status
   * @return MAX's assessment of the game
   */
  public int evaluateGameStatus(GameStatus status) {

    //
    int val = computerPlayer.evaluateGameStatus(status);
    if (this.MAX) {
      return val;
    } else {
      return -val;
    }
  }

  /**
   * runs an alphabeta search for a best move for MAX
   */
  public void search() {
    this.MAX = true;
    alphaBeta(this, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  /**
   * A traditional alphabeta implementation
   * @param node the node to search from
   * @param alpha the lower limit for MAX's score 
   * @param beta the highest limit for MAX's score
   * @return the minmax score for the tree
   */
  private int alphaBeta(GameTreeNode node, int alpha, int beta) {
    if (node.leaf())
      return node.scoreLeaf();
    ArrayList<GameTreeNode> children = node.generateChildren();
    if (node.MAX) {
      for (int i = 0; i < children.size(); i++) {
        int score = alphaBeta(children.get(i), alpha, beta);
        if (score > alpha) {
          alpha = score;
          node.bestMove = i;
        }
        if (alpha >= beta) {
          score = beta;
          return beta;
        }
      }
      score = alpha;
      return alpha;
    } else {
      for (int i = 0; i < children.size(); i++) {
        int score = alphaBeta(children.get(i), alpha, beta);
        if (score < beta) {
          beta = score;
          node.bestMove = i;
        }
        if (alpha >= beta) {
          score = alpha;
          return alpha;
        }
      }
      score = beta;
      return beta;
    }
  }

  /**
   * generates a list of child nodes for this game tree node
   * @return an ArrayList of GameTreeNodes, each representing a node after one move.
   */
  private ArrayList<GameTreeNode> generateChildren() {
    ArrayList<GameTreeNode> children = new ArrayList<GameTreeNode>();
    for (int i = 0; i < gameStatus.remainingCandidates.size(); i++) {
      // copy the node
      GameStatus newStatus = new GameStatus(gameStatus);

      // test all moves
      newStatus.toMove.move(newStatus, i);

      // make a new node
      GameTreeNode newNode = new GameTreeNode(newStatus);

      // set parameters for the new node
      newNode.computerPlayer = this.computerPlayer;
      newNode.depth = this.depth + 1;
      newNode.MAX = !this.MAX;

      // add the new node to the resulting list
      children.add(newNode);
    }
    return children;
  }

}
