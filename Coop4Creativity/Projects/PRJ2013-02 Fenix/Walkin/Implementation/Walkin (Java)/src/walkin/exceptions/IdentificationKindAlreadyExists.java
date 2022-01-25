/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.exceptions;

/**
 * @author Joao Paulo
 */
public class IdentificationKindAlreadyExists extends Exception {

    /**
     * Creates a new instance of <code>IdentificationKindAlreadyExists</code> without detail message.
     */
    public IdentificationKindAlreadyExists() {
    }

    /**
     * Constructs an instance of <code>IdentificationKindAlreadyExists</code> with the specified detail message.
     * @param msg the detail message.
     */
    public IdentificationKindAlreadyExists(String msg) {
        super(msg);
    }
}
