
import java.util.Random;

/**
 * This class implements and runs a greedy algorithm and a simulated annealing
 * algorithm for maximising an objective function for the problem of who to
 * invite to a party given candidates defined in Candidate.java and constraints
 * defined in InvitationProblem.java
 * 
 * @author Bjørnar Tessem
 *
 */
public class InvitationSolver {

  /**
   * A random number generator
   */
  Random ran = new Random();

  /**
   * the "temperature" parameter to the simulated annealing algorithm
   */
  static final double INITIAL_TEMPERATURE = 20.0;

  /**
   * the "cooling" parameter to the simulated annealing algorithm
   */
  static double COOLING_FACTOR = 0.95;

  /**
   * The algorithm that solves the stated problem with a greedy algorithm
   * starting from a random initial assignment
   * 
   * @return a local maximum of the InvitationProblem
   */

  public void setCooling(double verdi) {
    COOLING_FACTOR = verdi;
  }

  public InvitationAssignment greedySolve() {
    InvitationAssignment best = intializeRandom(); // a random initial
                                                   // assignment
    double bestScore = score(best); // compute the objective function
    while (true) { // run the algorithm until no improvement

      // find the best neighbour
      InvitationAssignment bestNeighbour = getBestNeighbour(best);

      // score the best neighbour
      double bestNeighbourScore = score(bestNeighbour);

      if (bestNeighbourScore > bestScore) { // if best neighbour is better
        best = bestNeighbour; // set best to best neighbour
        bestScore = bestNeighbourScore; // remember the best's scpre
      } else
        break; // no improvement --> we have a local maximum
    }
    return best;
  }

  /**
   * The algorithm that solves the stated problem with a simulated
   * annealing algorithm starting from a random initial assignment
   * 
   * @return a local maximum of the InvitationProblem
   */
  public InvitationAssignment simulatedAnnealingSolve() {
    InvitationAssignment best = intializeRandom(); // a random initial assignment
    double bestScore = score(best); // compute the objective function
    double t = INITIAL_TEMPERATURE; // set the start temperature for the annealing
    int noImprovements = 0; // counter to help ending the loop
    while (noImprovements < 20) { // if there are no improvements in
      // the last 20 iterations, stop the algorithm

      // find a random neighbour
      InvitationAssignment randomNeighbour = getRandomNeighbour(best);

      // score the random neighbour
      double randomNeighbourScore = score(randomNeighbour);
      if (randomNeighbourScore >= bestScore) { // if better
        best = randomNeighbour; // set best to the random neighbour
        bestScore = randomNeighbourScore; // remember the score
        if (randomNeighbourScore == bestScore) // if no improvement
          noImprovements++; // add no-improvement-steps
        else
          noImprovements = 0; // reset no-improvement-steps to 0
      } else {
        // the difference between the random and current best
        double diff = bestScore - randomNeighbourScore;

        // find a probability for switching to random
        double p = Math.exp(-diff / t);

        // find a random number in [0,1.0).
        double x = ran.nextDouble();

        if (x < p) { // switch to the worse assignment
          best = randomNeighbour;
          bestScore = randomNeighbourScore;
          noImprovements = 0;
        } else { // increase no-improvement-steps
          noImprovements++;
        }
        t = t * COOLING_FACTOR; // reduce temperature
      }
    }
    return best;
  }

  /**
   * Computes a random neighbour to an InvitationAssignment
   * @param best the changed InvitationAssignment
   * @return a new InvitationAssignment changed for one candidate
   */
  private InvitationAssignment getRandomNeighbour(InvitationAssignment best) {
    int size = best.theAssignment.size(); // the number of candidates

    // choose one random candidate
    int n = ran.nextInt(size);
    Candidate toChange = InvitationProblem.allCandidates[n];

    // make a new assignment (copy)
    InvitationAssignment result = new InvitationAssignment(best);

    // invert status of the random candidate
    result.invert(toChange);

    return result;
  }

  /**
   * Gets the neighbour that scores highest among the neighbours to an assignment 
   * @param best the assignment to find neighbors for
   * @return the best neighbour
   */
  private InvitationAssignment getBestNeighbour(InvitationAssignment best) {

    // initially no assignments
    InvitationAssignment bestNew = null;

    // initialize best score so far
    double bestScore = Double.MIN_VALUE;
    for (Candidate c : best.theAssignment.keySet()) { // for each candidate

      // make a new try assignment and invert invitation status for the particular
      // candidate
      InvitationAssignment tryAssignment = new InvitationAssignment(best);
      tryAssignment.invert(c);

      // score the new try assignment
      double tryScore = score(tryAssignment);

      if (bestNew == null || tryScore > bestScore) {
        // if no assignment so far or the tried assignment is
        // better remember
        bestNew = tryAssignment;
        bestScore = tryScore;
      }
    }
    return bestNew;
  }

  /**
   * Scores an assignment
   * @param assignment the assignment to be scored
   * @return the objective function to be maximized
   */
  public double score(InvitationAssignment assignment) {
    // returns the sum of the individual scores of each candidate
    // and the scores from all the constraints
    double result = individualScores(assignment) + constraintScores(assignment);
    return result;
  }

  /**
   * Computes the individual scores for an assignment
   * @param assignment 
   * @return a sum of the likeabilities
   */
  private double individualScores(InvitationAssignment assignment) {
    double result = 0.0; // initialize sum
    for (Candidate c : assignment.theAssignment.keySet()) {
      if (assignment.invited(c))
        result += c.likeability; // add likeability for invited guests
    }
    return result;
  }

  /**
   * Computes the sum of all the constraints in the Invitationproblem
   * on the particular assignment
   * @param assignment
   * @return the sum of the computed contraints
   */
  private double constraintScores(InvitationAssignment assignment) {
    double result = 0.0; // initialize sum
    for (InvitationConstraint c : InvitationProblem.allConstraints) {

      // add constraint value for all constraints
      result += c.constraint(assignment);
    } ;
    return result;
  }

  /**
   * makes a random assignment of invitations
   * @return the random assignment
   */
  private InvitationAssignment intializeRandom() {
    InvitationAssignment result = new InvitationAssignment();
    for (Candidate c : InvitationProblem.allCandidates) {
      int n = ran.nextInt(2);
      if (n == 0)
        result.set(c, false);
      else
        result.set(c, true);
    }
    return result;
  }

  /**
   * Runs the optimization algorithms
   * @param args
   */
  public static void main(String[] args) {
    InvitationSolver sol = new InvitationSolver();
    double score = 0;
    for (int i = 0; i < 100; i++) {
      InvitationAssignment greedySolution = sol.greedySolve();
      score = score + sol.score(greedySolution);
    }
    System.out.println("GreedyScore = " + score);

    for (int i = 0; i < 3; i++) {
      score = 0.0;

      if (i == 0) {
        sol.setCooling(0.8);
      }
      if (i == 1) {
        sol.setCooling(0.95);
      }
      if (i == 2) {
        sol.setCooling(0.99);
      }


      for (int x = 0; x < 100; x++) {
        InvitationAssignment simAnnealSolution = sol.simulatedAnnealingSolve();
        score += sol.score(simAnnealSolution);
      }
      System.out.println("Herdinger Score: " + score);
    }
  }
}
