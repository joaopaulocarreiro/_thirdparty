/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.model;

/**
 * Mapping/Implementation of Customer fax's.
 * @author Joao Paulo Carreiro
 */
public class bCustomerFax extends org.fcsolutions.jlib.bean.aBean {

    /* bean fields. */
    private bCustomer customer;
    private String fax;

    /* mutators. */
    public String getFax() {
        return fax;
    }

    public void setFax(String phone) {
        this.fax = phone;
    }

    public bCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(bCustomer customer) {
        this.customer = customer;
    }
}
