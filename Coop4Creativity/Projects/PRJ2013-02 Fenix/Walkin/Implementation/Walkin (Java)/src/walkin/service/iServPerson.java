/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.service;

/**
 * Service layer for persons.
 * @author João Paulo Carreiro
 */
public interface iServPerson {

    /**
     * Insert a new identification kind.
     * @param idkind the new identification kind.
     */
    void insertPersonIdKind(walkin.model.bPersonIdKind idkind) throws Exception;

    /**
     * Retrieve a list with all the identification kinds.
     * @return The list with the identification kinds.
     */
    java.util.List<walkin.model.bPersonIdKind> getPersonIdKindList() throws Exception;

    /**
     * Insert a new identification title.
     * @param title the new identification title.
     */
    void insertPersonTitle(walkin.model.bPersonTitle title) throws Exception;

    /**
     * Retrieve a list with all the identification titles.
     * @return The list with the identification titles.
     */
    java.util.List<walkin.model.bPersonTitle> getPersonTitleList() throws Exception;

    /**
     * Insert a new identification title.
     * @param title the new identification title.
     */
    void insertPersonProfession(walkin.model.bPersonProfession title) throws Exception;

    /**
     * Retrieve a list with all the identification titles.
     * @return The list with the identification titles.
     */
    java.util.List<walkin.model.bPersonProfession> getPersonProfessionList() throws Exception;

}
