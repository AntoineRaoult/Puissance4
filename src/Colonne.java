import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represente une colonne de la grille
 */
public class Colonne implements Comparable, Serializable {

    /**
     * Nombre de colonnes au total
     */
    private static int nbColonnes = 0;

    /**
     * Liste des jetons de la liste
     *
     * @see Jeton
     */
    private ArrayList<Jeton> jetons;

    /**
     * Numero de la colonne
     */
    private int numeroCol;

    /**
     * Creer une Colonne vide
     */
    public Colonne() {
        this.jetons = new ArrayList<Jeton>();
        this.numeroCol = getNbColonnes()+1;
        setNbColonnes(getNbColonnes() + 1);
    }

    /**
     * Retourne le nombre de jetons de la colonne
     *
     * @return nombre de jetons de la colonne
     */
    public int size() {
        return this.jetons.size();
    }

    /**
     * Retourne le numero de la colonne
     *
     * @return numero de la colonne
     */
    public int getNumeroCol() {
        return this.numeroCol;
    }

    /**
     * Retounr la liste des jetons
     *
     * @return liste des jetons
     * @see Jeton
     */
    public ArrayList<Jeton> getJetons() {
        return jetons;
    }

    @Override
    public int compareTo(Object o) {
        Colonne c = (Colonne) o;
        if(this.getJetons().size()>c.getJetons().size()) {
            return -1;
        } else if(this.getJetons().size()<c.getJetons().size()) {
            return 1;
        } else {
            return 0;
        }
    }

	public static int getNbColonnes() {
		return nbColonnes;
	}

	public static void setNbColonnes(int nbColonnes) {
		Colonne.nbColonnes = nbColonnes;
	}


}
