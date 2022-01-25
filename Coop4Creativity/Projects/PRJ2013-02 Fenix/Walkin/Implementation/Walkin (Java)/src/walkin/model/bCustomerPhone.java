/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.model;

/**
 * Mapping of Customer phone information.
 * @author Joao Paulo Carreiro
 */
public class bCustomerPhone extends org.fcsolutions.jlib.bean.aBean {

    /* bean fields. */
    private bCustomer customer;
    private String phone;

    /* mutators. */
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public bCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(bCustomer customer) {
        this.customer = customer;
    }
}
