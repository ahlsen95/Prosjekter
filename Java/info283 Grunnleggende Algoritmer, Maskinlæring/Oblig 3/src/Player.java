
import java.util.ArrayList;

/**
 * the Player to participate in a team-select-game
 * 
 * @author Bjørnar Tessem
 *
 */
public abstract class Player {

  /**
   * the list of team members that the player has chosen
   */
  ArrayList<Candidate> chosen = new ArrayList<Candidate>();

  /**
   * the number of compatibility score set for this player
   */
  int compatibilityScoreSet;

  /**
   * The players name
   */
  String name;

  /**
   * Constructor that sets the compatibility set
   * 
   * @param compatibilityScoreSet
   */
  public Player(int compatibilityScoreSet) {
    this.compatibilityScoreSet = compatibilityScoreSet;
  }

  /**
   * Scores the current status for the player
   * 
   * @return the sum of the compatibility scores
   */
  public int score() {
    int sum = 0;
    for (Candidate c : chosen) {
      sum += c.score(compatibilityScoreSet);
    }
    return sum;
  }

  /**
   * 
   * @return a printable representation of the chosen set
   */
  public String chosenToString() {
    StringBuffer result = new StringBuffer();
    for (Candidate c : chosen) {
      result.append(c.name);
      result.append("(" + c.score(compatibilityScoreSet) + ")" + " ");
    }
    return result.toString();
  }

  /**
   * Performs the actual move by modifying game status and the player
   * 
   * @param status
   * @param i
   *            number of candidate chosen
   */
  public void move(GameStatus status, int i) {
    Candidate c = status.remainingCandidates.remove(i);
    this.chosen.add(c);
    status.toMove = status.other;
    status.other = this;
  }

  /**
   * Prints information about a move
   * 
   * @param status
   * @param index
   */
  protected void printMove(GameStatus status, int index) {
    System.out.println(name + " chose " + index + " " + status.remainingCandidates.get(index).name
        + "(" + status.remainingCandidates.get(index).score(compatibilityScoreSet) + ")\n");
  }

  /**
   * an abstract function that decides the move and makes it
   * 
   * @param gameStatus
   */
  public abstract void makeMove(GameStatus gameStatus);

  /**
   * 
   * @return a copy of this player
   */
  public abstract Player copy();

}
