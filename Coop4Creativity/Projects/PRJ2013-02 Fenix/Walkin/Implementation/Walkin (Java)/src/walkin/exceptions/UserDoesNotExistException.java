/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.exceptions;

/**
 * @author Joao Paulo Carreiro
 */
public class UserDoesNotExistException extends Exception {

    /**
     * Creates a new instance of <code>UserDoesNotExistException</code>
     * without detail message.
     */
    public UserDoesNotExistException() {
    }

    /**
     * Constructs an instance of <code>UserDoesNotExistException</code>
     * with the specified detail message.
     * @param msg the detail message.
     */
    public UserDoesNotExistException(String msg) {
        super(msg);
    }
}
