package util;

public class Outils
{
  /**
   * Calculer le pgcd de deux entiers selon l'algorithme d'Euclide
   * @param a : entier > 0
   * @param b : entier > 0
   * @return  : pgcd de a et b
   */
  public static int pgcd(int a, int b)
  {
    assert a > 0 : "ERREUR d'appel : a doit être strictement positif";
    assert b > 0 : "ERREUR d'appel : b doit être strictement positif";
    return mypgcd(a, b);
  }
  private static int mypgcd(int a, int b)
  {
    if (b == 0) { return a; }
    else	{ return mypgcd(b, a % b); }
  }
}
