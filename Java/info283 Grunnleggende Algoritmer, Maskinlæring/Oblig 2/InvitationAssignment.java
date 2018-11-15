
import java.util.EnumMap;
import java.util.Map;

/**
 * A representation of an assignment of invitations
 * @author Bjørnar Tessem
 *
 */
public class InvitationAssignment {

  /**
   * the actual assignment
   */
  Map<Candidate, Boolean> theAssignment;

  /**
   * creates an empty assignment
   */
  public InvitationAssignment() {
    theAssignment = new EnumMap<Candidate, Boolean>(Candidate.class);
  }

  /**
   * creates an assignment as a copy of an other assignment
   * @param original
   */
  public InvitationAssignment(InvitationAssignment original) {
    theAssignment = new EnumMap<Candidate, Boolean>(original.theAssignment);
  }

  /**
   * @param c the Candidate
   * @return the invitation status of the candidate
   */
  boolean invited(Candidate c) {
    return theAssignment.get(c);
  }

  /**
   * Inverts the invitation status of a candidate
   * @param c the candidate
   */
  void invert(Candidate c) {
    // get the previous assignemnt
    boolean old = theAssignment.get(c);

    // set the new assignment to opposite of old
    theAssignment.put(c, !old);
  }

  /**
   * Sets the invitation status of a candidate
   * @param c the candidate
   * @param b the new status
   */
  void set(Candidate c, boolean b) {
    theAssignment.put(c, b);
  }

  /**
   * String representation of the Invitation Assignment
   */
  public String toString() {
    StringBuffer result = new StringBuffer();
    for (Candidate c : theAssignment.keySet()) {
      if (theAssignment.get(c))
        result.append(c.name + " ");
    }
    return result.toString();
  }

}
