import java.io.IOException;
import java.util.*;
import static libtest.Lanceur.*;
import static libtest.OutilTest.*;

/**
 * Classe de test de la classe Jeu
 */
public class TestJeu {

    public void test01_constructeurCreation() throws ChoixInvalideException, IOException, ClassNotFoundException {
        Queue<Participant> q = new LinkedList<Participant>();
        q.add(new BotAleatoire());
        q.add(new BotAleatoire());
        Jeu j = new Jeu(8,q);
        assertEquals("La liste de joueurs devrait contenir 2 participants", 2, j.getJoueurs().size());
        assertEquals("La pile devrait être vide" ,0, j.getPlays().size());
        assertEquals("La largeur de la grille devrait être 8",8,j.getGrille().getLargeur());
    }

    public static void main(String[] args) {
        lancer(new TestJeu(),args);
    }
}
