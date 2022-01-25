/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.model;

/**
 * User bean.
 * Mapping/Implementation of User information.
 * @author Joao Paulo Carreiro
 */
public class bUser extends org.fcsolutions.jlib.bean.aBean {

    /* bean fields. */
    private String username;
    private String password;
    private bUserGroup group;

    /* mutators. */
    public bUserGroup getGroup() {
        return group;
    }

    public void setGroup(bUserGroup group) {
        this.group = group;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
