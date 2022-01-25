/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.exceptions;

/**
 */
public class CountryAlreadyExistsException extends Exception {

    /**
     * Creates a new instance of <code>CountryAlreadyExistsException</code> without detail message.
     */
    public CountryAlreadyExistsException() {
    }

    /**
     * Constructs an instance of <code>CountryAlreadyExistsException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public CountryAlreadyExistsException(String msg) {
        super(msg);
    }
}
