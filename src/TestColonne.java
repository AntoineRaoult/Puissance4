import static libtest.Lanceur.lancer;
import static libtest.OutilTest.*;

/**
 * Classe de test de la classe Colonne
 */
public class TestColonne {

	public void test01_constructeur() {
		Colonne c = new Colonne();
		assertEquals("La liste des jetons devrait être vide",0,c.getJetons().size());
		assertEquals("Le numéro devrait être 1",1,c.getNumeroCol());
	}
	
	public void test02_compareTo() {
		Colonne c1 = new Colonne();
		Colonne c2 = new Colonne();
		c1.getJetons().add(new Jeton(new Joueur("test")));
		assertEquals("c1 devrait être plus remplis que c2", -1, c1.compareTo(c2));
		
	}
	
	public void test03_size() {
		Colonne c = new Colonne();
		c.getJetons().add(new Jeton(new Joueur("test")));
		assertEquals("la taille devrait etre 1",1,c.size());
		c.getJetons().add(new Jeton(new Joueur("test")));
		assertEquals("la taille devrait etre 2",2,c.size());
	}
	
    public static void main(String[] args) {
        lancer(new TestColonne(),args);
    }
}
