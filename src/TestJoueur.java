import static libtest.Lanceur.lancer;
import static libtest.OutilTest.*;

/**
 * Classe de test de la classe Joueur
 */
public class TestJoueur {
	
	public void test01_constructeur() {
		Joueur j = new Joueur("test");
		assertEquals("Le nom devrait etre 'test'","test",j.getNom());
	}

    public static void main(String[] args) {
        lancer(new TestJoueur(),args);
    }
}
