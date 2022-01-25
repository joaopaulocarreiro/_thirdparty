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
package omc.model.application;

public class bField {

    /** Constructor. */
    public bField() {
        attributeList = new java.util.Hashtable<String, bFieldAtt>();
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
     * Get the value of attributeList
     *
     * @return the value of attributeList
     */
    public java.util.Map<String, bFieldAtt> getAttributeList() {
        return attributeList;
    }

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType() {
        return type;
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
     * @param attrList new value of entityList
     */
    public void setAttributeList(java.util.Map<String, bFieldAtt> attrList) {
        this.attributeList = attrList;
    }

    /**
     * Set he value of type
     * 
     * @param type type new value for type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Add a new attribute to this field
     *
     * @param attribute the attribute to add
     */
    public void add(bFieldAtt attribute) {

        /* check arguments. */
        assert (attribute != null);

        /* check if the attribute already exists. */
        if (attributeList.containsKey(attribute.getName())) {
            return;
        }

        /* all ok, insert it.*/
        attributeList.put(attribute.getName(), attribute);
    }

    /**
     * Get an attribute by name.
     *
     * @param name the name of the attribute to get
     * @return the attribute object, or null if it does not exit
     */
    public bFieldAtt get(String name) {
        return attributeList.get(name);
    }

    /**
     * Get an iterator of the attributes in this field.
     *
     * @return the attribute iterator.
     */
    public java.util.Iterator<bFieldAtt> attributeList() {
        return attributeList.values().iterator();
    }

    /** Name for field. */
    private String name = null;

    /** Type for field. */
    private String type = null;

    /** List of associated attributes. */
    protected java.util.Map<String, bFieldAtt> attributeList = null;
}
