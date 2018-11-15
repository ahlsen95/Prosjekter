import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a game that allows the player to select a team for a particular problem solving situation which
 * again has an element of competition.
 * The team candidates have different compatibility for the two team leaders (who are the players of the game), 
 * and each move adds the compatibility of the selected candidate to the team leaders total sum. 
 * The compatibility is a number from 1..9 and the sum is 100 for the 22 candidates to select from.
 * The goal of the game is to get as high difference in sum for the two team leaders as this increases
 * the probability for a team leader to solve the problem solving situation better than the opponent leader.
 * So playing it may not only be to optimize your own sum, but also minimize your opponents sum.
 * With 22 candidates the number of games is 1,124,000,727,777,610,000,000
 * 
 * @author Bjørnar Tessem
 *
 */
public class SelectTeam {

  /**
   * the human player
   */
  Player human = null;

  /**
   * a computer player
   */
  Player computer = null;

  /**
   * the current status of the game
   */
  GameStatus gameStatus = new GameStatus();

  /**
   * a representation of the text based user interface
   */
  Scanner in;

  /**
   * initiates the game
   */
  public SelectTeam() {
    in = new Scanner(System.in);
  }

  /**
   * allows the human player to choose who to start the game
   */
  public void selectStarter(Boolean valg) {
    // String answer = null;
    do {
      // System.out.print("Do you want to start or not (Y/N)? ");
      // answer = in.nextLine();
      if (valg) {
        // human player starts
        // and will use compatibility set 1
        human = new MyComputerPlayer(1);
        computer = new SimpleComputerPlayer(2);
        gameStatus.toMove = human;
        gameStatus.other = computer;
      } else if (!valg) {
        // computer player starts
        // and will use compatibility set 1
        human = new MyComputerPlayer(2);
        computer = new SimpleComputerPlayer(1);
        gameStatus.toMove = computer;
        gameStatus.other = human;
      }
    } while (gameStatus.toMove == null);
  }

  /**
   * The main function
   * @param args
   */
  public static void main(String[] args) {
    Boolean valg = true;


    for (int i = 0; i < 100; i++) {
      Candidate.allCandidates = new ArrayList<>();
      Candidate.randomAssignment();
      SelectTeam game = new SelectTeam();
      game.selectStarter(valg);
      while (!game.finished()) {
        // game.printStatus();
        game.selectMove();
      }
      game.printResult();
      if (i % 2 == 0)
        valg = true;
      else
        valg = false;
    }

  }


  /**
   * Prints the final result of the game
   */
  private void printResult() {
    if (human.score() > computer.score()) {
      System.out.println(human.name.toUpperCase() + " WON! ");
    } else if (computer.score() > human.score()) {
      System.out.println(computer.name.toUpperCase() + " WON!");
    } else {
      System.out.println("DRAW");
    }
    System.out.print(human.name + " chose: ");
    System.out.println(human.chosenToString());
    System.out.println("Score: " + human.score());

    System.out.println();
    System.out.print(computer.name + " chose: ");
    System.out.println(computer.chosenToString());
    System.out.println("Score: " + computer.score());
  }

  /**
   * the player to move selects a move
   */
  private void selectMove() {
    gameStatus.toMove.makeMove(gameStatus);
  }

  /**
   * prints the current status of the game
   */
  private void printStatus() {
    System.out.println("To move: " + ((gameStatus.toMove == human) ? human.name : computer.name));
    System.out.print("Selected:");
    System.out.println(gameStatus.toMove.chosenToString());
    System.out.println("Score: " + gameStatus.toMove.score());
    System.out.println();
    System.out.println("Other: " + ((gameStatus.other == human) ? human.name : computer.name));
    System.out.print("Selected:");
    System.out.println(gameStatus.other.chosenToString());
    System.out.println("Score: " + gameStatus.other.score());
    System.out.println();
    System.out.println(gameStatus.remainingToString());
  }

  /**
   * @return true if game is finished
   */
  private boolean finished() {
    return gameStatus.finished();
  }

}
