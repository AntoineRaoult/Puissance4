import java.io.Serializable;
import java.util.Objects;

/**
 * <p>Represente un participant de la partie</p>
 * <p>Classe abstraite</p>
 */
public abstract class Participant implements Serializable {

    /**
     * Numero du participant
     */
    private int numero;

    /**
     * Nombre total de participants de la partie
     */
    public static int nombreParticipants = 0;

    /**
     * Creer un Participant
     */
    public Participant() {
        this.nombreParticipants++;
        this.numero = nombreParticipants;
    }

    /**
     * Retourne le numero du participant
     *
     * @return numero du participant
     */
    public int getNumero() {
        return this.numero;
    }

    @Override
    public String toString() {
        return Integer.toString(this.numero);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return numero == that.numero;
    }


    /**
     * Retourne la colonne jouee par le participant
     *
     * @param g Grille du jeu en cours
     * @return colonne jouee par le participant
     * @throws ColonneInexistanteException si la colonne retournee n'existe pas
     */
    public abstract int jouer(Grille g) throws ColonneInexistanteException;

    /**
     * Retourne le nom du participant
     *
     * @return nom du participant
     */
    public abstract String getNom();
}
