/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.model;

/**
 * Mapping/Implementation of Customer email information.
 * @author Joao Paulo Carreiro
 */
public class bCustomerEmail extends org.fcsolutions.jlib.bean.aBean {

    /* bean fields. */
    private bCustomer customer;
    private String email;

    /* mutators. */
    public bCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(bCustomer customer) {
        this.customer = customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
