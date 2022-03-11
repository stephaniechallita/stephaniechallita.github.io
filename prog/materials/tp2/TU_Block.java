package test;

import org.junit.Assert;
import org.junit.Test;

import tableau.Block;
import types.Tableau;

public class TU_Block extends ATU_Tableau
{
  // --------------------------------------------------------------------------------
  // Rien à changer ici
  // --------------------------------------------------------------------------------

  public Tableau<Integer> makeTableau(int capacite)
  {
    return new Block<Integer>(capacite);
  }

  // push_back : test assertion  !full 
  @Test(expected=AssertionError.class)
  public void testPush_back_assertion() {
    System.out.print("\npush_back        : test assertion  !full : ");
    Tableau<Integer> b2 = makeTableau(10);
    for (int i = 0; i < 100; ++i) {
      b2.push_back(100);
    }
  }

  // push_back : vérifier que le tableau devient plein
  @Test
  public void testPush_back_full() {
    System.out.print("\npush_back        : vérifier que le tableau devient plein : ");
    // ajouter des éléments dans un tableau initialement vide
    Tableau<Integer> b1 = makeTableau(10);
    for (int taille = 0; taille < 10; ++taille) {
      b1.push_back(taille);
    }
    Assert.assertTrue("Erreur : le tableau devrait être plein (taille = " + b1.size(), b1.full());
    System.out.println("test réussi");
  }

  // full : vérifier qu'un tableau non plein le devient quand on le remplit :)
  // et qu'un tableau plein ne l'est plus quand on enlève un élément
  @Test
  public void testFull() {
    System.out.print("\nfull             : ajout => full, retrait => !full : ");
    Tableau<Integer> b1 = makeTableau(10);
    Assert.assertFalse("Erreur : le tableau ne devrait pas être plein : " + b1.full(), b1.full());
    // ajouter des éléments
    for (int i = 1; i <= 9; ++i) {
      b1.push_back(5);
      Assert.assertFalse("Erreur : le tableau ne devrait pas être plein : " + b1.full(), b1.full());
    }
    // finir de remplir le tableau
    b1.push_back(5);
    Assert.assertTrue("Erreur : le tableau devrait être plein : " + b1.full(), b1.full());
    // ôter un élément
    b1.pop_back();
    Assert.assertFalse("Erreur : le tableau ne devrait pas être plein : " + b1.full(), b1.full());
    System.out.println("test réussi");
  }

}
