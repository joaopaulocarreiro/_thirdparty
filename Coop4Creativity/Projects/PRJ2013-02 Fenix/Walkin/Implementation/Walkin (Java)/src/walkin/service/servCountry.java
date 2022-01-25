/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.service;

/**
 * Implementation class for the Service layer.
 * This implementation is based on in-memory data structures,
 * there is no connection to the database here.
 * Mainly, this class is for testing purposes, i.e. testing of
 * the application framework.
 * @author Joao Paulo Carreiro.
 */
public class servCountry implements iServCountry {

    /* support structures. */
    private org.fcsolutions.jlib.bean.cBeanContainer countries;
    private org.fcsolutions.jlib.bean.cBeanContainer languages;

    /**
     * Initilize/Construct Service Layer.
     */
    public servCountry() {
        /* initialize support strucutres. */
        languages = new org.fcsolutions.jlib.bean.cBeanContainer();
        countries = new org.fcsolutions.jlib.bean.cBeanContainer();
    }

    /**
     * @see walkin.service.iServCountry.getCountryList
     */
    public java.util.List<walkin.model.bCountry> getCountryList() throws Exception {
        return (java.util.List) countries.getAll();
    }

    /**
     * @see walkin.service.iServCountry.getLanguageList
     */
    public java.util.List<walkin.model.bLanguage> getLanguageList() throws Exception {
        return (java.util.List) languages.getAll();
    }

    /**
     * @see walkin.service.iServCountry.insertCountry
     */
    public void insertCountry(walkin.model.bCountry country) throws Exception {
        countries.insert(country);
    }

    /**
     * @see walkin.service.iServCountry.insertLanguage
     */
    public void insertLanguage(walkin.model.bLanguage lang) throws Exception {
        languages.insert(lang);
    }
}

