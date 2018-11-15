

import java.util.Scanner;

/**
 * A representation of ahuman player in the select-team-game
 * @author Bjørnar Tessem
 *
 */
public class HumanPlayer extends Player {

  /**
   * Initiates a human player
   * @param computabilityScoreSet
   */
  public HumanPlayer(int computabilityScoreSet) {
    super(computabilityScoreSet);
    name = "You";
  }

  /**
   * Handles the user interaction for a human player who selects a team
   */
  @Override
  public void makeMove(GameStatus status) {
    Scanner in = new Scanner(System.in);
    boolean accepted = false;
    // no candidate chosen yet so index = -1
    int index = -1;
    do {
      System.out.println("Choose move (integer) > ");
      // value is the user input
      String value = in.nextLine();
      try {
        // value needs to be integer
        index = Integer.parseInt(value);
      } catch (NumberFormatException e) {
        System.out.print(value + " Not a number. ");
      }
      // if index in accepted interval move is chosen
      if (index >= 0 && index < status.remainingCandidates.size())
        accepted = true;
      else {
        System.out.println(value + " Illegal move!");
      }
    } while (!accepted);
    printMove(status, index);
    // do the actual move
    move(status, index);

  }


  /**
   * Creates a new Human player as a copy of this
   */
  @Override
  public Player copy() {
    Player result = new HumanPlayer(compatibilityScoreSet);
    result.chosen.addAll(this.chosen);
    return result;
  }

}
