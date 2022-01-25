/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.model;

/**
 * Mapping/Implementation of Customer information.
 * @author Joao Paulo Carreiro
 */
public class bCustomer extends org.fcsolutions.jlib.bean.aBean {

    /* personal information. */
    private walkin.model.bPersonTitle title;
    private String firstName;
    private String lastName;
    private String nationality;
    private walkin.model.bLanguage language;
    private java.util.Date dateOfBirth;
    private String taxNumber;
    private walkin.model.bPersonIdKind idKind;
    private String idNumber;

    /* address iformation. */
    private String address;
    private String city;
    private String zipCode;
    private walkin.model.bCountry country;

    /* professional information. */
    private walkin.model.bPersonProfession profession;
    private String company;
    private String contact;

    /* additional information. */
    private String observations;

    /* mutators. */
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public walkin.model.bCountry getCountry() {
        return country;
    }

    public void setCountry(walkin.model.bCountry country) {
        this.country = country;
    }

    public java.util.Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(java.util.Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public walkin.model.bPersonIdKind getIdKind() {
        return idKind;
    }

    public void setIdKind(walkin.model.bPersonIdKind idKind) {
        this.idKind = idKind;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public walkin.model.bLanguage getLanguage() {
        return language;
    }

    public void setLanguage(walkin.model.bLanguage language) {
        this.language = language;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public walkin.model.bPersonProfession getProfession() {
        return profession;
    }

    public void setProfession(walkin.model.bPersonProfession profession) {
        this.profession = profession;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public walkin.model.bPersonTitle getTitle() {
        return title;
    }

    public void setTitle(walkin.model.bPersonTitle title) {
        this.title = title;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
