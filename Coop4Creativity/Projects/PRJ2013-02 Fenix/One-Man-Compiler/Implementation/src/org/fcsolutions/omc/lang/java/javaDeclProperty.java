/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name javaDeclProperty
 * @version
 * @description
 * @date 25/Set/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */

package org.fcsolutions.omc.lang.java;

public class javaDeclProperty extends javaItem {

    public javaModifier getModifier() {
        return _modifier;
    }

    public javaType getType() {
        return _type;
    }

    public String getName() {
        return _name;
    }
    
    public void setModifier(javaModifier v) {
        _modifier = v;
    }
    
    public void setType(javaType v) {
        _type = v;
    }
    
    public void setName(String v) {
        _name = v;
    }
    
    /**
     * Pretty print the property declaration.
     * @param indent the indent space
     * @return the strng representation
     */
    @Override
    public String prettyPrint(int indent) {

        String modStr =
        /* put everything together. */
        String ret = ""
        return ret;
    }

    /* modifier for the property. */
    private javaModifier _modifier = null;

    /* type of property. */
    private javaType _type = null;

    /* name for property. */
    private String _name = null;
}
