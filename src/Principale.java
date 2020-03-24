import java.io.IOException;
import java.util.*;

/**
 * Classe principale, lance le jeu
 */
public class Principale {
    public static void main(String[] args) {
        try {
            Jeu j;
            Scanner sc = new Scanner(System.in);
            System.out.println("Voulez vous charger une partie ou en commencer une nouvelle ? (charger/creer)");
            String choix = sc.nextLine();
            if(choix.equals("charger")) {
                System.out.println("Indiquez le fichier de sauvegarde");
                String fichier = sc.nextLine();
                j = Jeu.chargerPartie(fichier);;
                System.out.println("Grille chargee : ");
                j.afficherGrille();
                System.out.println("Joueurs charges : ");
                j.afficherJoueurs();
            } else if(choix.equals("creer")) {
                Queue<Participant> participants = new LinkedList<Participant>();
                System.out.println("Entrez le nombre de joueurs :");
                int nbJoueurs = sc.nextInt();
                System.out.println("Entrez le nombre de bots :");
                int nbBots = sc.nextInt();
                System.out.println("Entrez le nombre de colonnes :");
                int nbCols = sc.nextInt();
                for(int i = 0; i<nbJoueurs; i++){
                	if(sc.hasNextLine()) {
                        sc.nextLine();
                	}
                	System.out.println("Entrez le nom du joueur " + (i+1));
                    String nom = sc.nextLine();
                    participants.add(new Joueur(nom));
                }
                for(int i = 0; i<nbBots; i++){
                    participants.add(new BotAleatoire());
                }
                j = new Jeu(nbCols,participants);
            } else {
                throw new ChoixInvalideException("Choix : '"+choix+"' inconnu");
            }
            while(!j.getFin()) {
                try {
                    System.out.println("---------------------------------------------------------------");
                    j.suivant();
                } catch (ColonneInexistanteException e) {
                    System.out.println(e.getMessage());
                } catch (ChoixInvalideException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Fin de partie sans sauvegarde");
                } catch (EmptyStackException e) {
                    System.out.println("Il n'y a plus d'element a retirer !");
                } catch (InputMismatchException e) {
                    System.out.println("Entree inconnue, reessayez");
                }
            }
        } catch (ChoixInvalideException e) {
            System.out.println(e.getMessage());
            System.out.println("Fin.");
        } catch (IOException e) {
            System.out.println("Erreur de lecture/ecriture de sauvegarde (fichier non-existant ?)");
        } catch (ClassNotFoundException e) {
            System.out.println("Probleme de lecture");
        }
    }
}
