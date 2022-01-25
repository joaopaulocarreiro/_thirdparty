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
package omc.model.application;

public class bEntity {

    /** Constructor. */
    public bEntity() {
        fieldList = new java.util.Hashtable<String, bField>();
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
     * Get the value of fieldList
     *
     * @return the value of entityList
     */
    public java.util.Map<String, bField> getFieldList() {
        return fieldList;
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
     * Set the value of fieldList
     *
     * @param fieldList new value of entityList
     */
    public void setFieldList(java.util.Map<String, bField> fieldList) {
        this.fieldList = fieldList;
    }

    /**
     * Add a new field to this entity
     *
     * @param field the entity to add
     */
    public void add(bField field) {

        /* check arguments. */
        assert (field != null);

        /* check if the field already exists. */
        if (fieldList.containsKey(field.getName())) return;

        /* all ok, insert it.*/
        fieldList.put(field.getName(), field);
    }

    /**
     * Get a field by name.
     *
     * @param name the name of the field to get
     * @return the field object, or null if it does not exit
     */
    public bField get(String name) {
        return fieldList.get(name);
    }

    /**
     * Get an iterator of the fields in this concept.
     *
     * @return the entity iterator.
     */
    public java.util.Iterator<bField> fieldList () {
        return fieldList.values().iterator();
    }

    /** Name for concept. */
    private String name = null;
    
    /** List of associated fields. */
    protected java.util.Map<String, bField> fieldList = null;
}
