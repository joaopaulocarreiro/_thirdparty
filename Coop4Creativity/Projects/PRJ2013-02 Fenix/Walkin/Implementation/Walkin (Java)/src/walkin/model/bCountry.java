/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.model;

/**
 * Mapping/Implementation of Country related information.
 * Countries have basically names and a language associated with it.
 * @author Joao Paulo Carreiro
 */
public class bCountry extends org.fcsolutions.jlib.bean.aBean {

    /* bean fields. */
    private bLanguage language;

    public bLanguage getLanguage() {
        return language;
    }

    public void setLanguage(bLanguage language) {
        this.language = language;
    }
}
