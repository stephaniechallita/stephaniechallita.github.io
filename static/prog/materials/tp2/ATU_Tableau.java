package test;

import org.junit.Assert;
import org.junit.Test;

import types.Tableau;

public abstract
  class ATU_Tableau
{
  // --------------------------------------------------------------------------------
  // Rien à changer ici
  // --------------------------------------------------------------------------------

  // constructeur : test assertion 0 < capacite
  @Test(expected=AssertionError.class)
  public void testConstructeurEchecAssertionCapacite() {
    System.out.print("\nconstructeur     : vérifier assertion 0 < capacite : ");
    // capacité <= 0 => assertion
    makeTableau(0);
  }

  // constructeur : vérifier l'état initial du tableau
  @Test
  public void testEtatInitial()
  {
    System.out.print("\nconstructeur     : vérifier l'état initial du tableau : taille, vide et non plein : ");
    Tableau<Integer> b1 = makeTableau(10);
    Assert.assertTrue("Erreur : taille incorrecte : " + b1.size() + " au lieu de 0", b1.size() == 0);
    Assert.assertTrue("Erreur : le tableau devrait être vide : " + b1.empty(), b1.empty());
    Assert.assertTrue("Erreur : ne devrait pas être plein, taille = " + b1.size(), !b1.full());
    System.out.println("test réussi");
  }

  // push_back 1 élt : vérifier size, empty, full
  @Test
  public void testPush_back_1elt() {
    System.out.print("\npush_back 1 élt  : vérifier size, empty, full : ");
    // ajouter 1 élément dans un tableau initialement vide
    Tableau<Integer> b1 = makeTableau(100);
    b1.push_back(999);
    // vérifier size, empty, full
    Assert.assertTrue("Erreur : la taille devrait être " + 1 + " et non " + b1.size(), b1.size() == 1);
    Assert.assertTrue("Erreur : le tableau ne devrait plus être vide : " + b1.empty(), ! b1.empty());
    Assert.assertTrue("Erreur : ne devrait pas être plein, taille = " + b1.size(), !b1.full());
    System.out.println("test réussi");
  }

  // push_back n élts : vérifier size, empty, full
  @Test
  public void testPush_back_nelts() {
    System.out.print("\npush_back n élts : vérifier size, empty, full : ");
    // ajouter des éléments dans un tableau initialement vide
    Tableau<Integer> b1 = makeTableau(100);
    for (int taille = 0; taille < 99; ++taille) {
      b1.push_back(taille);
      // vérifier size, empty, full
      Assert.assertTrue("Erreur : la taille devrait être " + (taille+1) + " et non " + b1.size(), b1.size() == taille+1);
      Assert.assertTrue("Erreur : le tableau ne devrait plus être vide : " + b1.empty(), ! b1.empty());
      Assert.assertTrue("Erreur : ne devrait pas être plein, taille = " + b1.size(), !b1.full());
    }
    System.out.println("test réussi");
  }

  // push_back : vérifier que l'élément ajouté a la bonne valeur
  @Test
  public void testPush_back_valeur() {
    System.out.print("\npush_back        : vérifier que l'élément ajouté a la bonne valeur : ");
    // ajouter des éléments dans un tableau initialement vide
    Tableau<Integer> b1 = makeTableau(100);
    for (int i = 0; i < 100; ++i) {
      b1.push_back(i);
      // vérifier la valeur de l'élément ajouté
      Assert.assertTrue("b1[" + i + "]=" + b1.get(i) + " au lieu de " + i, b1.get(i) == i);
    }
    System.out.println("test réussi");
  }

  // size : vérifier que la taille augmente correctement après chaque ajout 
  // et diminue correctement après chaque retrait
  @Test
  public void testSize() {
    System.out.print("\nsize             : vérifier augmentation et diminution taille : ");
    Tableau<Integer> b1 = makeTableau(10);
    // ajouter 7 éléments
    int taille_b1 = 0;
    for (int nb = 0; nb < 7; ++nb) {
      b1.push_back(5);
      ++taille_b1;
      Assert.assertTrue("Erreur : taille incorrecte : " + b1.size() + " au lieu de "+ taille_b1, b1.size() == taille_b1);
    }
    // retirer 7 éléments
    for (int nb = 0; nb < 7; ++nb) {
      b1.pop_back();
      --taille_b1;
      Assert.assertTrue("Erreur : taille incorrecte : " + b1.size() + " au lieu de "+ taille_b1, b1.size() == taille_b1);
    }
    System.out.println("test réussi");
  }

  // empty : vérifier qu'un tableau vide ne l'est plus après ajout d'un élément
  // et qu'un tableau non vide le devient après retrait de tous ses éléments
  @Test
  public void testEmpty() {
    System.out.print("\nempty            : vrai avant ajout, faux après, vrai après retraits : ");
    Tableau<Integer> b1 = makeTableau(10);
    Assert.assertTrue("Erreur : le tableau devrait être vide : " + b1.empty(), b1.empty());
    // ajouter 7 éléments
    for (int nb = 0; nb < 7; ++nb) {
      b1.push_back(5);
      Assert.assertTrue("Erreur : le tableau ne devrait pas être vide : " + b1.empty(), !b1.empty());
    }
    // retirer 7 éléments
    for (int nb = 0; nb < 7; ++nb) {
      b1.pop_back();
    }
    Assert.assertTrue("Erreur : le tableau devrait être vide : " + b1.empty(), b1.empty());
    System.out.println("test réussi");
  }

  // get : test assertion  0 <= i 
  @Test(expected=AssertionError.class)
  public void testGetEchecAssertion1() {
    System.out.print("\nget              : test assertion  0 <= i : ");
    Tableau<Integer> b2 = makeTableau(10);
    b2.get(-2);
  }

  // get : test assertion  i < size()
  @Test(expected=AssertionError.class)
  public void testGetEchecAssertion2() {
    System.out.print("\nget              : test assertion  i < size() : ");
    Tableau<Integer> b2 = makeTableau(10);
    b2.get(8);
  }

  // get : vérifier qu'un élément ajouté a bien la bonne valeur
  @Test
  public void testGet() {
    System.out.print("\nget              : vérifier qu'un élément ajouté a bien la bonne valeur : ");
    Tableau<Integer> b2 = makeTableau(120);
    // ajouter un élément et vérifier sa valeur
    b2.push_back(66);
    Assert.assertTrue("b2[" + (b2.size()-1) + "]=" + b2.get(b2.size()-1) + " au lieu de 66", b2.get(b2.size()-1) == 66);
    System.out.println("test réussi");
  }

  // set : test assertion  0 <= i 
  @Test(expected=AssertionError.class)
  public void testSetEchecAssertion1() {
    System.out.print("\nset              : test assertion  0 <= i  : ");
    Tableau<Integer> b2 = makeTableau(10);
    b2.set(-2, -2);
  }

  // set : test assertion  i < size() 
  @Test(expected=AssertionError.class)
  public void testSetEchecAssertion2() {
    System.out.print("\nset              : test assertion i < size()  : ");
    Tableau<Integer> b2 = makeTableau(10);
    b2.set(8, -2);
  }

  // set : vérifier qu'après modification un élément a bien sa nouvelle valeur
  @Test
  public void testSet() {
    System.out.print("\nset              : vérifier qu'après modification un élément a bien sa nouvelle valeur : ");
    Tableau<Integer> b2 = makeTableau(10);
    // ajouter un élément et vérifier sa valeur
    b2.push_back(66);
    Assert.assertTrue("b2[" + (b2.size()-1) + "]=" + b2.get(b2.size()-1) + " au lieu de 66", b2.get(b2.size()-1) == 66);
    for (int i = 0; i < b2.size(); ++i) {
      b2.set(i, 3*i+1);
      Assert.assertTrue("b2[" + i + "]=" + b2.get(i) + " au lieu de " + (3*i+1), b2.get(i) == 3*i+1);
    }
    System.out.println("test réussi");
  }

  // pop_back : test assertion  !empty 
  @Test(expected=AssertionError.class)
  public void testPop_backEchecAssertion() {
    System.out.print("\npop_back         : test assertion  !empty : ");
    Tableau<Integer> b1 = makeTableau(10);
    b1.pop_back();
  }

  // pop_back : vérifier que la taille a diminué d'une unité 
  @Test
  public void testPop_back() {
    System.out.print("\npop_back         : vérifier que la taille a diminué d'une unité : ");
    // ajouter des éléments dans un tableau initialement vide
    Tableau<Integer> b1 = makeTableau(10);
    for (int taille = 0; taille < 10; ++taille) {
      b1.push_back(taille);
      Assert.assertTrue("Erreur : la taille devrait être " + (taille+1) + " et non " + b1.size(), b1.size() == taille+1);
    }
    b1.pop_back();
    Assert.assertTrue("Erreur : la taille devrait être " + 9 + " et non " + b1.size(), b1.size() == 9);
    System.out.println("test réussi");
  }

  // méthode de création de tableau qui dépend de l'implémentation
  public abstract Tableau<Integer> makeTableau(int capacite);

}
