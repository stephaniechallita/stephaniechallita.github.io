import types.ABinHuffman;
import types.ListeABH;
import outilsHuffman.OutilsHuffman;

/**
 * Réalisation du codage d'un texte par la méthode de Huffman
 */

public class CodageHuffman
{
  public static void main (String[] args)
  {
    //------------------------------------------------------------------------
    // 0. Saisir le nom du fichier à coder (À FAIRE)
    //------------------------------------------------------------------------
    String nomFichier;

    //------------------------------------------------------------------------
    // 1. Lire le texte (DONNÉ)
    //------------------------------------------------------------------------
    char [] texte = OutilsHuffman.lireFichier(nomFichier);

    //------------------------------------------------------------------------
    // 2. Calculer la table des fréquences des caractères (À FAIRE)
    //------------------------------------------------------------------------
    int [] tableFrequences = calculerFrequences(texte);

    //------------------------------------------------------------------------
    // 3. Enregistrer la table de fréquences dans le fichier de sortie (DONNÉ)
    //------------------------------------------------------------------------
    OutilsHuffman.enregistrerTableFrequences(tableFrequences, nomFichier + ".code");

    //------------------------------------------------------------------------
    // 4. Construire l'arbre de codage de Huffman (DONNÉ - À FAIRE)
    //------------------------------------------------------------------------
    ABinHuffman arbreCodageHuffman = construireArbreHuffman(tableFrequences);

    //------------------------------------------------------------------------
    // Afficher l'arbre de codage de Huffman (DÉJÀ FAIT)
    //------------------------------------------------------------------------
    System.out.println("Arbre de Huffman associé au texte " + nomFichier);
    DecodageHuffman.afficherHuffman(arbreCodageHuffman);

    //------------------------------------------------------------------------
    // 5. Construire la table de codage associée (À FAIRE)
    //------------------------------------------------------------------------
    String [] tablecodage = construireTableCodage(arbreCodageHuffman);

    //------------------------------------------------------------------------
    // 5.1. afficher la table de codage (À FAIRE)
    //------------------------------------------------------------------------
    System.out.println("Table de codage associée au texte " + nomFichier);
    afficherTableCodage(tablecodage);

    //------------------------------------------------------------------------
    // 6. coder le texte avec l'arbre de Huffman (À FAIRE)
    //------------------------------------------------------------------------
    StringBuilder texteCode = coderTexte(texte, tablecodage);

    //------------------------------------------------------------------------
    // 7. enregistrer le texte codé (DONNÉ)
    //------------------------------------------------------------------------
    OutilsHuffman.enregistrerTexteCode(texteCode, nomFichier + ".code");

    //------------------------------------------------------------------------
    // xx. calculer et afficher les stats (À FAIRE)
    //------------------------------------------------------------------------
    // calculer la taille du fichier non codé
    // calculer la taille du fichier codé

  }

  /**
   * 2. calculer la fréquence d'apparition de chaque caractère
   * @param  tcar tableau des caractères du texte
   * @return tableau de fréquence des caractères, indicé par les caractères
   */
  public static int [] calculerFrequences(char [] tcar)
  {
  }

  /**
   * 4. construire un arbre de codage de Huffman par sélection et combinaison
   * des éléments minimaux
   * @param tableFrequences table des fréquences des caractères
   * @return arbre de codage de Huffman
   */
  public static ABinHuffman construireArbreHuffman(int [] tableFrequences)
  {
  }

  /**
   * 4.1 Faire une liste triée dont chaque élément est un arbreBinaire<br>
   * comprenant un unique sommet dont l'étiquette est un couple
   * <caractère, fréquence>, trié par fréquence croissante
   * @param tableFrequences : table des fréquences des caractères
   * @return		      la liste triée
   */
  private static ListeABH faireListeAbinHuffman(int [] tableFrequences)
  {
  }

  /**
   * 5. construire la table de codage correspondant à l'arbre de Huffman
   * @param abinHuff : arbre de Huffman
   * @return table de (dé)codage indicé par lex caractères
   */
  public static String [] construireTableCodage(ABinHuffman abinHuff)
  {
  }

  /**
   * 5.1. Afficher la table de codage associée au texte
   * @param tablecodage : table de codage associée au texte
   */
  public static void afficherTableCodage(String [] tablecodage)
  {
  }

  /**
   * 6. Coder un texte à l'aide de la table de codage
   * @param texte à coder
   * @param tablecodage : table de codage associée au texte
   * @return texte codé
   */
  public static StringBuilder coderTexte(char [] texte, String [] tablecodage)
  {
  }
}// CodageHuffman
