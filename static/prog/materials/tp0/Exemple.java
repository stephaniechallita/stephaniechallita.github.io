import java.util.Scanner;

/**
 * Exemple.java : tri d'une suite de nombres
 *
 * Created: Fri Oct 29 09:19:27 2004
 * @author Jean-Christophe Engel
 * @version 1.5
 */

public class Exemple {

  public static void main(String [] args)
  {
    final int maxNombres = 12000;	// capacité du tableau
    int nbEntiers;			// nombre effectif d'entiers à trier

    Scanner entree = new Scanner(System.in);
    // saisir le nombre de valeurs à trier (et s'assurer qu'il est dans les limites)
    do {
      System.out.println("Ce programme trie une suite de nombres entiers saisis au clavier");
      System.out.print("Combien de nombres voulez-vous trier ? (>= 0 et <= " + maxNombres + ") : ");
      nbEntiers = entree.nextInt();
    } while (nbEntiers < 0 || nbEntiers > maxNombres);
    // ici, 0 <= nbEntiers <= maxNombres

    // Saisir la suite de nombre et l'afficher
    int [] lesNombres = initialiserTableau(nbEntiers, entree);
    System.out.print("Tableau initial            ");
    afficherTableau(lesNombres);

    // Trier la suite et l'afficher
    trierNombres(lesNombres);
    System.out.print("Tableau trié par sélection ");
    afficherTableau(lesNombres);
  } // main

  /**
     Tri d'un tableau d'entier.
     L'algorithme de tri utilisé est le tri par sélection :
     - on suppose que les éléments en fin de tableau sont triés
     - chercher le plus grand des éléments non triés (en tête du tableau)
     - l'échanger avec le dernier élément non trié
     - répéter jusqu'à ce que tous les éléments soient triés
     @param tnb : Le tableau à trier (modifié)
     @post tnb est trié en ordre croissant
  */
  public static void trierNombres(int[] tnb)
  {
    int nonTries = tnb.length;		// nombre d'éléments non triés en tête de tableau
					// les éléments de [nonTries..tnb.length[ sont triés
    while (nonTries > 1)
      {
	// les éléments de [nonTries..tnb.length[ sont triés
	// chercher le max de [0..nonTries[
	int imax = indiceMax(tnb, nonTries);
	// l'échanger avec le dernier non trié
	if (imax != nonTries - 1) {
	  int x = tnb[imax]; tnb[imax] = tnb[nonTries - 1] ; tnb[nonTries - 1] = x;
	}
	nonTries = nonTries - 1;
	// les éléments de [nonTries..tnb.length[ sont triés
      }
    // les éléments de [nonTries..tnb.length[ sont triés
    // nonTries = 1 => 1 tableau d'un élément est trié
  } // trierNombres

  /**
     chercher l'indice du max des nb premiers élts d'un tableau T
     @pre nb > 0
     @return indice du max des nb premiers (0 <= imax < nb)
  */
  static int indiceMax(int [] T, int nb)
  {
    int	imax = 0;			      // indice du max trouvé
    for (int j = 1; j < nb; ++j) {
      if (T[j] > T[imax]) { imax = j; }	      // nouveau max
    }
    return imax;
  }

  /**
   * Initialisation d'un tableau par des valeurs saisies par un utilisateur
   * @param nb = nombres de valeurs à saisir
   * @param entree = scanner d'entrée
   * @return tableau qui contient les valeurs saisies
   */
  public static int [] initialiserTableau(int nb, Scanner entree)
  {
    // créer un tableau pour y mettre les nombres saisis
    int [] lesNombres = new int[nb];
    System.out.println("Donnez une suite de " + nb + " entiers");
    // effectuer la saisie
    for (int i = 0; i < nb; i = i + 1) {
      System.out.print("valeur numéro " + (i+1) + " : ");
      lesNombres[i]=entree.nextInt();
    }
    return lesNombres;
  } // initialiserTableau

  /**
   * Affichage d'un tableau à l'écran
   * @param tnb = tableau à afficher (non modifié)
   */
  public static void afficherTableau(int [] tnb)
  {
    for (int i = 0 ; i < tnb.length ; ++i) {
        System.out.print(tnb[i] + " ") ;
      }
    System.out.println();
  } // afficherTableau

}// Exemple
