import org.junit.Assert;
import org.junit.Test;

// test calcul racine
public class TestUnitaireRacine
{

  /**
     @pre r>=0
     @pre epsilon > 0
     @post resultat > 0 et |resultat^2 - r | < epsilon
   */
  public double AppelCalculRacine(double r, double epsilon)
  {
    return Racine.racineCarree(r, epsilon);
  }

  //------------------------------------------------------------
  // Les différents tests
  // ------------------------------------------------------------

  @Test(expected = AssertionError.class,timeout=1000)
  public void assertion_r_positif()
  {
    System.out.print("\nTest assertion r >= 0		 : ");
    double x = AppelCalculRacine(-3, 1e-7);
    System.out.print("Erreur : assertion manquante");
  }

  @Test(expected = AssertionError.class,timeout=1000)
  public void assertion_epsilon_positif()
  {
    System.out.print("\nTest assertion epsilon > 0	 : ");
    double x = AppelCalculRacine(5, -0.5);
    System.out.print("Erreur : assertion manquante");
  }

  @Test(timeout=1000)
  public void racine_carre_parfait()
  {
    System.out.print("\nTest racine d'un carré		 : ");
    double r = 4, epsilon = 1e-7;
    double x = AppelCalculRacine(r, epsilon);
    double solution = 2;
    Assert.assertTrue(solution + " attendu, " + x + " reçu", Math.abs(x * x - r) < epsilon);
  }

  @Test(timeout=1000)
  public void racine_reel_quelconque()
  {
    System.out.print("\nTest racine d'un réel quelconque : ");
    double r = 3./7., epsilon = 1e-12;
    double x = AppelCalculRacine(r, epsilon);
    double solution = Math.sqrt(r);
    Assert.assertTrue(solution + " attendu, " + x + " reçu", Math.abs(x * x - r) < epsilon);
  }

  @Test(timeout=1000)
  public void racine_pi()
  {
    System.out.print("\nTest racine de Pi (3.14...)      : ");
    double r = Math.PI, epsilon = 1e-12;
    double x = AppelCalculRacine(r, epsilon);
    double solution = Math.sqrt(r);
    Assert.assertTrue(solution + " attendu, " + x + " reçu", Math.abs(x * x - r) < epsilon);
  }

} // TestUnitaireRacine
