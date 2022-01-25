/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.model;

/**
 * Mapping/Implementation of identification kinds.
 * @author Joao Paulo Carreiro
 */
public class bPersonIdKind extends org.fcsolutions.jlib.bean.aBean {

    /* fields. */
    private String description;

    /* mutators. */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
