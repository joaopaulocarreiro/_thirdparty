/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.service;

/**
 * Service Layer for application.
 * @author Joao Paulo Carreiro
 */
public interface iServMain {

    /**
     * Get the country service layer.
     * @return the country service layer.
     */
    iServCountry getCountryService();
 
    /**
     * Get the user service layer.
     * @return the user service layer.
     */
    iServUser getUserService();

    /**
     * Get the identification kind service layer.
     * @return the identification kind service layer.
     */
    iServPerson getPersonService();

    /**
     * Get the customer service layer.
     * @return the customer service layer.
     */
    iServCustomer getCustomerService();
}
