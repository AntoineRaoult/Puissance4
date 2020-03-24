import java.io.Serializable;

/**
 * Represente un jeton d'une colonne
 */
public class Jeton implements Serializable {

    /**
     * Proprietaire du jeton
     *
     * @see Participant
     */
    private Participant proprietaire;

    /**
     * Créer un Jeton avec un propriétaire donné en paramètre
     *
     * @param p propriétaire du Jeton
     */
    public Jeton(Participant p) {
        this.proprietaire = p;
    }

    /**
     * Retounr le proprietaire du jeton
     *
     * @return proprietaire du jeton
     * @see Participant
     */
    public Participant getProprietaire() {
        return this.proprietaire;
    }

    @Override
    public String toString() {
        return Integer.toString(this.proprietaire.getNumero());
    }
}
