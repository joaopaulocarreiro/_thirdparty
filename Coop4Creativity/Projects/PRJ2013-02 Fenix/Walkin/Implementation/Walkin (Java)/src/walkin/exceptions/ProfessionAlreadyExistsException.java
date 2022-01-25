/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.exceptions;

/**
 * @author Joao Paulo Carrreiro
 */
public class ProfessionAlreadyExistsException extends Exception {

    /**
     * Creates a new instance of <code>UserAlreadyExistsException</code>
     * without detail message.
     */
    public ProfessionAlreadyExistsException() {
    }

    /**
     * Constructs an instance of <code>UserAlreadyExistsException</code>
     * with the specified detail message.
     * @param msg the detail message.
     */
    public ProfessionAlreadyExistsException(String msg) {
        super(msg);
    }
}
