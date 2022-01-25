/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name bModel
 * @version
 * @description
 * @date 29/Ago/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */

package omc.domain.model;

import java.util.Iterator;
import java.util.Map;

public class bModel {

    /** Constructor. */
    public bModel() {
        setConceptList(new java.util.Hashtable<String, bConcept>());
    }
    
    /**
     * Get the value of name
     * @return the value of name
     */
    public String getName() {
        return _name;
    }

    /**
     * Get the value of version
     * @return the value of version
     */
    public String getVersion() {
        return _version;
    }

    /**
     * Get the value of the concept list map.
     * @return the value of conceptList
     */
    public Map<String, bConcept> getConceptList() {
        return _conceptMap;
    }

    /**
     * Set the value of name
     * @param name new value of name
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * Set the value of version
     * @param version new value of version
     */
    public void setVersion(String version) {
        this._version = version;
    }

    /**
     * Set the value of conceptList
     * @param conceptList new value of conceptList
     */
    public void setConceptList(java.util.Map<String, bConcept> conceptList) {
        this._conceptMap = conceptList;
    }

    /**
     * Add a new concept to this application
     * @param concept the concept to add
     */
    public void add(bConcept concept) {

        /* check arguments. */
        assert (concept != null);

        /* check if concept already exists. */
        if (getConceptList().containsKey(concept.getName())) return;

        /* all ok, insert it.*/
        getConceptList().put(concept.getName(), concept);
    }

    /**
     * Get a concept by name.
     * @param name the name of the concept to get
     * @return the concept object, or null if it does not exit
     */
    public bConcept get(String name) {
        return getConceptList().get(name);
    }

    /**
     * Get an iterator of the concepts in this applications.
     * @return the concept iterator.
     */
    public Iterator<bConcept> conceptItr () {
        return getConceptList().values().iterator();
    }

    /* name of application. */
    private String _name = null;

    /* version of application. */
    private String _version = null;

    /* list of concepts included. */
    private Map<String, bConcept> _conceptMap = null;
}
