/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.service;

/**
 * @author Joao Paulo Carreiro.
 */
public class servUser implements iServUser {

    /* support structures. */
    private org.fcsolutions.jlib.bean.cBeanContainer systemUsers;

    /**
     * Initilize/Construct Service Layer.
     */
    public servUser() {
        /* initialize bean container. */
        systemUsers = new org.fcsolutions.jlib.bean.cBeanContainer();
    }

    /**
     * @see walkin.service.iServUser.insertUser
     */
    public void insertUser(walkin.model.bUser user) throws Exception {

        /* check arguments. */
        assert (user != null);
        assert (user.getUsername() != null);
        assert (!user.getUsername().isEmpty());
        assert (user.getPassword() != null);

        /* insert user. */
        systemUsers.insert(user);
    }

    /**
     * @see walkin.service.iServUser.deleteUser
     */
    public void deleteUser(walkin.model.bUser user) throws Exception {

        /* check arguments. */
        assert (user != null);
        assert (user.getUsername() != null);
        assert (!user.getUsername().isEmpty());

        /* delete user. */
        systemUsers.delete(user);
    }

    /**
     * @see walkin.service.iServUser.updateUser
     */
    public void updateUser(walkin.model.bUser user) throws Exception {

        /* check arguments. */
        assert (user != null);
        assert (user.getUsername() != null);
        assert (!user.getUsername().isEmpty());

        /* update user. */
        systemUsers.update(user);
    }

    /**
     * @see walkin.service.iServUser.validateUser
     */
    public boolean validateUser(walkin.model.bUser user) throws Exception {

        /* argument check. */
        assert (user != null);
        assert (user.getUsername() != null);
        assert (!user.getUsername().isEmpty());
       
        /* check it. */
        walkin.model.bUser elem = (walkin.model.bUser) systemUsers.get(user);
        return elem.getPassword().equals(user.getPassword());
    }
}

