/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.service;

import walkin.model.bUser;

/**
 * Service Layer for system users.
 * @author Joao Paulo Carreiro
 */
public interface iServUser {

    /**
     * Add a user to the system.
     * @param user The user to add.
     * @throws Exception If something goes wrong.
     */
    void insertUser(bUser user) throws Exception;

    /**
     * Delete a user from the system.
     * @param user The user to remove.
     * @throws Exception If the user does not exist or some other problem.
     */
    void deleteUser(bUser user) throws Exception;

    /**
     * Update information relative to a certain user.
     * @param user The user to change.
     * @throws Exception in case of error.
     */
    void updateUser(bUser user) throws Exception;

    /**
     * Check if a user is valid, i.e. the username and password
     * are known by the system.
     * @param user The user to check,
     * @return True is user is valid, false otherwise.
     * @throws Exception in case of error.
     */
    boolean validateUser(bUser user) throws Exception;
}
