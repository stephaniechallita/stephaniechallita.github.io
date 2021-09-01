package test;

import org.junit.Assert;
import org.junit.Test;

import tableau.Tableau2x;
import types.Tableau;

public
  class TU_Tableau2x
  extends ATU_Tableau
{
  // --------------------------------------------------------------------------------
  // Rien à changer ici
  // --------------------------------------------------------------------------------

  public Tableau<Integer> makeTableau(int capinit)
  {
    return new Tableau2x<Integer>(capinit);
  }

  // push_back sans agrandissement : empty, size, full
  @Test
  public void testPush_back_not_full() {
    System.out.print("\npush_back        : empty, size, full sans agrandissement : ");
    long debut = System.currentTimeMillis();
    int capinit = 120;
    Tableau<Integer> b1 = makeTableau(capinit);
    // remplir la capacité initiale sans agrandir le tableau
    for (int i = 0; i < capinit; ++i) {
      b1.push_back(i);
      Assert.assertTrue("Erreur : le tableau ne devrait pas être plein, taille = " + b1.size(), !b1.full());
      Assert.assertTrue("Erreur : le tableau ne devrait pas être vide  : " + b1.empty(), ! b1.empty());
      Assert.assertTrue("Erreur : la taille devrait être " + (i+1) + " et non " + b1.size(), b1.size() == i+1);
    }
    Assert.assertTrue("Erreur : la taille devrait être " + capinit + " et non " + b1.size(), b1.size() == capinit);
    long fin = System.currentTimeMillis();
    System.out.println("test réussi (" + (fin-debut) + " ms)");
  }

  // push_back avec agrandissement : empty, size, full
  @Test
  public void testPush_back_agrandissement_taille() {
    System.out.print("\npush_back        : empty, size, full avec agrandissement : ");
    long debut = System.currentTimeMillis();
    int capinit = 120;
    Tableau<Integer> b1 = makeTableau(capinit);
    // vérifier l'agrandissement de la capacité
    for (int i = 0; i < 100*capinit; ++i) {
      b1.push_back(i);
      Assert.assertTrue("Erreur : le tableau ne devrait pas être plein, taille = " + b1.size(), !b1.full());
      Assert.assertTrue("Erreur : le tableau ne devrait pas être vide : " + b1.empty(), ! b1.empty());
      Assert.assertTrue("Erreur : la taille devrait être " + (i+1) + " et non " + b1.size(), b1.size() == i+1);
    }
    Assert.assertTrue("Erreur : la taille devrait être " + (100*capinit) + " et non " + b1.size(), b1.size() == (100*capinit));
    long fin = System.currentTimeMillis();
    System.out.println("test réussi (" + (fin-debut) + " ms)");
  }

  // push_back avec agrandissement : valeur
  @Test
  public void testPush_back_agrandissement_valeur() {
    System.out.print("\npush_back        : valeur avec agrandissement : ");
    long debut = System.currentTimeMillis();
    int capinit = 120;
    Tableau<Integer> b1 = makeTableau(capinit);
    for (int i = 0; i < 100*capinit; ++i) {
      b1.push_back(i);
      Assert.assertTrue("b1[" + i + "]=" + b1.get(i) + " au lieu de " + i, b1.get(i) == i);
    }
    long fin = System.currentTimeMillis();
    System.out.println("test réussi (" + (fin-debut) + " ms)");
  }

  // pop_back : vérifier qu'après retrait la taille diminue 
  @Test
  public void testPop_back_apres_agrandissement() {
    System.out.print("\npop_back         : taille après agrandissement : ");
    long debut = System.currentTimeMillis();
    int capinit = 120;
    Tableau<Integer> b1 = makeTableau(capinit);
    // ajouter des éléments dans le tableau de façon à l'agrandir
    for (int i = 0; i < 100*capinit; ++i) {
      b1.push_back(i);
    }
    // retirer les éléments
    for (int i = 0; i < 100*capinit; ++i) {
      Assert.assertTrue("Erreur : le tableau ne devrait pas être vide : " + b1.empty(), ! b1.empty());
      b1.pop_back();
      Assert.assertTrue("Erreur : la taille devrait être " + (100*capinit-i-1) + " et non " + b1.size(), b1.size() == 100*capinit-i-1);
    }
    Assert.assertTrue("Erreur : la taille devrait être " + 0 + " et non " + b1.size(), b1.size() == 0);
    Assert.assertTrue("Erreur : le tableau devrait être vide : " + b1.empty(), b1.empty());
    long fin = System.currentTimeMillis();
    System.out.println("test réussi (" + (fin-debut) + " ms)");
  }


  // get : vérifier que les éléments ajoutés dans un tableau ont bien la valeur prévue
  @Test
  public void testGet_complet() {
    System.out.print("\nget              : valeur après agrandissement et pop : ");
    long debut = System.currentTimeMillis();
    int capinit = 120;
    Tableau<Integer> b1 = makeTableau(capinit);
    int nb = 1000;
    for (int i = 0; i < nb; ++i) {
      b1.push_back(i);
    }
    // retirer des éléments et vérifier la valeur des éléments restants
    for (int i = 0; i < nb/2; ++i) {
      b1.pop_back();
      for (int j = 0; j < b1.size(); ++j) {
	Assert.assertTrue("b1[" + j + "]=" + b1.get(j) + " au lieu de " + j, b1.get(j) == j);
      }
    }
    long fin = System.currentTimeMillis();
    System.out.println("test réussi (" + (fin-debut) + " ms)");
  }

  // set : vérifier qu'après modification un élément a bien sa nouvelle valeur
  @Test
  public void testSet_apres_agrandissement() {
    System.out.print("\nset              : valeur après agrandissement : ");
    long debut = System.currentTimeMillis();
    int capinit = 120;
    Tableau<Integer> b1 = makeTableau(capinit);
    int nb = 100000;
    for (int i = 0; i < nb; ++i) {
      b1.push_back(i);
    }
    // vérifier la valeur des éléments
    for (int i = 0; i < nb; ++i) {
      Assert.assertTrue("b1[" + i + "]=" + b1.get(i) + " au lieu de " + i, b1.get(i) == i);
    }
    // modifier la valeur des éléments
    for (int i = 0; i < nb; ++i) {
      b1.set(i, b1.get(i)*b1.get(i));
    }
    // vérifier la valeur des éléments
    for (int i = 0; i < nb; ++i) {
      Assert.assertTrue("b1[" + i + "]=" + b1.get(i) + " au lieu de " + (i*i), b1.get(i) == i*i);
    }
    // retirer des éléments
    for (int i = 0; i < nb/2; ++i) {
      b1.pop_back();
    }
    // vérifier la valeur des éléments
    for (int i = 0; i < nb/2; ++i) {
      Assert.assertTrue("b1[" + i + "]=" + b1.get(i) + " au lieu de " + (i*i), b1.get(i) == i*i);
    }
    long fin = System.currentTimeMillis();
    System.out.println("test réussi (" + (fin-debut) + " ms)");
  }
}
