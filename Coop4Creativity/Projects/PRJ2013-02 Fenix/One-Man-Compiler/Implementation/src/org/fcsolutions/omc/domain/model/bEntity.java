/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name bEntity
 * @version
 * @description
 * @date 29/Ago/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */
package org.fcsolutions.omc.domain.model;

import java.util.Iterator;
import java.util.Map;

public class bEntity {

    /**
     * Constructor.
     * Initialization of object.
     */
    public bEntity() {
        setFieldMap(new java.util.Hashtable<String, bField>());
    }

    /**
     * Get the value of name
     * @return the value of name
     */
    public String getName() {
        return _name;
    }

    /**
     * Get the value of the field map structure.
     * @return the value of entityList
     */
    public Map<String, bField> getFieldMap() {
        return _fieldMap;
    }

    /**
     * Set the value of name.
     * @param name new value of name
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * Set the value for the field map structure.
     * @param fieldList new value of entityList
     */
    public void setFieldMap(Map<String, bField> fieldList) {
        this._fieldMap = fieldList;
    }

    /**
     * Add a new field to this entity.
     * @param field the entity to add
     */
    public void add(bField field) {

        /* check arguments. */
        assert (field != null);

        /* check if the field already exists. */
        if (getFieldMap().containsKey(field.getName())) return;

        /* all ok, insert it.*/
        getFieldMap().put(field.getName(), field);
    }

    /**
     * Get a field by name.
     * @param name the name of the field to get
     * @return the field object, or null if it does not exit
     */
    public bField get(String name) {

        /* check the argument. */
        assert(name != null);
        return getFieldMap().get(name);
    }

    /** Name for concept. */
    private String _name = null;
    
    /** List of associated fields. */
    protected Map<String, bField> _fieldMap = null;
}
