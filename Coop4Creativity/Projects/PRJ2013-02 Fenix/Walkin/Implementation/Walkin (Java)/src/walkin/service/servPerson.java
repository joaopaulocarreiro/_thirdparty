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
public class servPerson implements iServPerson {

    /* support structures. */
    private org.fcsolutions.jlib.bean.cBeanContainer idkinds;
    private org.fcsolutions.jlib.bean.cBeanContainer idtitles;
    private org.fcsolutions.jlib.bean.cBeanContainer professions;

    /**
     * Initilize/Construct Service Layer.
     */
    public servPerson() {
        /* initialize support strucutres. */
        idkinds = new org.fcsolutions.jlib.bean.cBeanContainer();
        idtitles = new org.fcsolutions.jlib.bean.cBeanContainer();
        professions = new org.fcsolutions.jlib.bean.cBeanContainer();
    }

    /**
     * @see walkin.service.iServPerson
     */
    public void insertPersonIdKind(walkin.model.bPersonIdKind idkind) throws Exception {
        idkinds.insert(idkind);
    }

    /**
     * @see walkin.service.iServPerson
     */
    public java.util.List<walkin.model.bPersonIdKind> getPersonIdKindList() throws Exception {
        return (java.util.List) idkinds.getAll();
    }

    /**
     * @see walkin.service.iServPerson
     */
    public void insertPersonTitle(walkin.model.bPersonTitle title) throws Exception {
        idtitles.insert(title);
    }

    /**
     * @see walkin.service.iServPerson
     */
    public java.util.List<walkin.model.bPersonTitle> getPersonTitleList() throws Exception {
        return (java.util.List) idtitles.getAll();
    }

    /**
     * @see walkin.service.iServPerson
     */
    public void insertPersonProfession(walkin.model.bPersonProfession prof) throws Exception {
        professions.insert(prof);    }

    /**
     * @see walkin.service.iServPerson
     */
    public java.util.List<walkin.model.bPersonProfession> getPersonProfessionList() throws Exception {
        return (java.util.List) professions.getAll();
    }
}

