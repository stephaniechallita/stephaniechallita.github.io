package test;

import tableau.Block;
import types.Tableau;

public class TU_NombresPremiersBlock extends ATU_NombresPremiers
{
  // --------------------------------------------------------------------------------
  // Fonctions à tester : CHANGER LE NOM DE LA CLASSE SI BESOIN 
  // --------------------------------------------------------------------------------

  // Déterminer si n est premier
  boolean estPremier(int n, Tableau<Integer> nombresPremiers)
  {
    return main.NombresPremiers.estPremier(n, nombresPremiers);
  }

  // Calculer les nombres premiers dans l'intervalle [2,N]
  int calculerNombresPremiers(int N, Tableau<Integer> nombresPremiers)
  {
    return main.NombresPremiers.calculerNombresPremiers(N, nombresPremiers);
  }

  // Remplir un tableau avec nb nombres tirés au hasard dans [0..nb[
  Tableau<Integer> remplirHasard(int nb)
  {
    return main.NombresPremiers.remplirHasard(nb);
  }

  // Éliminer du tableau t1 les éléments du tableau t2 (trié)
  int eliminerPresents(Tableau<Integer> t, Tableau<Integer> nombresPremiers)
  {
    return main.NombresPremiers.eliminerPresents(t, nombresPremiers);
  }

  // --------------------------------------------------------------------------------
  // Rien à changer plus bas
  // --------------------------------------------------------------------------------

  // création de tableau
  public Tableau<Integer> makeTableau(int capinit)
  {
    return new Block<Integer>(capinit);
  }
}
