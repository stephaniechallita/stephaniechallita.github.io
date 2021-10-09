package outilsHuffman;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ListIterator;

import types.ABinHuffman;
import types.Couple;
import types.ListeABH;

/**
 * Outils mis à disposition pour réaliser le (dé)codage d'un texte par la méthode de Huffman
 *
 * @author <a href="mailto:Jean-Christophe.Engel@irisa.fr">Jean-Christophe Engel</a>
 * @version 2.0
 */

public class OutilsHuffman {

  //--------------------------------------------------------------------------------
  //			opérations d'entrée/sortie pour le CODAGE
  //--------------------------------------------------------------------------------

  /**
   * lire un texte dans un fichier et renvoyer son contenu dans un tableau de caractères
   * @param  nomFichier nom du fichier à lire
   * @return contenu du fichier dans un tableau de caractères
   */
  public static char [] lireFichier(String nomFichier)
  {
    try {
      File file			   = new File(nomFichier);
      long tailleFichier	   = file.length();
      if (tailleFichier > Integer.MAX_VALUE) {
	throw new IOException("La taille du fichier" + nomFichier + " excède la capacité maximale");
      }
      DataInputStream lecteur	   = new DataInputStream(new FileInputStream(file));
      char [] contenuFichierACoder = new char[(int)tailleFichier];

      // lire le contenu du fichier et le mémoriser dans un tableau
      for (int i = 0; i < tailleFichier; ++i) {
	int x = lecteur.read();
	contenuFichierACoder[i] = (char) x;
      }
      lecteur.close();
      return contenuFichierACoder;
    }
    catch(IOException e) {
      erreur("Échec lireFichier : " + e.getMessage());
    }
    return null; // jamais atteint
  }

  /**
   * enregistrer dans le fichier de sortie la table de fréquences
   * @param tabfreq table de fréquences
   * @param nomFichierCode nom du fichier de sortie
   */
  public static void enregistrerTableFrequences(int [] tabfreq, String nomFichierCode) {
    // mémoriser le nom du fichier de sortie
    m_nomFCodeSortie = nomFichierCode;
    try {
      // ouvrir le fichier de sortie
      m_fCodeSortie = new DataOutputStream(new FileOutputStream(nomFichierCode));

      // y enregistrer la table de fréquences
      for (int i = 0; i < tabfreq.length; ++i) {
	if (tabfreq[i] > 0) {
	  m_fCodeSortie.writeInt(tabfreq[i]);
	  m_fCodeSortie.writeByte(i);
	}
      }
      // ajouter un séparateur
      m_fCodeSortie.writeInt(0);
    } catch (IOException e) {
      erreur("Échec enregistrerTableFrequences : " + e.getMessage());
    }
  }

  /**
   * enregistrer le texte codé dans le fichier de sortie
   * @param texteCode à enregistrer
   * @param nomFichierCode du fichier de sortie
   */
  public static void enregistrerTexteCode(StringBuilder texteCode, String nomFichierCode) {
    // vérifier si le nom du fichier de sortie est celui mémorisé
    // ==> le texte codé doit être enregistré dans le même fichier que la table de fréquences

    if (m_nomFCodeSortie == null) {
      erreur("Échec enregistrerTexteCode : " + m_nomFCodeSortie + "\nIl faut enregistrer la table de fréquences AVANT le texte codé" );
    }
    if (! nomFichierCode.equals(m_nomFCodeSortie)) {
      erreur("Échec enregistrerTexteCode : " + nomFichierCode + "\nLe texte codé doit être enregistré dans le même fichier que la table de fréquences : " + m_nomFCodeSortie);
    }
    // c'est bon, en peut enregistrer
    try {
      // enregistrer le nombre de bits du texte codé
      m_fCodeSortie.writeLong(texteCode.length());
      // binariser le tableau d'octets
      byte [] tbin = binariser(texteCode);
      // enregistrer le texte codé
      m_fCodeSortie.write(tbin, 0, tbin.length);
      m_fCodeSortie.close();
    } catch (IOException e) {
      erreur("Échec enregistrerTexteCode : " + e.getMessage());
    }
  }
  //--------------------------------------------------------------------------------
  //			opérations d'entrée/sortie pour le DÉCODAGE
  //--------------------------------------------------------------------------------

  /**
   * lire la table de fréquences
   * @param nomFichierCode nom du fichier codé
   * @return table de fréquences lue dans le fichier
   */
  public static int [] lireTableFrequences(String nomFichierCode)
  {
    m_nomFichierCode = nomFichierCode;
    try {
      // ouvrir le fichier codé
      DataInputStream fCodeEntree = new DataInputStream(new FileInputStream(nomFichierCode));
      // FileInputStream is meant for reading streams of raw bytes such as image
      // data.
      // A data input stream lets an application read primitive Java data types
      // from an underlying input stream in a machine-independent way.
      int [ ] tabfreq = new int[256];
      // lire la table de fréquences : {frequence, car }+ 0
      while (true) {
	int frequence = fCodeEntree.readInt();		// fréquence
	if (frequence == 0) { break; }			// marqueur de fin
	char caractere = (char) fCodeEntree.read();	// caractère
	tabfreq[caractere] = frequence;
      }
      // Lire et mémoriser le texte codé
      m_longueurtexteCode = fCodeEntree.readLong(); 	// longueur du texte codé
      long nb = (m_longueurtexteCode+7)/8;		// nombre d'octets
      m_texteCode = new byte[(int)nb];			// tableau des octets binaires du fichier
      fCodeEntree.readFully(m_texteCode, 0, m_texteCode.length);
      fCodeEntree.close();
      System.out.println("Longueur du texte codé : " + (m_longueurtexteCode+7)/8 + " octets");
      return tabfreq;
    }
    catch (IOException e) {
      erreur("Échec lireTableFrequences : " + e.getMessage());
    }
    return null;
  }

  /**
   * lire le texte codé et le retourner dans une chaîne
   * @param nomFichierCode nom du fichier codé
   * @return texte codé
   */
  public static String lireTexteCode(String nomFichierCode)
  {
    // vérifier fichier
    if (m_nomFichierCode == null) {
      erreur("Échec lireTexteCode : " + m_nomFichierCode + "\nIl faut d'abord lire la table de fréquences");
    }
    if (! nomFichierCode.equals(m_nomFichierCode)) {
      erreur("Échec lireTexteCode : " + nomFichierCode + "\nLe texte codé doit être lu dans le même fichier que la table de fréquences :  " + m_nomFichierCode);
    }
    // fichier semble ok
    // débinariser le tableau d'octets mémorisé prédédemment
    byte [] tascii = debinariser(m_texteCode, (int) m_longueurtexteCode);

    // transformer ce tableau en chaîne
    return new String(tascii);
  }

  /**
   * enregistrer le texte décodé dans le fichier de sortie
   * @param texte décodé à enregistrer
   * @param nomFichierDecode nom du fichier de sortie
   */
  public static void enregistrerTexte(StringBuilder texte, String nomFichierDecode)
  {
    try {
      Writer sortie =
	new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nomFichierDecode),
						  "ISO-8859-1"));
      sortie.write(texte.toString());
      sortie.close();
    }
    catch (FileNotFoundException e) {
      erreur("Échec enregistrerTexte : fichier non trouvé : " + e.getMessage());
    }
    catch (UnsupportedEncodingException e) {
      erreur("Échec enregistrerTexte : l'encodage du texte n'est pas reconnu : " + e.getMessage());
    }
    catch (IOException e) {
      erreur("Échec enregistrerTexte : erreur d'entrée/sortie : " + e.getMessage());
    }
  }

  public static void enregistrerTexte_vo(StringBuilder texte, String nomFichierDecode) {
    OutputStream sortie;
    try {
      sortie = new DataOutputStream(new FileOutputStream(nomFichierDecode));
      for (int i = 0; i < texte.length(); ++i) {
	sortie.write(texte.charAt(i));
      }
      sortie.close();
    }
    catch (IOException e) {
      erreur("Échec enregistrerTexte : " + e.getMessage());
    }
  }
  //--------------------------------------------------------------------------------
  //			opérations arbres
  //--------------------------------------------------------------------------------
  /**
   * construit un arbre de codage de Huffman par sélection et combinaison
   * des éléments minimaux
   * @param tableFrequences table des fréquences des caractères
   * @return arbre de codage de Huffman
   */
  public static ABinHuffman construireArbreHuffman(int [] tableFrequences) {

    // Faire une liste triée dont chaque élément est un arbreBinaire
    // comprenant un unique sommet dont l'étiquette est un couple
    // <caractère, fréquence>, trié par fréquence croissante

    ListeABH listeFreq = faireListeAbinHuffman(tableFrequences);

    while (listeFreq.size() > 1) {
      // 1 prendre les arbre d'étiquettes minimales (ce sont les deux de tête)
      ABinHuffman abmin1 = listeFreq.get(0);
      listeFreq.removeFirst();		//(0); => accès indexé interdit
      ABinHuffman abmin2 = listeFreq.get(0);
      listeFreq.removeFirst();		//(0); => accès indexé interdit

      // 2 en faire un nouvel arbre
      ABinHuffman nouveau
      = consArbre(new Couple<Character, Integer>('@',
	  frequence(abmin1) + frequence(abmin2)),
	  abmin1, abmin2);

      // 3 l'insérer dans la liste
      insereTrie(listeFreq, nouveau);
    }
    // la liste ne contient plus qu'un arbre, c'est le bon !!
    return listeFreq.get(0);
  }

  /**
   * Faire une liste triée dont chaque élément est un arbreBinaire<br>
   * comprenant un unique sommet dont l'étiquette est un couple
   * <caractère, fréquence>, trié par fréquence croissante
   * @param tableFrequences table des fréquences des caractères
   * @return		      la liste triée
   */
  private static ListeABH faireListeAbinHuffman(int [] tableFrequences) {
    ListeABH listeFreq = new ListeABH();

    for (char i = 0; i < tableFrequences.length; ++i) {
      if (tableFrequences[i] > 0) {
	insereTrie(listeFreq,
	    consArbre(new Couple<Character, Integer>( i, tableFrequences[i]),
		new ABinHuffman(), new ABinHuffman()));
      }
    }
    return listeFreq;
  }

  /**
   * insère dans une liste triée un arbre comportant un unique sommet dont
   * l'étiquette est un couple <caractère, fréquence> le critère de tri est
   * la fréquence associée à la racine de l'arbre
   * @param listeABH liste triée dont chaque élément est un Arbre de Huffman
   * @param abinHuff arbre de Huffman à insérer
   */
  private static void insereTrie(ListeABH listeABH, ABinHuffman abinHuff) {
    // chercher la position d'insertion
    ListIterator<ABinHuffman> it = listeABH.listIterator();
    while (it.hasNext()) {
      if (frequence(it.next()) > frequence(abinHuff)) {
	// l'élément qu'on vient de dépasser est > celui qu'on veut insérer
	it.previous();
	break;
      }
      else {}   // poursuivre
    }
    // l'élément qu'on veut insérer est >= tous les élts de la liste ; le curseur est en fin
    // insérer l'élément à gauche du curseur
    it.add(abinHuff);
  }

  /**
   * construit un arbre d'étiquette x, de fils gauche g et de fils droit d
   * @param x : étiquette du nouvel arbre
   * @param g : fils gauche du nouvel arbre
   * @param d : fils droit  du nouvel arbre
   * @return nouvel arbre d'étiquette x, de fils gauche g et de fils droit d
   */
  public static ABinHuffman consArbre(Couple<Character, Integer> x,
      ABinHuffman g, ABinHuffman d) {
    ABinHuffman nouveau = new ABinHuffman();
    nouveau.setValeur(x);
    nouveau.filsGauche().setArbre(g);
    nouveau.filsDroit().setArbre(d);
    return nouveau;
  }

  // attribut frequence de l'étiquette d'un arbre de Huffmann
  private static int frequence(ABinHuffman a) {
    return a.getValeur().deuxieme();
  }

  //--------------------------------------------------------------------------------
  //		opérations diverses
  //--------------------------------------------------------------------------------

  /**
   * donne un nombre de millisecondes correspondant à l'instant présent
   * @return instant présent (millisecondes)
   */
  public static long getInstantPresent() {
    return System.currentTimeMillis();
  }

  /**
   * renvoie la taille d'un fichier
   * @param nomFichier nom du fichier
   * @return taille du fichier
   */
  public static long tailleFichier(String nomFichier) {
    java.io.File f = new java.io.File(nomFichier);
    return f.length();
  }

  //--------------------------------------------------------------------------------
  //			opérations internes liées aux E/S
  //--------------------------------------------------------------------------------

  private static void erreur(String message) {
    System.err.println(message);
    System.err.println("Erreur d'entrée/sortie fatale");
    System.exit(1);
  }

  /**
   * binariser une chaîne de char ; chaque élément de la chaîne vaut '0' ou '1'
   * @param tb01 tableau d'octets de valeur 0/1
   * @return tableau d'octets binaires
   */
  private static byte [] binariser(StringBuilder tb01) {
    int nboctets = (tb01.length() + 7) / 8;
    byte [] tbin = new byte[nboctets];
    for (int i = 0; i < nboctets; ++i) {
      tbin[i] = binariserOctet(tb01, 8*i);
    }
    return tbin;	
  }

  /**
   * transforme les 8 éléments du tableau tb en un octet binaire
   * @param tb01 tableau d'octets de valeur '0'/'1'
   * @param p : indice du premier élt de tb
   * @return octet binaire
   */
  private static byte binariserOctet(StringBuilder tb, int p) {
    byte octet = 0;
    for (int i = p; i < p+8; ++i) {
      octet <<= 1;
      if(i < tb.length() && tb.charAt(i) == '1') { ++octet; }
    }
    return octet;
  }

  /**
   * debinariser un tableau d'octets
   * @param tbin : tableau binaire d'octets
   * @param nbb : nombre de bits utiles (<= tbin.length * 8)
   * @return tableau de '0'/'1'
   */
  private static byte [] debinariser(byte [] tbin, int nbb) {
    byte [] tascii = new byte[nbb];
    for (int i = 0; i < tbin.length; ++i) {
      debinariserOctet(tbin[i], tascii, i*8);
    }
    return tascii;
  }

  /**
   * décode un octet (place des '0'/'1' dans ta à partir de l'indice p)
   */
  private static void debinariserOctet(byte octet, byte [] ta, int p) {

    // décoder de droite à gauche (car >>> marche mal)
    byte un = 1;
    for (int i = 7; i >= 0; --i) {
      // le dernier octet peut être incomplet
      if (p + i <  ta.length) {
	if ((octet & un) != 0) { ta[p+i] = '1'; } else { ta[p+i] = '0'; }
      }
      un <<= 1;					// >>> marche mal
    }
  }

  //--------------------------------------------------------------------------------
  // attributs internes
  //--------------------------------------------------------------------------------

  // attributs pour le décodage
  private static String m_nomFichierCode;	 // nom du fichier codé d'entrée
  private static byte [ ] m_texteCode;		 // contenu binaire du fichier codé
  private static long m_longueurtexteCode;

  // attributs pour le codage
  private static String m_nomFCodeSortie;	 // nom du fichier codé de sortie
  private static DataOutputStream m_fCodeSortie; // fichier codé de sortie

} // classe OutilsHuffman
