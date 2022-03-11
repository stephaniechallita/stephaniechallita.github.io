package test;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import tableau.Block;
import types.Tableau;

// tests recherche dichotomique
public class TU_RechercheDichotomique
{
  // --------------------------------------------------------------------------------
  // CHANGER LE NOM DE LA CLASSE SI BESOIN
  // --------------------------------------------------------------------------------

  public boolean rechercheDichotomique(int x, int [] t)
  {
    Tableau<Integer> block = arrayToTableau(t);
    return main.NombresPremiers.estPresent(x, block, t.length);
  }

  //------------------------------------------------------------
  // Rien à modifier plus bas
  //------------------------------------------------------------

  // 2.1 tableau d'un élément
  @Test(timeout=2000)
  public void test_1element_inferieur() {
    int [] tnb = { -7 };
    System.out.print("\nrecherche dans un tableau d'un élément (élt inférieur)	     : ");
    Assert.assertFalse(rechercheDichotomique(-8, tnb));
    System.out.println("Test réussi");
  }

  // 2.2 tableau d'un élément
  @Test(timeout=2000)
  public void test_1element_present() {
    int [] tnb = { -7 };
    System.out.print("\nrecherche dans un tableau d'un élément (élt présent)	     : ");
    Assert.assertTrue(rechercheDichotomique(-7, tnb));
    System.out.println("Test réussi");
  }

  // 2.3 tableau d'un élément
  @Test(timeout=2000)
  public void test_1element_superieur() {
    int [] tnb = { -7 };
    System.out.print("\nrecherche dans un tableau d'un élément (élt supérieur)	     : ");
    Assert.assertFalse(rechercheDichotomique(-6, tnb));
    System.out.println("Test réussi");
  }

  // 3. élément cherché inférieur à tous les éléments du tableau
  @Test(timeout=2000)
  public void test_inferieur() {
    int [] tnb = {
      0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 11, 12, 12, 12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 18, 19, 20, 21, 21, 22, 22, 23, 24, 25, 27, 28, 29, 29, 30, 31, 35, 43, 45, 48, 51, 55, 77, 88, 88, 88, 482, 579, 609, 617, 650, 650, 669, 702, 725, 738, 778, 797, 802, 802, 802, 805, 813, 823, 830, 848, 871, 896, 921, 933, 946
    };
    System.out.print("\nrecherche d'un élément inférieur à tous les éléments	     : ");
    Assert.assertFalse(rechercheDichotomique(-7, tnb));
    System.out.println("Test réussi");
  }


  // 4. élément cherché supérieur à tous les éléments du tableau
  @Test(timeout=2000)
  public void test_superieur() {
    int [] tnb = {
      0, 5, 6, 7, 8, 9, 10, 10, 10, 11, 12, 12, 12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 18, 19, 20, 21, 21, 22, 22, 23, 24, 25, 27, 28, 29, 29, 30, 31, 35, 43, 45, 48, 51, 55, 77, 88, 88, 88, 482, 579, 609, 617, 650, 650, 669, 702, 725, 738, 778, 797, 802, 802, 802, 805, 813, 823, 830, 848, 871, 896, 921, 933, 946
    };
    System.out.print("\nrecherche d'un élément supérieur à tous les éléments	     : ");
    Assert.assertFalse(rechercheDichotomique(1000, tnb));
    System.out.println("Test réussi");
  }

  // 5. élément cherché absent (ni inférieur ni supérieur à tous les éléments du tableau)
  @Test(timeout=2000)
  public void test_absent_apres_premier() {
    int [] tnb = {
      0, 5, 6, 7, 8, 9, 10, 10, 10, 11, 12, 12, 12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 18, 19, 20, 21, 21, 22, 22, 23, 24, 25, 27, 28, 29, 29, 30, 31, 35, 43, 45, 48, 51, 55, 77, 88, 88, 88, 482, 579, 609, 617, 650, 650, 669, 702, 725, 738, 778, 797, 802, 802, 802, 805, 813, 823, 830, 848, 871, 896, 921, 933, 946
    };
    System.out.print("\nrecherche d'un élément absent (après 1er)		     : ");
    Assert.assertFalse(rechercheDichotomique(4, tnb));
    System.out.println("Test réussi");
  }

  // 6. 
  @Test(timeout=2000)
  public void test_absent_quelconque() {
    int [] tnb = {
      0, 5, 6, 7, 8, 9, 10, 10, 10, 11, 12, 12, 12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 18, 19, 20, 21, 21, 22, 22, 23, 24, 25, 27, 28, 29, 29, 30, 31, 35, 43, 45, 48, 51, 55, 77, 88, 88, 88, 482, 579, 609, 617, 650, 650, 669, 702, 725, 738, 778, 797, 802, 802, 802, 805, 813, 823, 830, 848, 871, 896, 921, 933, 946
    };
    System.out.print("\nrecherche d'un élément absent (position qque)		     : ");
    Assert.assertFalse(rechercheDichotomique(651, tnb));
    System.out.println("Test réussi");
  }

  // 7.
  @Test(timeout=2000)
  public void test_absent_avant_dernier() {
    int [] tnb = {
      0, 5, 6, 7, 8, 9, 10, 10, 10, 11, 12, 12, 12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 18, 19, 20, 21, 21, 22, 22, 23, 24, 25, 27, 28, 29, 29, 30, 31, 35, 43, 45, 48, 51, 55, 77, 88, 88, 88, 482, 579, 609, 617, 650, 650, 669, 702, 725, 738, 778, 797, 802, 802, 802, 805, 813, 823, 830, 848, 871, 896, 921, 933, 946
    };
    System.out.print("\nrecherche d'un élément absent (avant dernier)		     : ");
    Assert.assertFalse(rechercheDichotomique(940, tnb));
    System.out.println("Test réussi");
  }

  // 8. élément cherché  = premier du tableau
  @Test(timeout=2000)
  public void test_present_premier() {
    int [] tnb = {
      0, 5, 6, 7, 8, 9, 10, 10, 10, 11, 12, 12, 12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 18, 19, 20, 21, 21, 22, 22, 23, 24, 25, 27, 28, 29, 29, 30, 31, 35, 43, 45, 48, 51, 55, 77, 88, 88, 88, 482, 579, 609, 617, 650, 650, 669, 702, 725, 738, 778, 797, 802, 802, 802, 805, 813, 823, 830, 848, 871, 896, 921, 933, 946
    };
    System.out.print("\nrecherche d'un élément présent (le premier)		     : ");
    Assert.assertTrue(rechercheDichotomique(0, tnb));
    System.out.println("Test réussi");
  }

  // 9. élément cherché  = dernier du tableau
  @Test(timeout=2000)
  public void test_present_dernier() {
    int [] tnb = {
      0, 5, 6, 7, 8, 9, 10, 10, 10, 11, 12, 12, 12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 18, 19, 20, 21, 21, 22, 22, 23, 24, 25, 27, 28, 29, 29, 30, 31, 35, 43, 45, 48, 51, 55, 77, 88, 88, 88, 482, 579, 609, 617, 650, 650, 669, 702, 725, 738, 778, 797, 802, 802, 802, 805, 813, 823, 830, 848, 871, 896, 921, 933, 946
    };
    System.out.print("\nrecherche d'un élément présent (le dernier)		     : ");
    Assert.assertTrue(rechercheDichotomique(946, tnb));
    System.out.println("Test réussi");
  }

  // 10. élément cherché présent à une position quelconque
  @Test(timeout=2000)
  public void test_present_quelconque() {
    int [] tnb = {
      0, 5, 6, 7, 8, 9, 10, 10, 10, 11, 12, 12, 12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 18, 19, 20, 21, 21, 22, 22, 23, 24, 25, 27, 28, 29, 29, 30, 31, 35, 43, 45, 48, 51, 55, 77, 88, 88, 88, 482, 579, 609, 617, 650, 650, 669, 702, 725, 738, 778, 797, 802, 802, 802, 805, 813, 823, 830, 848, 871, 896, 921, 933, 946
    };
    System.out.print("\nrecherche d'un élément présent (position qque)		     : ");
    Assert.assertTrue(rechercheDichotomique(23, tnb));
    System.out.println("Test réussi");
  }

  // 11. élément cherché présent plusieurs fois
  @Test(timeout=2000)
  public void test_present_plusieurs_fois() {
    int [] tnb = {
      0, 5, 6, 7, 8, 9, 10, 10, 10, 11, 12, 12, 12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 18, 19, 20, 21, 21, 22, 22, 23, 24, 25, 27, 28, 29, 29, 30, 31, 35, 43, 45, 48, 51, 55, 77, 88, 88, 88, 482, 579, 609, 617, 650, 650, 669, 702, 725, 738, 778, 797, 802, 802, 802, 805, 813, 823, 830, 848, 871, 896, 921, 933, 946
    };
    System.out.print("\nrecherche d'un élément présent (plusieurs fois)		     : ");
    Assert.assertTrue(rechercheDichotomique(13, tnb));
    System.out.println("Test réussi");
  }

  // 12. test un peu plus complet
  @Test(timeout=5000)
  public void tableau_hasard()
  {
    int nbElements = 100000;
    System.out.print("\nrecherche parmi " + nbElements + " nombres tirés au hasard		     : ");
    long debut = System.currentTimeMillis();

    // faire un tableau trié
    int [] tnombres = initialiserTableau(nbElements);
    Arrays.sort(tnombres);

    // ABSENT INFÉRIEUR : chercher un nombre inférieur au premier
    Assert.assertFalse(rechercheDichotomique(tnombres[0] - 10, tnombres));

    // chercher alternativement un nombre présent et un nombre absent
    int i = 0;
    while (i < tnombres.length - 1) {
      // chercher un nombre PRÉSENT
      Assert.assertTrue(rechercheDichotomique(tnombres[i], tnombres));

      // chercher un nombre ABSENT
      if ((tnombres[i+1]-tnombres[i]>1)) {
	Assert.assertFalse(rechercheDichotomique((tnombres[i] + tnombres[i+1]) / 2, tnombres));
      }

      i = i + 131;
    }
    // chercher le dernier
    i = tnombres.length - 1;
    Assert.assertTrue(rechercheDichotomique(tnombres[i], tnombres));

    // ABSENT SUPÉRIEUR : chercher un nombre supérieur au dernier
    Assert.assertFalse(rechercheDichotomique(tnombres[i] + 1000, tnombres));

    // c'est fini
    long fin = System.currentTimeMillis();
    System.out.println("test réussi (" + (fin-debut) + " ms)");
  }

  // faire un Tableau avec les éléments d'un []
  Tableau<Integer> arrayToTableau(int [ ] t) {
    Tableau<Integer> tab = makeTableau(t.length);
    for (int i = 0; i < t.length; ++i) {
      tab.push_back(t[i]);
    }
    return tab;
  }
  /**
   * initialiser un tableau avec des nombres tirés au hasard
   * @param nb  : nombre d'éléments du tableau (0 < nb)
   * @return      tableau de nb éléments tirés au hasard
   */
  public static int [] initialiserTableau(int nb)
  {
    int [] thasard = new int[nb];
    Random generateur = new Random();
    for (int i = 0; i < nb; ++i) {
      int h = generateur.nextInt(nb*100);
      thasard[i] = h;
    }
    return thasard;
  }

  public Tableau<Integer> makeTableau(int capacite) {
    return new Block<Integer>(capacite);
  }
}
