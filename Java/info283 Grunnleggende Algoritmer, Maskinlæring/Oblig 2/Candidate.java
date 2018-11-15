
/**
 * An enum type representing all the possible candidates to be invited
 * @author Bjørnar Tessem
 *
 */
public enum Candidate {

  // The candidates
  anne("Anne", 1.5, true), guri("Guri", 0.3, true), nina("Nina", 1.2, true), emma("Emma", 0.1,
      true), berit("Berit", 0.6, true), sofie("Sofie", 0.3, true), kristin("Kristin", 0.9,
          true), kari("Kari", 1.0, true), solveig("Solveig", 1.4, true), britt("Britt", 0.8,
              true), marit("Marit", 1.0, true), aud("Aud", 1.1, true), elsa("Elsa", 0.2,
                  true), astrid("Astrid", 0.7, true), tone("Tone", 1.2, true), per("Per", 1.3,
                      false), ola("Ola", 0.9, false), knut("Knut", 0.3, false), ivar("Ivar", 1.4,
                          false), nils("Nils", 1.1, false), dag("Dag", 0.6, false), jens("Jens",
                              0.7, false), tom("Tom", 0.3, false), inge("Inge", 0.8, false), lars(
                                  "Lars", 1.1, false), even("Even", 1.0, false), erik("Erik", 0.4,
                                      false), artur("Artur", 0.9, false), helge("Helge", 1.1,
                                          false), rune("Rune", 0.8, false);

  /**
   * the name of a candidate
   */
  String name;

  /**
   * a measure of how well one wants a candidate to be at a party
   */
  double likeability;

  /**
   * the gender of a candidate
   */
  public boolean woman;

  /**
   * A standard constructor for all fields
   * @param name
   * @param likeability
   * @param woman
   */
  Candidate(String name, double likeability, boolean woman) {
    this.name = name;
    this.likeability = likeability;
    this.woman = woman;
  }

}
