/**
 * <p>Represente un bot</p>
 * <p>Classe abstraite</p>
 * <p>Herites de Participant</p>
 *
 * Le but de cette classe abstraite est de pouvoir creer plusieurs bots dont le comportement sera different
 * Par exemple :
 * <ul>
 *     <li>un bot qui joue aleatoirement a chaque tours,</li>
 *     <li>un bot qui bloque le joueur des qu'il aligne 3 jetons,</li>
 *     <li>un bot qui detecte quand 3 de ses jetons sont alignes pour placer dernier au bon endroit</li>
 *     <li>etc ...</li>
 *</ul>
 * @see Participant
 */
public abstract class Bot extends Participant {

    /**
     * Creer un Bot
     */
    public Bot(){
        super();
    }

   @Override
    public String toString() {
        return super.toString()+" : Bot";
    }
}
