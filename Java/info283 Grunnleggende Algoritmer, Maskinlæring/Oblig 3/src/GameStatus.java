
import java.util.ArrayList;

/**
 * the representation of the select-team-game
 * @author Bjørnar Tessem
 *
 */
public class GameStatus {

  /** 
   * the Player to move
   */
  Player toMove;

  /**
   * the Player who just chose
   */
  Player other;

  /**
   * a list of candidates not yet selected
   */
  ArrayList<Candidate> remainingCandidates = new ArrayList<Candidate>();

  /**
   * initialization of the game status by adding all candidates to remaining candidates
   */
  public GameStatus() {
    remainingCandidates.addAll(Candidate.allCandidates);
  }

  /**
   * Creates a copy of a game status. Used for game search
   * @param gameStatus
   */
  public GameStatus(GameStatus gameStatus) {
    this.toMove = gameStatus.toMove.copy();
    this.other = gameStatus.other.copy();
    remainingCandidates.addAll(gameStatus.remainingCandidates);
  }

  /**
   * 
   * @return true if game is finished, false otherwise
   */
  public boolean finished() {
    return remainingCandidates.isEmpty();
  }

  /**
   *
   * @return the current difference between the player to move and the other
   */
  public int currentScore() {
    return toMove.score() - other.score();
  }

  /**
   * A printable string representation of the remaining players
   * @return
   */
  public String remainingToString() {
    StringBuffer result = new StringBuffer();
    // Print header
    result.append("Remaining players to choose from\n");
    result.append("      \t\tCompatibility\n");
    // print the pplayers' name
    result.append("No\tCand.\t" + toMove.name + "\t" + other.name + "\n");
    int toMoveSum = 0;
    int otherSum = 0;
    for (int i = 0; i < remainingCandidates.size(); i++) {
      Candidate c = remainingCandidates.get(i);
      int toMoveComp, otherComp;
      if (toMove.compatibilityScoreSet == 1) {
        toMoveComp = c.compatibility1;
        otherComp = c.compatibility2;
      } else {
        otherComp = c.compatibility1;
        toMoveComp = c.compatibility2;
      }
      // print the candidate with number in list and compatibilities
      result.append(i + "\t" + c.name + "\t" + toMoveComp + "\t" + otherComp + "\n");
      toMoveSum += toMoveComp;
      otherSum += otherComp;
    }
    // append the sum compatibilities
    result.append("\tSum\t" + toMoveSum + "\t" + otherSum);
    return result.toString();
  }

  /**
   * Performs a move by selecting a candidate 
   * @param i the index of the selected candidate
   */
  public void move(int i) {
    toMove.move(this, i);
  }

}
