
public class MyComputerPlayer extends ComputerPlayer {

  /**
   * An initialisation of a MyComputerPlayer
   * @param compatibilityScoreSet
   */
  public MyComputerPlayer(int compatibilityScoreSet) {
    super(compatibilityScoreSet);
    name = "My";
  }

  /**
   * The My evaluation function
   */
  @Override
  public int evaluateGameStatus(GameStatus status) {
    int result = status.toMove.score() - status.other.score();

    int me = 0;

    for (Candidate c : status.remainingCandidates) {

      me += c.score(status.toMove.compatibilityScoreSet);
    }
    result += me;

    return result;
  }

  /**
   * Creates a new MyComputerPlayer as a copy of this
   */
  @Override
  public Player copy() {
    Player result = new MyComputerPlayer(compatibilityScoreSet);
    result.chosen.addAll(this.chosen);
    return result;
  }

}


