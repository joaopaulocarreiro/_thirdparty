/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.exceptions;

/**
 */
public class LanguageAlreadyExistsException extends Exception {

    /**
     * Creates a new instance of <code>LanguageAlreadyExistsException</code> without detail message.
     */
    public LanguageAlreadyExistsException() {
    }

    /**
     * Constructs an instance of <code>LanguageAlreadyExistsException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public LanguageAlreadyExistsException(String msg) {
        super(msg);
    }
}
