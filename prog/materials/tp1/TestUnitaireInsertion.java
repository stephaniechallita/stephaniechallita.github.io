package util;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import types.Rationnel;
// ************************************************************************
// CHANGER l'import POUR TESTER AVEC LA DEUXIÈME IMPLÉMENTATION
import rationnel.RationnelSimple;
//import rationnel.RationnelCouple;
// ************************************************************************


public class TestUnitaireInsertion
{

  // ************************************************************************
  // REMPLACER ClientRationnel PAR LE NOM DE LA CLASSE QUI CONTIENT
  // LA FONCTION insererRationnel
  // ************************************************************************
  void appelFonctionInsertion(Rationnel r, Rationnel[ ] tr, int N)
  {
    util.ClientRationnel.insererRationnel(r, tr, N);
  }

  // ************************************************************************
  // REMPLACER RationnelSimple PAR RationnelCouple POUR TESTER
  // AVEC LA DEUXIÈME IMPLÉMENTATION
  //
  // REMPLACER new RationnelXXX PAR L'APPEL DE makeRationnel
  // POUR TESTER LE MÉLANGE DE CLASSES
  // ************************************************************************

  public Rationnel creerRationnel(int n)
  {
    return new RationnelSimple(n);
  }
  public Rationnel creerRationnel(int n, int d)
  {
    return new RationnelSimple(n, d);
  }
  public Rationnel creerRationnel(Rationnel r)
  {
    return new RationnelSimple(r);
  }

  // ************************************************************************
  // Rien à modifier ci-dessous
  // ************************************************************************

  @Test(timeout=20)
  public void inserer_tableau_vide()
  {
    System.out.print("\ninsertion dans un tableau vide		      : ");
    // insertion dans un tableau vide
    Rationnel [] resu = new Rationnel[]{creerRationnel(1, 2)};

    Rationnel [] lesRationnels = new Rationnel[1];
    Rationnel r1 = creerRationnel(1, 2);
    appelFonctionInsertion(r1, lesRationnels, 0);
    tester_egalite(lesRationnels, resu, 1);
    System.out.println("test réussi");
  }

  @Test(timeout=20)
  public void inserer_tete_tableau_1elt()
  {
    System.out.print("\ninsertion en tête d'un tableau d'un élément   : ");
    Rationnel [] resu = new Rationnel[]{creerRationnel(1, 3),
					creerRationnel(1, 2)};

    Rationnel [] lesRationnels = new Rationnel[2];
    lesRationnels[0] = creerRationnel(1, 2);
    appelFonctionInsertion(creerRationnel(1, 3), lesRationnels, 1);
    tester_egalite(lesRationnels, resu, 2);
    
    System.out.println("test réussi");
  }

  @Test(timeout=20)
  public void inserer_fin_tableau_1elt()
  {
    System.out.print("\ninsertion en fin  d'un tableau d'un élément   : ");
    Rationnel [] resu = new Rationnel[]{creerRationnel(1, 3),
					creerRationnel(1, 2)};

    Rationnel [] lesRationnels = new Rationnel[2];
    lesRationnels[0] = creerRationnel(1, 3);
    appelFonctionInsertion(creerRationnel(1, 2), lesRationnels, 1);
    tester_egalite(lesRationnels, resu, 2);
    
    System.out.println("test réussi");
  }

  @Test(timeout=20)
  public void inserer_tete()
  {
    System.out.print("\ninsertion en tête			      : ");
    int [] numerateurs =
      {
	15,14,13,12,11,10,9,8,7,6,
      };
    int [] denominateurs =
      {
	5,5,5,5,5,5,5,5,5,5,
      };
    Rationnel [] resu = new Rationnel[numerateurs.length];
    for (int i = 0 ; i < numerateurs.length;  ++i)
    {
      resu[resu.length - i - 1] = creerRationnel(numerateurs[i], denominateurs[i]);
    }

    // insérer
    Rationnel [] lesRationnels = new Rationnel[numerateurs.length];
    for (int i = 0; i < numerateurs.length; ++i)
    {
      appelFonctionInsertion(creerRationnel(numerateurs[i], denominateurs[i]),
			     lesRationnels, i);
    }
    tester_egalite(lesRationnels, resu, numerateurs.length);
    System.out.println("test réussi");
  }

  @Test(timeout=20)
  public void inserer_fin()
  {
    System.out.print("\ninsertion en fin			      : ");
    int [] numerateurs =
      {
	15,14,13,12,11,10,9,8,7,6,
      };
    int [] denominateurs =
      {
	5,5,5,5,5,5,5,5,5,5,
      };
    Rationnel [] resu = new Rationnel[numerateurs.length];
    for (int i = 0 ; i < numerateurs.length;  ++i)
    {
      resu[resu.length - i - 1] = creerRationnel(numerateurs[i], denominateurs[i]);
    }

    // insérer
    Rationnel [] lesRationnels = new Rationnel[numerateurs.length];
    for (int i = numerateurs.length - 1; i >= 0; --i)
    {
      appelFonctionInsertion(creerRationnel(numerateurs[i], denominateurs[i]),
			     lesRationnels,
			     lesRationnels.length - i - 1);
    }
    tester_egalite(lesRationnels, resu, numerateurs.length);
    System.out.println("test réussi");
  }

  @Test(timeout=20)
  public void inserer_partout()
  {
    System.out.print("\ninsertion partout			      : ");
    int [] numerateurs =
      {
	15,3,-5,10,11,-6,-8,7,13,14,
      };
    int [] denominateurs =
      {
	5,5,5,5,5,5,5,5,5,5,
      };
    Rationnel [] resu = new Rationnel[numerateurs.length];
    int [] numerateurs_tris = Arrays.copyOf(numerateurs, numerateurs.length);
    Arrays.sort(numerateurs_tris);
    for (int i = 0 ; i < numerateurs_tris.length;  ++i)
    {
      resu[i] = creerRationnel(numerateurs_tris[i], denominateurs[i]);
    }

    // insérer
    Rationnel [] lesRationnels = new Rationnel[numerateurs.length];
    for (int i = 0 ; i < numerateurs.length;  ++i)
    {
      appelFonctionInsertion(creerRationnel(numerateurs[i], denominateurs[i]),
			     lesRationnels,
			     i);
    }
    tester_egalite(lesRationnels, resu, numerateurs.length);
    System.out.println("test réussi");
  }

  // vérifier l'égalité des nb premiers éléments de deux tableaux
  void tester_egalite(Rationnel [] t1, Rationnel [] t2, int nb)
  {
    for(int i = 0; i < nb; ++i)
    {
      Assert.assertTrue(t1[i].equals(t2[i]));
    }
  }
}
