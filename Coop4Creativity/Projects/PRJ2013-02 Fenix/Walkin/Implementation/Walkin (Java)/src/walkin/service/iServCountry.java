/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.service;

import walkin.model.bCountry;
import walkin.model.bLanguage;

/**
 */
public interface iServCountry {

    /**
     * Inserir informação relativa a um país.
     * @param country The country to insert.
     */
    void insertCountry(bCountry country) throws Exception;

    /**
     * Insert a new language in the system.
     * Tuple form:
     *      name :: String (name of language)
     * @param lang The language to insert.
     */
    void insertLanguage(bLanguage language) throws Exception;

    /**
     * Retrieve a list with all the countries inserted in the system.
     * Tuple form:
     *      name :: String (name of country)
     * @return The list with countries.
     */
    java.util.List<bCountry> getCountryList() throws Exception;

    /**
     * Retrieve the list with all the languages of the system.
     * Tuple form:
     *      name :: String (name of language)
     * @return The list with the languages.
     */
    java.util.List<bLanguage> getLanguageList() throws Exception;
}
