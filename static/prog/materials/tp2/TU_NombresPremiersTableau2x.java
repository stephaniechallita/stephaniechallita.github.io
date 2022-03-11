package test;

import tableau.Tableau2x;
import types.Tableau;

public class TU_NombresPremiersTableau2x extends TU_NombresPremiersBlock
 {
  // --------------------------------------------------------------------------------
  // Rien à changer
  // --------------------------------------------------------------------------------

  // création de tableau
  public Tableau<Integer> makeTableau(int capinit)
   {
    return new Tableau2x<Integer>(capinit);
  }
}
