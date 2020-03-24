import java.io.*;
import java.util.*;

/**
 * Represente le jeu en cours
 */
public class Jeu implements Serializable {

    /**
     * Grille de la partie
     *
     * @see Grille
     */
    private Grille grille;

    /**
     * File des joueurs de la partie
     * Quand un joueur joue, il est replace au bout de la file
     *
     * @see Joueur
     */
    private Queue<Participant> joueurs;

    /**
     * Pile des numeros de colonnes jouees
     */
    private Stack<Integer> plays;

    /**
     * Etat de la partie
     * true = partie finie
     * false = partie en cours
     */
    private boolean fin = false;

    /**
     * Creer un nouveau jeu vierge ou le charge depuis une sauvegarde
     */
    public Jeu(int nbCols, Queue<Participant> participants) {
        this.grille = new Grille(nbCols);
        this.joueurs = participants;
        this.plays = new Stack<Integer>();
    }

    /**
     * Met a jour la partie en fonction de ce que fait le joueur
     *
     * @throws ColonneInexistanteException si la colonne indiquee n'existe pas
     */
    public void suivant() throws ColonneInexistanteException, ChoixInvalideException, IOException {
        Participant current = this.joueurs.peek();

        System.out.println("C'est au joueur "+current.toString()+" de jouer : ");
        int col = current.jouer(this.grille);

        Scanner sc = new Scanner(System.in);
        if(col == 0) {
            System.out.println("Voulez vous sauvegarder la partie ? (oui/non)");
            String end = sc.nextLine();
            if(end.equals("oui")) {
                System.out.println("Entrez le nom du fichier de sauvegarde");
                String fichier = sc.nextLine();
                this.sauvegarderPartie(fichier);
                System.out.println("Partie sauvegardee, a bientot !");
            } else if(end.equals("non")) {
                System.out.println("A bientot !");
            } else {
            	sc.close();
                throw new ChoixInvalideException("Option '"+end+"' inexistante");
            }
            fin = true;
        } else if(col == -1) {
            this.grille.retirerJeton(this.plays.pop());
            this.afficherGrille();
            this.grille.afficherColonnesTriees();
            this.grille.afficherMoyenne();
        } else {
            this.grille.ajouterJeton(current, col-1);
            this.plays.add(col-1);
            this.joueurs.remove();
            this.joueurs.add(current);
            this.afficherGrille();
            this.grille.afficherColonnesTriees();
            this.grille.afficherMoyenne();

        }

        if(this.grille.avoirGagnant() != null) {
            System.out.println("le joueur "+this.grille.avoirGagnant().toString()+" a gagne");
            this.fin = true;
            sc.close();
        }
    }

    /**
     * Sauvegarde la partie en cours
     *
     * @param destination fichier de sauvegarde
     * @throws IOException
     */
    public void sauvegarderPartie(String destination) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(destination));
        oos.writeObject(this);
        oos.close();
    }

    /**
     * Charge une partie sauvegardee
     *
     * @param source fichier de sauvegarde
     * @return la partie chargee
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Jeu chargerPartie(String source) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(source));
        Jeu j = (Jeu) ois.readObject();
        ois.close();
        return j;
    }

    /**
     * Affiche les joueurs de la partie
     */
    public void afficherJoueurs() {
        for(Participant j : this.joueurs) {
            System.out.println("Joueur "+j);
        }
    }

    /**
     * Affiche la grille du jeu en cours
     */
    public void afficherGrille() {
        System.out.println(this.grille);
    }

    /**
     * Retourne la grille du jeu en cours
     *
     * @return grille du jeu en cours
     * @see Grille
     */
    public Grille getGrille() {
        return this.grille;
    }

    /**
     * Retourne la file des participants du jeu en cours
     *
     * @return file des participants du jeu en cours
     * @see Participant
     */
    public Queue<Participant> getJoueurs() {
        return joueurs;
    }

    public Stack<Integer> getPlays() {
        return this.plays;
    }

    /**
     * Retourne fin (fin=true si la partie est terminee)
     *
     * @return fin
     */
    public boolean getFin() {
        return this.fin;
    }

}
