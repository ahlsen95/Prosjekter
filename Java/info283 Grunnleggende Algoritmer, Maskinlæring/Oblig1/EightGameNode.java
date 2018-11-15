/**
 * A representation of the EightGame node
 * @author Bjørnar
 *
 */
public class EightGameNode implements ProblemNode {

  private int[][] board = new int[3][3];
  private int cost = 1;
  int xEmpty;
  int yEmpty;
  private static int[][] defaultBoard = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

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
    initEmpties();
  }

  public EightGameNode(int[][] board, int cost) {
    this(board);
    this.cost = cost;
  }

  private void initEmpties() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == 0) {
          xEmpty = j;
          yEmpty = i;
        } ;
      }
    }
  }

  @Override
  public EightGameNode copy() {
    return new EightGameNode(board, cost);
  }

  public EightGameNode moveLeft() {
    if (xEmpty == 0)
      return null;

    else {
      EightGameNode res = new EightGameNode(board);
      res.board[yEmpty][xEmpty] = res.board[yEmpty][xEmpty - 1];
      res.board[yEmpty][xEmpty - 1] = 0;
      res.xEmpty--;
      // if (xEmpty == 0 && yEmpty == 2) res.setCost(10); else
      res.setCost(1);
      return res;
    }
  }

  public EightGameNode moveRight() {
    if (xEmpty == 2)
      return null;

    else {
      EightGameNode res = new EightGameNode(board);
      res.board[yEmpty][xEmpty] = res.board[yEmpty][xEmpty + 1];
      res.board[yEmpty][xEmpty + 1] = 0;
      res.xEmpty++;
      // if (xEmpty == 0 && yEmpty == 2) res.setCost(10); else
      res.setCost(1);
      // res.setCost(10);
      return res;
    }
  }

  public EightGameNode moveUp() {
    if (yEmpty == 0)
      return null;

    else {
      EightGameNode res = new EightGameNode(board);
      res.board[yEmpty][xEmpty] = res.board[yEmpty - 1][xEmpty];
      res.board[yEmpty - 1][xEmpty] = 0;
      res.yEmpty--;
      // if (xEmpty == 0 && yEmpty == 2) res.setCost(10); else
      res.setCost(1);
      return res;
    }
  }

  public EightGameNode moveDown() {
    if (yEmpty == 2)
      return null;

    else {
      EightGameNode res = new EightGameNode(board);
      res.board[yEmpty][xEmpty] = res.board[yEmpty + 1][xEmpty];
      res.board[yEmpty + 1][xEmpty] = 0;
      res.yEmpty++;
      // if (xEmpty == 0 && yEmpty == 2) res.setCost(10); else
      res.setCost(1);
      return res;
    }
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

  public int hammingDistance(EightGameNode goal) {
    int wrongPlace = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] != goal.board[i][j]) {
          wrongPlace++;
        }
      }
    }
    return wrongPlace - 1;
  }

  public int manhattanDistance(EightGameNode goal) {
    int distance = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int value = board[i][j];
        if (value != 0) {
          for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
              if (goal.board[x][y] == value) {
                distance += Math.abs(i - x) + Math.abs(j - y);

              }
            }
          }
        }
      }
    }
    return distance / 2;
  }

  public int mittEgetEstimat(EightGameNode goal) {
    // Credits to Anders.
    int result = (((manhattanDistance(goal) + hammingDistance(goal) + 1) / 2) % 3);
    return result;
  }

}
