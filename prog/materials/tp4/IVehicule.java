package v1;

/**
 * Modèle de véhicule
 */
public interface IVehicule
{
  // déterminer la longueur d'un véhicule
  public int getLongueur();

  // déterminer le nombre de passagers
  public int getPassagers();

  // connaître l'immatriculation
  public String getImmatriculation();

  // représentation affichable
  public String toString();

  // calculer le tarif du véhicule
  public float calculerTarif();
}
