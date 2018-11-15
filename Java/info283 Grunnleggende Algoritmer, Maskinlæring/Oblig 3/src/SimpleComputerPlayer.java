
/**
 * The representation of a computer player in the team-select-game
 * Uses only the difference of scores as a heuristic
 * @author Bjørnar Tessem
 *
 */
public class SimpleComputerPlayer extends ComputerPlayer {

  /**
   * An initialisation of a SimpleComputerPlayer
   * @param compatibilityScoreSet
   */
  public SimpleComputerPlayer(int compatibilityScoreSet) {
    super(compatibilityScoreSet);
    name = "Simple";
  }

  /**
   * The simple evaluation function
   */
  @Override
  public int evaluateGameStatus(GameStatus status) {
    return status.toMove.score() - status.other.score();
  }

  /**
   * Creates a new SimpleComputerPlayer as a copy of this
   */
  @Override
  public Player copy() {
    Player result = new SimpleComputerPlayer(compatibilityScoreSet);
    result.chosen.addAll(this.chosen);
    return result;
  }

}
