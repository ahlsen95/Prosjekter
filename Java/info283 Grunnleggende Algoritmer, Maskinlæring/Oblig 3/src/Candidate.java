
import java.util.ArrayList;
import java.util.Random;

/**
 * a class representing the candidates to selct from in a select-team-game
 * with compatibility values and names
 * It also creates a random assignment of compatibilities
 * 
 * @author sinbt
 *
 */
public class Candidate {

  // The candidates
  public static Candidate per = new Candidate("Per");
  public static Candidate anne = new Candidate("Anne");
  public static Candidate jonas = new Candidate("Jonas");
  public static Candidate aud = new Candidate("Aud");
  public static Candidate mons = new Candidate("Mons");
  public static Candidate kari = new Candidate("Kari");
  public static Candidate roar = new Candidate("Roar");
  public static Candidate emma = new Candidate("Emma");
  public static Candidate ola = new Candidate("Ola");
  public static Candidate lise = new Candidate("Lise");
  public static Candidate ivar = new Candidate("Ivar");
  public static Candidate brit = new Candidate("Brit");
  public static Candidate dag = new Candidate("Dag");
  public static Candidate siri = new Candidate("Siri");
  public static Candidate tom = new Candidate("Tom");
  public static Candidate eli = new Candidate("Eli");
  public static Candidate lars = new Candidate("Lars");
  public static Candidate mia = new Candidate("Mia");
  public static Candidate erik = new Candidate("Erik");
  public static Candidate nora = new Candidate("Nora");
  public static Candidate helge = new Candidate("Helge");
  public static Candidate ruth = new Candidate("Ruth");

  public static ArrayList<Candidate> allCandidates = new ArrayList<Candidate>();
  private static Random ran = new Random();

  /**
   * initialization of the candidates
   */
  private static void init() {
    allCandidates.add(per);
    allCandidates.add(anne);
    allCandidates.add(jonas);
    allCandidates.add(aud);
    allCandidates.add(mons);
    allCandidates.add(kari);
    allCandidates.add(roar);
    allCandidates.add(emma);
    allCandidates.add(ola);
    allCandidates.add(lise);
    allCandidates.add(ivar);
    allCandidates.add(brit);
    allCandidates.add(dag);
    allCandidates.add(siri);
    allCandidates.add(tom);
    allCandidates.add(eli);
    allCandidates.add(lars);
    allCandidates.add(mia);
    allCandidates.add(erik);
    allCandidates.add(nora);
    allCandidates.add(helge);
    allCandidates.add(ruth);
  }

  /**
   * the name of a candidate
   */
  String name;

  /**
   * a measure of how well a candidate fits with leader 1
   */
  int compatibility1;

  /**
   * a measure of how well a candidate fits with leader 2
   */
  int compatibility2;

  /**
   * A standard constructor for all fields
   * @param name
   * @param compatibility1
   * @param compatibility2
   */
  public Candidate(String name) {
    this.name = name;
  }

  /**
   * The compatibility of a candidate with respect to a compatibility set
   * @param n the number of the compatibility set
   * @return
   */
  public int score(int n) {
    if (n == 1)
      return compatibility1;
    else if (n == 2)
      return compatibility2;
    else
      return -1;
  }

  /**
   * A random assignment of compatibilities
   */
  public static void randomAssignment() {
    init();
    int sum1 = 0, sum2 = 0;

    // generate random compatibilities
    for (Candidate c : allCandidates) {
      c.compatibility1 = ran.nextInt(9) + 1;
      sum1 += c.compatibility1;
      c.compatibility2 = ran.nextInt(9) + 1;
      sum2 += c.compatibility2;
    }

    // adjust compatibilities so they add up to 100
    while (sum1 < 100)
      sum1 = addPoint1(1, sum1);
    while (sum1 > 100)
      sum1 = addPoint1(-1, sum1);
    while (sum2 < 100)
      sum2 = addPoint2(1, sum2);
    while (sum2 > 100)
      sum2 = addPoint2(-1, sum2);
  }

  /**
   * Adds compatibility points to compatibility set 1
   * @param inc the number of compatibility points 
   * @param sum th sum after the addition
   * @return
   */
  private static int addPoint1(int inc, int sum) {
    int i = ran.nextInt(allCandidates.size());
    int val = allCandidates.get(i).compatibility1 + inc;
    if (val < 1 || val > 9)
      return sum;
    else {
      allCandidates.get(i).compatibility1 = val;
      return sum + inc;
    }
  }

  /**
   * Adds compatibility points to compatibility set 2
   * @param inc the number of compatibility points 
   * @param sum the sum after the addition
   * @return
   */
  private static int addPoint2(int inc, int sum) {
    int i = ran.nextInt(allCandidates.size());
    int val = allCandidates.get(i).compatibility2 + inc;
    if (val < 1 || val > 9)
      return sum;
    else {
      allCandidates.get(i).compatibility2 = val;
      return sum + inc;
    }
  }

}
