/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.service;

import walkin.model.bCustomer;

/**
 * Customer Service Layer.
 * @author Joao Paulo Carreiro
 */
public class servCustomer implements iServCustomer {

    /* private data. */
    private org.fcsolutions.jlib.bean.cBeanContainer tb_customer;
    private org.fcsolutions.jlib.bean.cBeanContainer tb_phone;
    private org.fcsolutions.jlib.bean.cBeanContainer tb_mobile;
    private org.fcsolutions.jlib.bean.cBeanContainer tb_email;
    private org.fcsolutions.jlib.bean.cBeanContainer tb_creditCard;

    /**
     * Constructor.
     */
    public servCustomer() {
        tb_customer = new org.fcsolutions.jlib.bean.cBeanContainer();
        tb_phone = new org.fcsolutions.jlib.bean.cBeanContainer();
        tb_mobile = new org.fcsolutions.jlib.bean.cBeanContainer();
        tb_email = new org.fcsolutions.jlib.bean.cBeanContainer();
        tb_creditCard = new org.fcsolutions.jlib.bean.cBeanContainer();
    }

    /**
     * @see walkin.service.iServCustomer
     */
    public void insertCustomer(
            walkin.model.bCustomer cust,
            java.util.List<String> phone,
            java.util.List<String> mobile,
            java.util.List<String> email) throws Exception {

        /* insert customer main data. */
        tb_customer.insert(cust);

        /* insert phone numbers. */
        {
            java.util.Iterator<String> itr = phone.iterator();
            while (itr.hasNext()) {
                String elm = itr.next();
                walkin.model.bCustomerPhone bean = new walkin.model.bCustomerPhone();
                bean.setCustomer(cust);
                bean.setPhone(elm);
                tb_phone.insert(bean);
            }    
        }

        /* insert mobile numbers. */
        {
            java.util.Iterator<String> itr = mobile.iterator();
            while (itr.hasNext()) {
                String elm = itr.next();
                walkin.model.bCustomerMobile bean = new walkin.model.bCustomerMobile();
                bean.setCustomer(cust);
                bean.setMobile(elm);
                tb_mobile.insert(bean);
            }
        }

        /* insert emails. */
        {
            java.util.Iterator<String> itr = email.iterator();
            while (itr.hasNext()) {
                String elm = itr.next();
                walkin.model.bCustomerEmail bean = new walkin.model.bCustomerEmail();
                bean.setCustomer(cust);
                bean.setEmail(elm);
                tb_email.insert(bean);
            }
        }
    }

    /**
     * @see walkin.service.iServCustomer
     */
    public void deleteCustomer(bCustomer cust) throws Exception {
        tb_customer.delete(cust);
    }

    /**
     * @see walkin.service.iServCustomer
     */
    public void updateCustomer(bCustomer cust) throws Exception {
        tb_customer.update(cust);
    }
}
