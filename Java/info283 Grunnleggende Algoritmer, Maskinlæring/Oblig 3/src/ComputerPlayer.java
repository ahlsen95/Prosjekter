
/**
 * Representation of a computer player in a team-select-game
 * @author Bjørnar Tessem
 *
 */
public abstract class ComputerPlayer extends Player {

  /**
   * constant that sets the depth of a game search
   */
  public static final int MAX_DEPTH = 5;

  /**
   * Initiates a computer player
   * @param compuatibilityScoreSet
   */
  public ComputerPlayer(int compatibilityScoreSet) {
    super(compatibilityScoreSet);
    name = "Comp";
  }

  /**
   * The computer players move
   */
  @Override
  public void makeMove(GameStatus status) {
    int selectedCandidate = selectCandidate(status);
    move(status, selectedCandidate);
  }

  /**
   * The computer's algorithm for selecting a team member
   * @param status
   * @return the index of the candidate
   */
  private int selectCandidate(GameStatus status) {
    // make a game tree
    GameTreeNode root = new GameTreeNode(status);
    root.computerPlayer = this;

    // run search
    root.search();

    printMove(status, root.bestMove);
    System.out.println("Best move score: " + root.score + "\n");
    // return the best move found in root
    return root.bestMove;
  }

  /**
   * a computer player needs a function to evaluate the status of an unfinished game
   * @param status
   * @return
   */
  abstract public int evaluateGameStatus(GameStatus status);

}
