package info283.search;

/**
 * A representation of the EightGame node
 * @author Bj�rnar
 *
 */
public class EightGameNode implements ProblemNode {

  // Variable representing the board, an array of arrays where the latter are the rows
  private int[][] board = new int[3][3];
  private int cost = 1;
  private static int[][] defaultBoard = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

  /**
   * This corresponds to the board
   * 1	2	3
   * 4	5	6
   * 7	8	0
   *
   */

  public EightGameNode(int[][] board) {
    if (board.length == 3 && board[0].length == 3 && board[1].length == 3 && board[2].length == 3) {

      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          this.board[i][j] = board[i][j];
        }
      }
    } else {
      System.out.println("Feil i start-brett");
      this.board = defaultBoard;
    }
  }

  public EightGameNode(int[][] board, int cost) {
    this(board);
    this.cost = cost;
  }

  @Override
  public EightGameNode copy() {
    return new EightGameNode(board, cost);
  }

  public EightGameNode moveLeft() {
    // TODO
    // Move the empty field left
    EightGameNode result = new EightGameNode(this.board);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (result.board[i][j] == 0 && j != 0) {
          result.board[i][j] = result.board[i][j - 1];
          result.board[i][j - 1] = 0;

          return result;
        }
      }
    }

    return null;
  }

  public EightGameNode moveRight() {
    // TODO
    // Move the empty field right
    EightGameNode result = new EightGameNode(this.board);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (result.board[i][j] == 0 && j != 2) {
          result.board[i][j] = result.board[i][j + 1];
          result.board[i][j + 1] = 0;

          return result;
        }
      }
    }

    return null;
  }

  public EightGameNode moveUp() {
    // TODO
    // Move the empty field up
    EightGameNode result = new EightGameNode(this.board);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (result.board[i][j] == 0 && i != 0) {
          result.board[i][j] = result.board[i - 1][j];
          result.board[i - 1][j] = 0;

          return result;
        }
      }
    }

    return null;
  }

  public EightGameNode moveDown() {
    // TODO
    // Move the empty field down
    EightGameNode result = new EightGameNode(this.board);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (result.board[i][j] == 0 && i != 2) {
          result.board[i][j] = result.board[i + 1][j];
          result.board[i + 1][j] = 0;
          return result;
        }
      }
    }

    return null;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof EightGameNode))
      return false;
    else {
      EightGameNode node = (EightGameNode) obj;
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (this.board[i][j] != node.board[i][j])
            return false;
        }
      }
      return true;
    }
  }

  public void print() {

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        System.out.print(board[i][j] == 0 ? " " : board[i][j]);
      }
      System.out.println();
    }
  }

  public String toString() {
    StringBuffer result = new StringBuffer();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        result.append(board[i][j] == 0 ? " " : board[i][j]);
      }
      result.append("\n");
    }
    return result.toString();
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  @Override
  public int hashCode() {
    return board[0][0] * 1 + board[0][1] * 2 + board[0][2] * 3 + board[1][0] * 4 + board[1][1] * 5
        + board[1][2] * 6 + board[2][0] * 7 + board[2][1] * 8 + board[2][2] * 9;
  }

  public static void main(String[] args) {
    int[][] pos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
    EightGameNode s = new EightGameNode(pos);
    s.print();
    System.out.println("Move Down");
    s = s.moveDown();
    s.print();
    System.out.println("Move Up");
    s = s.moveUp();
    s.print();
    System.out.println("Move Right");
    s = s.moveRight();
    s.print();
    System.out.println("Move Left");
    s = s.moveLeft();
    s.print();
    System.out.println();
  }

}
