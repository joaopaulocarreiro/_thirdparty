/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin.service;

import walkin.model.bCountry;
import walkin.model.bPersonIdKind;
import walkin.model.bLanguage;
import walkin.model.bPersonProfession;
import walkin.model.bPersonTitle;
import walkin.model.bUser;

/**
 * Implementation class for the servMain layer.
 * This implementation is based on in-memory data structures,
 * there is no connection to the database here.
 * Mainly, this class is for testing purposes, i.e. testing of
 * the application framework.
 * @author Joao Paulo Carreiro.
 */
public class servMain implements iServMain {

    /*** sub-service layers. */
    private iServUser servUser;
    private iServCountry servCountry;
    private iServPerson servPerson;
    private iServCustomer servCustomer;

    /**
     * Initilize/Construct servMain Layer.
     */
    public servMain() {
        /* */
        servUser = new servUser();
        servCountry = new servCountry();
        servPerson = new servPerson();
        servCustomer = new servCustomer();

        /* add some users to emulate database access. */
        bUser userJoao = new bUser();
        userJoao.setUsername("joao");
        userJoao.setPassword("pipepipe");

        bUser userFragata = new bUser();
        userFragata.setUsername("fragata");
        userFragata.setPassword("benfica");

        bUser userGuest = new bUser();
        userGuest.setUsername("guest");
        userGuest.setPassword("guest");

        /* add some languages. */
        bLanguage portugueseLang = new bLanguage();
        portugueseLang.setName("Português");

        bLanguage englishLang = new bLanguage();
        englishLang.setName("Inglês");

        bLanguage germanLang = new bLanguage();
        germanLang.setName("Alemão");

        bLanguage frenchLang = new bLanguage();
        frenchLang.setName("Françês");

        /* add some countries. */
        bCountry portugalCountry = new bCountry();
        portugalCountry.setName("Portugal");
        portugalCountry.setLanguage(portugueseLang);

        bCountry englandCountry = new bCountry();
        englandCountry.setName("Inglaterra");
        englandCountry.setLanguage(englishLang);

        bCountry germanyCountry = new bCountry();
        germanyCountry.setName("Alemanha");
        germanyCountry.setLanguage(germanLang);

        /* sample identification kinds. */
        bPersonIdKind idIdKind = new bPersonIdKind();
        idIdKind.setName("Bilhete de identidade");

        bPersonIdKind ccIdKind = new bPersonIdKind();
        ccIdKind.setName("Cartão de Cidadão");

        bPersonIdKind otherIdKind = new bPersonIdKind();
        otherIdKind.setName("Outro");

        /* sample identification titles. */
        bPersonTitle mrTitle = new bPersonTitle();
        mrTitle.setName("Sr.");

        bPersonTitle mrsTitle = new bPersonTitle();
        mrsTitle.setName("Sra.");

        /* sample professions. */
        bPersonProfession prof1 = new bPersonProfession();
        prof1.setName("Engenheiro Civil");

        bPersonProfession prof2 = new bPersonProfession();
        prof2.setName("Profissão Liberal");

        bPersonProfession prof3 = new bPersonProfession();
        prof3.setName("Carpinteiro");

        bPersonProfession prof4 = new bPersonProfession();
        prof4.setName("Outro");

        /* add predefined information. */
        try {
            servUser.insertUser(userJoao);
            servUser.insertUser(userFragata);
            servUser.insertUser(userGuest);

            servCountry.insertLanguage(portugueseLang);
            servCountry.insertLanguage(englishLang);
            servCountry.insertLanguage(germanLang);
            servCountry.insertLanguage(frenchLang);

            servCountry.insertCountry(portugalCountry);
            servCountry.insertCountry(englandCountry);
            servCountry.insertCountry(germanyCountry);

            servPerson.insertPersonIdKind(idIdKind);
            servPerson.insertPersonIdKind(ccIdKind);
            servPerson.insertPersonIdKind(otherIdKind);

            servPerson.insertPersonTitle(mrTitle);
            servPerson.insertPersonTitle(mrsTitle);

            servPerson.insertPersonProfession(prof1);
            servPerson.insertPersonProfession(prof2);
            servPerson.insertPersonProfession(prof3);
            servPerson.insertPersonProfession(prof4);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public iServCountry getCountryService() {
        return this.servCountry;
    }

    public iServUser getUserService() {
        return this.servUser;
    }

    public iServPerson getPersonService() {
        return this.servPerson;
    }

    public iServCustomer getCustomerService() {
        return this.servCustomer;
    }
}

