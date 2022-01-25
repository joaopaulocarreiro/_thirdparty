/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name bField
 * @version
 * @description
 * @date 29/Ago/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */
package omc.domain.model;

import java.util.Iterator;
import java.util.Map;

public class bField {

    /**
     * Constructor.
     * Initialize internal structures.
     */
    public bField() {
        setAttributeMap(new java.util.Hashtable<String, bFieldAtt>());
    }

    /**
     * Get the value of name.
     * @return the value of name
     */
    public String getName() {
        return _name;
    }

    /**
     * Get the value of the attribute list.
     * @return the value of attributeList
     */
    public Map<String, bFieldAtt> getAttributeMap() {
        return _attributeMap;
    }

    /**
     * Get the value of type.
     * @return the value of type
     */
    public String getType() {
        return _type;
    }

    /**
     * Set the value of name.
     * @param name new value of name
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * Set the value for the attribute list.
     * @param attrList new value of entityList
     */
    public void setAttributeMap(Map<String, bFieldAtt> attrList) {
        this._attributeMap = attrList;
    }

    /**
     * Set the value of type.
     * @param type type new value for type
     */
    public void setType(String type) {
        this._type = type;
    }

    /**
     * Add a new attribute to this field.
     * @param attribute the attribute to add
     */
    public void add(bFieldAtt attribute) {

        /* check arguments. */
        assert (attribute != null);

        /* check if the attribute already exists. */
        if (getAttributeMap().containsKey(attribute.getName())) {
            return;
        }

        /* all ok, insert it.*/
        getAttributeMap().put(attribute.getName(), attribute);
    }

    /**
     * Get an attribute by name.
     * @param name the name of the attribute to get
     * @return the attribute object, or null if it does not exit
     */
    public bFieldAtt get(String name) {

        /* check the argument. */
        assert(name != null);

        return getAttributeMap().get(name);
    }

    /**
     * Get an iterator of the attributes in this field.
     * @return the attribute iterator.
     */
    public Iterator<bFieldAtt> attributeItr() {
        return getAttributeMap().values().iterator();
    }

    /* name for field. */
    private String _name = null;

    /* type for field. */
    private String _type = null;

    /* list of associated attributes. */
    protected Map<String, bFieldAtt> _attributeMap = null;
}
