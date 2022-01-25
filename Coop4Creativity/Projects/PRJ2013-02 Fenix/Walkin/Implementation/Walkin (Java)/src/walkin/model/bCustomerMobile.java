/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.model;

/**
 * Mapping/Implementation of Customer mobile information.
 * @author Joao Paulo Carreiro
 */
public class bCustomerMobile extends org.fcsolutions.jlib.bean.aBean {

    /* bean fields. */
    private bCustomer customer;
    private String mobile;

    /* mutators. */
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String phone) {
        this.mobile = phone;
    }

    public bCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(bCustomer customer) {
        this.customer = customer;
    }
}
