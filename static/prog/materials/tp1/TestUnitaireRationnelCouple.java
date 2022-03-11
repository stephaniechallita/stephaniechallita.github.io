package util;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import types.Rationnel;
import rationnel.RationnelCouple;

public class TestUnitaireRationnelCouple
{

  Rationnel creerRationnel(int n)	  { return new RationnelCouple(n); }
  Rationnel creerRationnel(int n, int d ) { return new RationnelCouple(n, d); }
  Rationnel creerRationnel(Rationnel r )  { return new RationnelCouple(r); }

  @Test(timeout=20)
  // test constructeur à un paramètre
  public void test_Constructeur1() {
    System.out.print("\nconstructeur à un paramètre : ");
    Rationnel r1 = creerRationnel(3);
    Assert.assertTrue(r1.getNumerateur() == 3);
    Assert.assertTrue(r1.getDenominateur() == 1);
    System.out.println("test réussi");
  }

  // test constructeur à 2 paramètres : cas d'un dénominateur nul
  @Test(timeout=20, expected = AssertionError.class)
  public void test_Constructeur2_EchecAssertion() {
    System.out.print("\nconstructeur à 2 paramètres : assertion dénominateur non nul");
    creerRationnel(3, 0);
  }

  @Test(timeout=20)
  // test constructeur à 2 paramètres : divers cas
  public void test_Constructeur2() {
    System.out.print("\nconstructeur à 2 paramètres : vérifier simplifications : ");
    // fraction nulle
    Rationnel r0 = creerRationnel(0, 7);
    Assert.assertTrue(r0.getNumerateur()   == 0);
    Assert.assertTrue(r0.getDenominateur() == 1);
    // pas de simplification ni changement de signe
    Rationnel r1 = creerRationnel(3, 1);
    Assert.assertTrue(r1.getNumerateur()   == 3);
    Assert.assertTrue(r1.getDenominateur() == 1);
    // pas de simplification, mais changement signe
    r1 = creerRationnel(3, -7);
    Assert.assertTrue(r1.getNumerateur()   == -3);
    Assert.assertTrue(r1.getDenominateur() == 7);
    // simplification obligatoire, sans changement de signe
    Rationnel r2 = creerRationnel(60, 90);
    Assert.assertTrue(r2.getNumerateur()   == 2);
    Assert.assertTrue(r2.getDenominateur() == 3);
    // simplification et changement signe (1er cas)
    Rationnel r3 = creerRationnel(-60, -90);
    Assert.assertTrue(r3.getNumerateur()   == 2);
    Assert.assertTrue(r3.getDenominateur() == 3);
    // simplification et changement signe (2ème cas)
    Rationnel r4 = creerRationnel(60, -90);
    Assert.assertTrue(r4.getNumerateur()   == -2);
    Assert.assertTrue(r4.getDenominateur() == 3);
    System.out.println("test réussi");
  }

  @Test(timeout=20)
  // test constructeur de copie
  public void test_Constructeur3() {
    System.out.print("\nconstructeur de copie : vérifier égalité original/copie : ");
    Rationnel r1 = creerRationnel(-21, -7);
    Rationnel r2 = creerRationnel(r1);
    Assert.assertTrue(r1.getNumerateur()   == r2.getNumerateur() &&
		      r1.getDenominateur() == r2.getDenominateur());
    System.out.println("test réussi");
  }

  @Test(timeout=20)
  public void testGetNumerateur() {
    System.out.print("\nvérifier getNumerateur : ");
    Rationnel r1 = creerRationnel(-21, -4);
    Assert.assertTrue(r1.getNumerateur() == 21);
    Rationnel r2 = creerRationnel(-21, -7);
    Assert.assertTrue(r2.getNumerateur() == 3);
    Rationnel r3 = creerRationnel(-5);
    Assert.assertTrue(r3.getNumerateur() == -5);
    System.out.println("test réussi");
  }

  @Test(timeout=20)
  public void testGetDenominateur() {
    System.out.print("\nvérifier getDenominateur : ");
    Rationnel r1 = creerRationnel(-21, -4);
    Assert.assertTrue(r1.getDenominateur() == 4);
    Rationnel r2 = creerRationnel(-21, -7);
    Assert.assertTrue(r2.getDenominateur() == 1);
    Rationnel r3 = creerRationnel(-5);
    Assert.assertTrue(r3.getDenominateur() == 1);
    System.out.println("test réussi");
  }

  @Test(timeout=20)
  // test de la méthode equals
  public void testEqualsRationnel() {
    System.out.print("\nTester égalité : ");
    Rationnel r1 = creerRationnel(-21, -7);
    Rationnel r2 = creerRationnel(r1);
  
    Assert.assertTrue(r1.equals(r2));
    Assert.assertTrue(r2.equals(r1));
  
    Rationnel r3 = creerRationnel(3);
    Assert.assertTrue(r1.equals(r3));
    Assert.assertTrue(r3.equals(r1));
  
    Rationnel r4 = creerRationnel(4);
    Assert.assertTrue(! r1.equals(r4));
    Assert.assertTrue(! r4.equals(r3));
    System.out.println("test réussi");
  }

  @Test(timeout=20)
  // test de la somme de 2 rationnels
  public void testSomme() {
    System.out.print("\nTester somme : ");
    Rationnel r1 = creerRationnel(-21, -7);
    Rationnel r3 = creerRationnel(3);
    Rationnel somme = r1.somme(r3);
    Assert.assertTrue(somme.getNumerateur() == 6 && somme.getDenominateur() == 1);
  
    Rationnel r2 = creerRationnel(-3);
    somme = r2.somme(r1);
    Assert.assertTrue(somme.getNumerateur() == 0 && somme.getDenominateur() == 1);
    System.out.println("test réussi");
  }

  @Test(timeout=20, expected = AssertionError.class)
  // inversion d'une fraction de valeur 0
  public void testInverseEchecAssert() {
    System.out.print("\nTester inverse : assertion ...");
    Rationnel zero = creerRationnel(0);
    Rationnel inverse = zero.inverse();
  }

  @Test(timeout=20)
  // inversion d'une fraction non nulle
  public void testInverse() {
    System.out.print("\nTester inverse : ");
    Rationnel r1 = creerRationnel(-21, -4);
    Rationnel inverse = r1.inverse();
    Assert.assertTrue(inverse.getNumerateur()   == r1.getDenominateur() && 
		      inverse.getDenominateur() == r1.getNumerateur());
    System.out.println("test réussi");
  }

  @Test(timeout=20)
  // valeur d'une fraction
  public void testValeur() {
    System.out.print("\nTester valeur d'une fraction : ");
    Rationnel r1 = creerRationnel(-21, -4);
    double val_r1 = r1.valeur();
    Assert.assertTrue(val_r1 == 5.25);
    System.out.println("test réussi");
  }

  @Test(timeout=20)
  public void testCompareTo() {
    System.out.print("\nTester compareTo : ");
    Rationnel r1 = creerRationnel(-21, -4);
    Assert.assertTrue(r1.compareTo(r1) == 0);
    Rationnel r2 = creerRationnel(-21, -7);
    Assert.assertTrue(r1.compareTo(r2) > 0);
    Assert.assertTrue(r2.compareTo(r1) < 0);
    System.out.println("test réussi");
  }
}
