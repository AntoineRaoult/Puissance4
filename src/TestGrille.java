import static libtest.Lanceur.lancer;
import static libtest.OutilTest.assertEquals;

/**
 * Classe de test de la classe Grille
 */
public class TestGrille {

	public void test01_constructeur() {
		Grille g = new Grille(8);
		assertEquals("La largeur de la grille devrait être 8",8,g.getLargeur());		
		for(int i=0; i<g.getColonnes().size();i++) {
			assertEquals("Les colonnes devraient être vides",0,g.getColonnes().get(i).size());
		}
		assertEquals("La hateur devrait être 0",0,g.getHauteur());
	}
	
	public void test02_ajouterJeton() {
		Grille g = new Grille(8);
		g.ajouterJeton(new Joueur("test"), 0);
		assertEquals("La hauteur devrait être 0",0,g.getHauteur());
		assertEquals("La colonne 0 devrait avoir 1 jeton",1,g.getColonnes().get(0).size());
		g.ajouterJeton(new Joueur("test"), 0);
		assertEquals("La hauteur devrait être 1",1,g.getHauteur());
		assertEquals("La colonne 0 devrait avoir 2 jeton",2,g.getColonnes().get(0).size());
	}
	
	public void test03_retirerJeton() {
		Grille g = new Grille(8);
		g.ajouterJeton(new Joueur("test"), 0);
		g.ajouterJeton(new Joueur("test"), 0);
		g.retirerJeton(0);
		assertEquals("La colonne 0 devrait avoir 1 jeton",1,g.getColonnes().get(0).size());
		g.retirerJeton(0);
		assertEquals("La colonne 0 devrait avoir 0 jeton",0,g.getColonnes().get(0).size());
	}
	
	public void test04_trouverColonnePlusRemplie() {
		Grille g = new Grille(8);
		g.ajouterJeton(new Joueur("test"), 0);
		Colonne c = g.trouverColonnePlusRemplie();
		assertEquals("La colonne devrait être la colonne 1", 1, c.getNumeroCol());
	}

	public void test05_avoirQuatreAlignesColonne(){
		Grille g = new Grille(8);
		Joueur j = new Joueur("test");
		g.ajouterJeton(j, 0);
		assertEquals("Le resultat devrait etre null",null,g.avoirQuatreAlignesColonne());
		g.ajouterJeton(j, 0);
		assertEquals("Le resultat devrait etre null",null,g.avoirQuatreAlignesColonne());
		g.ajouterJeton(j, 0);
		assertEquals("Le resultat devrait etre null",null,g.avoirQuatreAlignesColonne());
		g.ajouterJeton(j, 0);
		assertEquals("Le resultat devrait etre le joueur j",j,g.avoirQuatreAlignesColonne());		
	}
	
	public void test06_avoirQuatreAlignesLigne() {
		Grille g = new Grille(8);
		Joueur j = new Joueur("test");
		g.ajouterJeton(j, 0);
		assertEquals("Le resultat devrait etre null",null,g.avoirQuatreAlignesLigne());
		g.ajouterJeton(j, 1);
		assertEquals("Le resultat devrait etre null",null,g.avoirQuatreAlignesLigne());
		g.ajouterJeton(j, 2);
		assertEquals("Le resultat devrait etre null",null,g.avoirQuatreAlignesLigne());
		g.ajouterJeton(j, 3);
		assertEquals("Le resultat devrait etre le joueur j",j,g.avoirQuatreAlignesLigne());
	}
	
	public void test07_avoirQuatreAlignesDiagonale() {
		Grille g = new Grille(8);
		Joueur j1 = new Joueur("test1");
		Joueur j2 = new Joueur("test2");
		
		g.ajouterJeton(j1, 1);
		g.ajouterJeton(j1, 2);
		g.ajouterJeton(j1, 2);
		g.ajouterJeton(j1, 3);
		g.ajouterJeton(j1, 3);
		g.ajouterJeton(j1, 3);
		
		g.ajouterJeton(j2, 0);
		assertEquals("Le resultat devrait etre null",null,g.avoirQuatreAlignesDiagonale());
		g.ajouterJeton(j2, 1);
		assertEquals("Le resultat devrait etre null",null,g.avoirQuatreAlignesDiagonale());
		g.ajouterJeton(j2, 2);
		assertEquals("Le resultat devrait etre null",null,g.avoirQuatreAlignesDiagonale());
		g.ajouterJeton(j2, 3);
		
		assertEquals("Le resultat devrait etre le joueur j2",j2,g.avoirQuatreAlignesDiagonale());
		
	}
	
    public static void main(String[] args) {
        lancer(new TestGrille(),args);
    }
}
