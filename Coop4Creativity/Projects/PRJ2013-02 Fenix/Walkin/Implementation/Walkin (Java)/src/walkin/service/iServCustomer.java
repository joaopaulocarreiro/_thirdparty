/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.service;

/**
 * Service Layer for customers.
 * @author Joao Paulo Carreiro
 */
public interface iServCustomer {

    /**
     * Insert a new customer in system.
     * @param cust The customer main information.
     * @param phone list of additional phone numbers.
     * @param mobile list of additional mobile phone numbers.
     * @param email list of additional emails.
     * @throws Exception
     */
    void insertCustomer(
            walkin.model.bCustomer cust,
            java.util.List<String> phone,
            java.util.List<String> mobile,
            java.util.List<String> email) throws Exception;

    /**
     * Delete customer from system
     * @param cust the customer to delete.
     * @throws Exception
     */
    void deleteCustomer(walkin.model.bCustomer cust) throws Exception;

    /**
     * Update customer information.
     * @param cust the customer to update.
     * @throws Exception
     */
    void updateCustomer(walkin.model.bCustomer cust) throws Exception;
}
