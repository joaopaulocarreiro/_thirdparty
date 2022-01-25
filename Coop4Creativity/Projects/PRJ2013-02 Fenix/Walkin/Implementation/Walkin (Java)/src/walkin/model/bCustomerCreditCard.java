/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.model;

import java.util.Date;

/**
 * Mapping/Implementation of Customer Credit card information.
 * @author Joao Paulo Carreiro
 */
public class bCustomerCreditCard extends org.fcsolutions.jlib.bean.aBean {

    /* fields. */
    private bCustomer customer;
    private String number;
    private Date expiresOn;
    private String name;

    /* mutators. */
    public bCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(bCustomer customer) {
        this.customer = customer;
    }

    public Date getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(Date expiresOn) {
        this.expiresOn = expiresOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
