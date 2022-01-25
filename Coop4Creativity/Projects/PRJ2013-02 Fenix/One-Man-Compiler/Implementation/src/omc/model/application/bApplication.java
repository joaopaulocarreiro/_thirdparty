/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name bApplication
 * @version
 * @description
 * @date 29/Ago/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */

package omc.model.application;

public class bApplication {

    /** Constructor. */
    public bApplication() {
        conceptList = new java.util.Hashtable<String, bConcept>();
    }
    
    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the value of version
     *
     * @return the value of version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Get the value of conceptList
     *
     * @return the value of conceptList
     */
    public java.util.Map<String, bConcept> getConceptList() {
        return conceptList;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the value of version
     *
     * @param version new value of version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Set the value of conceptList
     *
     * @param conceptList new value of conceptList
     */
    public void setConceptList(java.util.Map<String, bConcept> conceptList) {
        this.conceptList = conceptList;
    }

    /**
     * Add a new concept to this application
     *
     * @param concept the concept to add
     */
    public void add(bConcept concept) {

        /* check arguments. */
        assert (concept != null);

        /* check if concept already exists. */
        if (conceptList.containsKey(concept.getName())) return;

        /* all ok, insert it.*/
        conceptList.put(concept.getName(), concept);
    }

    /**
     * Get a concept by name.
     *
     * @param name the name of the concept to get
     * @return the concept object, or null if it does not exit
     */
    public bConcept get(String name) {
        return conceptList.get(name);
    }

    /**
     * Get an iterator of the concepts in this applications.
     *
     * @return the concept iterator.
     */
    public java.util.Iterator<bConcept> conceptList () {
        return conceptList.values().iterator();
    }

    /** Name of application. */
    private String name = null;

    /** Version of application. */
    private String version = null;

    /** List of concepts included. */
    private java.util.Map<String, bConcept> conceptList = null;
}
