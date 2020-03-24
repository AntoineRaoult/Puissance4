import java.io.Serializable;
import java.util.Scanner;

/**
 * <p>Represente un joueur de la partie</p>
 * <p>Herite de la classe Participant</p>
 *
 * @see Participant
 */
public class Joueur extends Participant {

    /**
     * Nom du joueur
     */
    private String nom;

    /**
     * Creer un Joueur
     * Le numero est attribue grace a l'attribut de classe nombreParticipants de la classe abstraite Participant
     *
     * @param nom nom du joueur
     */
    public Joueur(String nom) {
        super();
        this.nom = nom;
    }

    @Override
    public int jouer(Grille g) throws ColonneInexistanteException {
        Scanner sc = new Scanner(System.in);
        int col = sc.nextInt();
        if(col>g.getLargeur() || col<0 && col!=-1) {
            throw new ColonneInexistanteException("Colonne entree : " + col + " , doit etre compris entre 1 et " + g.getLargeur());
        }
        return col;
    }

    @Override
    public String toString() {
        return super.toString()+" : "+this.getNom();
    }

    /**
     * Retourner le nom du joueur
     * @return nom du joueur
     */
    public String getNom() {
        return this.nom;
    }
}
