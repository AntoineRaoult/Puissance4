/**
 * Represente l'exception levee quand un joueur veut placer un jeton dans une colonne qui n'existe pas
 */
public class ColonneInexistanteException extends Exception {

    public ColonneInexistanteException() {
        super();
    }

    public ColonneInexistanteException(String message) {
        super(message);
    }
}
