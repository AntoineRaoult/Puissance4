/**
 * Represente l'exception levee quand le joueur fait un choix qui n'est pas propose
 */
public class ChoixInvalideException extends Exception {

    public ChoixInvalideException() {
        super();
    }

    public ChoixInvalideException(String message) {
        super(message);
    }
}
