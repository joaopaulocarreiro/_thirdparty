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
package omc.domain.model;

import java.util.Iterator;
import java.util.Map;

public class bConcept {

    /**
     * Constructor.
     * Initialization of object.
     */
    public bConcept() {
        setEntityMap(new java.util.Hashtable<String, bEntity>());
    }

    /**
     * Get the value of name.
     * @return the value of name
     */
    public String getName() {
        return _name;
    }

    /**
     * Get the value for the map of entities.
     * @return the value of entityList
     */
    public Map<String, bEntity> getEntityMap() {
        return _entityMap;
    }

    /**
     * Set the value of name
     * @param name new value of name
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * Set the value for the entity map structure.
     * @param map new value of entityList
     */
    public void setEntityMap(Map<String, bEntity> map) {
        this._entityMap = map;
    }

    /**
     * Add a new entity to this concept
     * @param entity the entity to add
     */
    public void add(bEntity entity) {

        /* check arguments. */
        assert (entity != null);

        /* check if the entity already exists. */
        if (getEntityMap().containsKey(entity.getName())) return;

        /* all ok, insert it.*/
        getEntityMap().put(entity.getName(), entity);
    }

    /**
     * Get a entity by name.
     * @param name the name of the entity to get
     * @return the entity object, or null if it does not exit
     */
    public bEntity get(String name) {

        /* check the argument. */
        assert(name != null);

        return getEntityMap().get(name);
    }

    /**
     * Get an iterator of the entities in this concept.
     * @return the entity iterator.
     */
    public Iterator<bEntity> entityItr () {
        return getEntityMap().values().iterator();
    }

    /* name for concept. */
    private String _name = null;
    
    /* list of associated entities. */
    private Map<String, bEntity> _entityMap = null;
}
