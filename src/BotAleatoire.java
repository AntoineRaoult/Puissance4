/**
 * <p>Represente un BotAleatoire</p>
 * <p>Herites de Bot</p>
 *
 * @see Bot
 */
public class BotAleatoire extends Bot {

    /**
     * Creer un BotAleatoire
     */
    public BotAleatoire() {
        super();
    }

    @Override
    public int jouer(Grille g) {
        int val = (int)(Math.random() * ((g.getLargeur() - 1) + 1)) + 1;
        System.out.println(this.toString() + " a joue en colonne " + val);
        return val;
    }

    @Override
    public String getNom() {
        return "BotAleatoire";
    }

    @Override
    public String toString(){
        return super.toString()+"Aleatoire";
    }
}
