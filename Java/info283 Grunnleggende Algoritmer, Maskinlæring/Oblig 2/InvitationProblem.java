import java.util.ArrayList;

/**
 * The specification of an Invitation problem in the shape of all candidates
 * and the constraints 
 * @author Bj¯rnar Tessem
 *
 */
public class InvitationProblem {

  /**
   * the candidates in an array, found in the Candidate enum
   */
  final static Candidate[] allCandidates = Candidate.values();

  /*
   * Since InvitationConstraint is an interface with ONLY ONE METHOD we are using so called lambda
   * definitions of constraints which essentially is to make an object with an anonymous class and
   * lets the program lines in the definition be the content of the single method. The method
   * argument 'assignment' is found in the "(assignment)->"-part of the constraint definition.
   */

  /**
   *  constraint nr 1: best with 12 guests, not so good with lesser or fewer guests
   */
  final static InvitationConstraint constraint1 = (assignment) -> {
    int count = 0;
    for (Candidate c : allCandidates) {
      // count number of invited candidates in this assignment
      if (assignment.invited(c))
        count++;
    }

    // find difference from 12
    int diff = Math.abs(12 - count);

    if (diff == 0)
      return 30.0; // if difference 0, splendid!
    if (diff == 1)
      return 20.0; // otherwise not so good
    if (diff == 2)
      return 10.0;
    if (diff == 3)
      return 5.0;
    return 0.0;
  };

  /**
   * constraint nr 2: best with equal number of men and women
   */
  final static InvitationConstraint constraint2 = (assignment) -> {
    int countMen = 0, countWomen = 0;
    for (Candidate c : allCandidates) {
      // count women and men
      if (assignment.invited(c))
        if (c.woman) {
          countWomen++;
        } else {
          countMen++;
        }
    }
    // find difference between women and men
    int diff = Math.abs(countWomen - countMen);
    if (diff == 0)
      return 20.0; // if equal, magnificent!
    if (diff == 1)
      return 15.0; // otherwise not so good
    if (diff == 2)
      return 10.0;
    if (diff == 3)
      return 5.0;
    return 0.0;
  };

  /**
   * constraint no 3: Anne dislikes Ola
   */
  final static InvitationConstraint constraint3 = (assignment) -> {
    // if both invited that is bad
    if (assignment.invited(Candidate.anne) && assignment.invited(Candidate.ola))
      return -5.0;
    // otherwise ok
    else
      return 0.0;
  };

  // constraint no 4: Rune, Helge, Ivar and Lars makes bad music choices if three of them present
  final static InvitationConstraint constraint4 = (assignment) -> {
    int count = 0;

    // count how many of these are invited
    count += assignment.invited(Candidate.ivar) ? 1 : 0;
    /*
     * Shorthand for: if assignment.invited(Candidate.ivar) { count = count+1; } else count = count
     * + 0;
     */
    count += assignment.invited(Candidate.lars) ? 1 : 0;
    count += assignment.invited(Candidate.rune) ? 1 : 0;
    count += assignment.invited(Candidate.helge) ? 1 : 0;

    // if 3 or 4 that is bad
    if (count >= 3)
      return -6.0;
    // otherwise ok
    else
      return 0.0;
  };
  final static InvitationConstraint constraint5 = (assignment) -> {
    // if both invited that is good
    if (assignment.invited(Candidate.sofie) && assignment.invited(Candidate.tom))
      return 5.0;
    // otherwise bad
    else
      return -5.0;
  };

  final static InvitationConstraint constraint6 = (assignment) -> {
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> abc = new ArrayList<String>();
    abc.add("ABCDEFGHIJKLMNOPQRSTUVWXYZ∆ÿ≈");
    double sum = 0.0;
    for (Candidate c : allCandidates) {
      if (assignment.invited(c))
        names.add(c.name);
    }
    for (int i = 0; i < 29; i++) {
      int teller = 0;
      char bokstav = abc.get(0).charAt(i);
      for (int j = 0; j < names.size(); j++) {
        if (bokstav == names.get(j).charAt(0)) {
          teller++;
          if (teller > 1) {
            sum = sum - 2.0;
          }
        }
      }
    }
    return sum;
  };

  /**
   * an array with all the constraint objects
   */
  final static InvitationConstraint[] allConstraints =
      {constraint1, constraint2, constraint3, constraint4, constraint5, constraint6}; // TODO Add
                                                                                      // constraints
                                                                                      // here after
                                                                                      // you
  // made function for it

}

