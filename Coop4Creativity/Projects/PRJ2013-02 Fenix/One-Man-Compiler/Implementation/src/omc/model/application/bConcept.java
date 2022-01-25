/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name bConcept
 * @version
 * @description
 * @date 29/Ago/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */
package omc.model.application;

public class bConcept {

    /** Constructor. */
    public bConcept() {
        entityList = new java.util.Hashtable<String, bEntity>();
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
     * Get the value of entityList
     *
     * @return the value of entityList
     */
    public java.util.Map<String, bEntity> getEntityList() {
        return entityList;
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
     * Set the value of entityList
     *
     * @param entityList new value of entityList
     */
    public void setEntityList(java.util.Map<String, bEntity> entityList) {
        this.entityList = entityList;
    }

    /**
     * Add a new entity to this concept
     *
     * @param entity the entity to add
     */
    public void add(bEntity entity) {

        /* check arguments. */
        assert (entity != null);

        /* check if the entity already exists. */
        if (entityList.containsKey(entity.getName())) return;

        /* all ok, insert it.*/
        entityList.put(entity.getName(), entity);
    }

    /**
     * Get a entity by name.
     *
     * @param name the name of the entity to get
     * @return the entity object, or null if it does not exit
     */
    public bEntity get(String name) {
        return entityList.get(name);
    }

    /**
     * Get an iterator of the entities in this concept.
     *
     * @return the entity iterator.
     */
    public java.util.Iterator<bEntity> entityList () {
        return entityList.values().iterator();
    }

    /** Name for concept. */
    private String name = null;
    
    /** List of associated entities. */
    protected java.util.Map<String, bEntity> entityList = null;
}
