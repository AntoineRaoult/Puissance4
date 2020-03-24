import java.io.Serializable;
import java.util.*;

/**
 * Represente la grille d'une partie
 */
public class Grille implements Serializable {

    /**
     * Liste triee sur l'ordre des colonnes de la grille
     *
     * @see Colonne
     */
    private List<Colonne> colonnes;

    /**
     * Liste triee sur le remplissage
     *
     * @see Colonne
     */
    private List<Colonne> colonnesTriees;

    /**
     * Largeur de la grille
     */
    private int largeur;

    /**
     * Hauteur de la grille (mise a jour au fur et a mesure)
     */
    private int hauteur;

    /**
     * Creer une grille de largeur parametrable
     *
     * @param taille taille de la grille
     */
    public Grille(int taille) {
        this.largeur = taille;
        Colonne.setNbColonnes(0);
        this.colonnes = new ArrayList<Colonne>(largeur);
        for(int i = 0; i<largeur; i++) {
            this.colonnes.add(new Colonne());
        }
    }

    /**
     * Met a jour la liste triee
     */
    public void trierListe() {
        colonnesTriees = (List<Colonne>)((ArrayList<Colonne>)this.colonnes).clone();
        Collections.sort(colonnesTriees);
    }

    /**
     * Affiche les colonnes par remplissage decroissant
     */
    public void afficherColonnesTriees() {
        String s = "Numeros de colonnes triees par remplissage : ";
        for(int i = 0; i<this.colonnesTriees.size(); i++) {
            s += this.colonnesTriees.get(i).getNumeroCol()+"("+this.colonnesTriees.get(i).size()+") ";
        }
        System.out.println(s);
    }

    /**
     * Affiche la moyenne de remplissage des colonnes
     */
    public void afficherMoyenne() {
        double moy = 0;
        for(Colonne colonne : colonnes) {
            moy += colonne.size();
        }
        moy /= colonnes.size();
        System.out.printf("Remplissage moyen : %.2f\n", moy);
    }

    /**
     * Renvoie le participant ayant gagne si il existe
     * Une methode plus efficace pour chercher un gagnant aurait ete de verifier si le dernier jeton pose donne
     * la victoire plutot que de rechercher dans tous la grille à chaque fois
     *
     * @return le participant ayant gagné
     */
    public Participant avoirGagnant() {
        if(this.avoirQuatreAlignesColonne() != null) {
            return this.avoirQuatreAlignesColonne();
        }
        if(this.avoirQuatreAlignesLigne() != null) {
            return this.avoirQuatreAlignesLigne();
        }
        if(this.avoirQuatreAlignesDiagonale() != null) {
            return this.avoirQuatreAlignesDiagonale();
        }
        return null;
    }

    /**
     * Detecte si quatre jetons d'un meme joueur sont alignes verticalement
     *
     * @return le prorietaire des jetons alignes
     * @see Participant
     */
    public Participant avoirQuatreAlignesColonne() {
        for(Colonne c : this.colonnes) {
            int count = 1;
            if(c.size() >= 4) {
                Participant prec = c.getJetons().get(0).getProprietaire();
                for(int i = 1; i<c.size(); i++) {
                    if(c.getJetons().get(i).getProprietaire().equals(prec)) {
                        count++;
                    } else {
                        count = 1;
                    }
                    if(count == 4) {
                        return prec;
                    }
                    prec = c.getJetons().get(i).getProprietaire();
                }
            }
        }
        return null;
    }

    /**
     * Detecte si quatre jetons d'un meme joueur sont alignes horizontalement
     *
     * @return le proprietaire des jetons alignes
     * @see Participant
     */
    public Participant avoirQuatreAlignesLigne() {
        for(int i = 0; i<=hauteur; i++) {
            int count = 1;
            if(this.colonnes.get(0).getJetons().size()>i) {
                Participant prec = this.colonnes.get(0).getJetons().get(i).getProprietaire();
                for(int j = 1; j<largeur; j++) {
                    if(this.colonnes.get(j).getJetons().size()>i) {
                        if(this.colonnes.get(j).getJetons().get(i).getProprietaire().equals(prec)) {
                            count++;
                        } else {
                            count = 1;
                        }
                        if(count == 4) {
                            return prec;
                        }
                        prec = this.colonnes.get(j).getJetons().get(i).getProprietaire();
                    } else {
                        count = 0;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Detecte si quatre jetons d'un meme jouer sont alignes en diagonale
     *
     * @return le proprietaire des jetons alignes
     * @see Participant
     */
    public Participant avoirQuatreAlignesDiagonale() {
        //Cherche les diagonales de gauche à droite
        for(int i = 0; i<=largeur-4; i++) {
            for(int j = 0; j<this.colonnes.get(i).getJetons().size(); j++) {
                Participant current = this.colonnes.get(i).getJetons().get(j).getProprietaire();
                if(this.colonnes.get(i+1).getJetons().size()>j+1
                        && this.colonnes.get(i+2).getJetons().size()>j+2
                        && this.colonnes.get(i+3).getJetons().size()>j+3) {
                    if(this.colonnes.get(i+1).getJetons().get(j+1).getProprietaire().equals(current)
                            && this.colonnes.get(i+2).getJetons().get(j+2).getProprietaire().equals(current)
                            && this.colonnes.get(i+3).getJetons().get(j+3).getProprietaire().equals(current)) {
                        return current;
                    }
                }
            }
        }
        //On cherche les diagonales de droite à gauche
        for(int i = largeur-1; i >= 3; i--) {
            for(int j = 0; j<this.colonnes.get(i).getJetons().size(); j++) {
                Participant current = this.colonnes.get(i).getJetons().get(j).getProprietaire();
                if(this.colonnes.get(i-1).getJetons().size()>j+1
                        && this.colonnes.get(i-2).getJetons().size()>j+2
                        && this.colonnes.get(i-3).getJetons().size()>j+3) {
                    if(this.colonnes.get(i-1).getJetons().get(j+1).getProprietaire().equals(current)
                            && this.colonnes.get(i-2).getJetons().get(j+2).getProprietaire().equals(current)
                            && this.colonnes.get(i-3).getJetons().get(j+3).getProprietaire().equals(current)) {
                        return current;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Retourne la colonne la plus remplie de la grille
     *
     * @return colonne la plus remplie de la grille
     */
    public Colonne trouverColonnePlusRemplie() {
        return this.colonnesTriees.get(0);
    }

    /**
     * Ajoute un jeton dans la colonne donnee en parametre et met a jour la hauteur de la grille
     *
     * @param current Participant placant le jeton
     * @param col Colonne dans laquelle on ajoute le jeton
     * 
     * @see Participant
     */
    public void ajouterJeton(Participant current, int col) {
        this.colonnes.get(col).getJetons().add(new Jeton(current));
        this.trierListe();
        this.hauteur = trouverColonnePlusRemplie().size()-1;
    }

    /**
     * Retire un jeton de la colonne donnee en parametre
     * @param col colonne a laquelle on retire le jeton
     */
    public void retirerJeton(int col){
        this.colonnes.get(col).getJetons().remove(this.colonnes.get(col).size()-1);
        this.trierListe();
        this.hauteur = trouverColonnePlusRemplie().size()-1;
    }

    /**
     * Retourne la largeur de la grille
     *
     * @return largeur de la grille
     */
    public int getLargeur() {
        return this.largeur;
    }

    /**
     * Retourne la liste des colonnes
     * 
     * @return liste des colonnes
     */
    public List<Colonne> getColonnes(){
    	return this.colonnes;
    }
    
    /**
     * Retourne la hauteur de la grille
     * 
     * @return hauteur de la grille 
     */
    public int getHauteur() {
    	return this.hauteur;
    }
    
    @Override
    public String toString() {
        String s = "|";
        for(int i = 1; i<=largeur; i++) {
            s += i+"|";
        }
        s += "\n";
        for(int i = 1; i<=largeur; i++) {
            s += "-+";
        }
        s += "-\n";
        for(int i = hauteur; i >= 0; i--) {
            s+="|";
            for(Colonne col : colonnes) {
                if(col.getJetons().size()<i+1) {
                    s += " |";
                } else {
                    s += col.getJetons().get(i).toString()+"|";
                }
            }
            s += "\n";
        }
        return s;
    }
}
