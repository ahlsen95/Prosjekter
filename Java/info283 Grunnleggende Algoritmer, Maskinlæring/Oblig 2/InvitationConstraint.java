
/**
 * An interface to define the methods of a constraint in the InvitationProblem
 * @author Bjørnar Tessem
 *
 */
public interface InvitationConstraint {

  /**
   * The method that computes the added score of a constraint
   * as it applies to an assignment
   * @param assignment
   * @return a computed value for a constraint
   */
  double constraint(InvitationAssignment assignment);

}
