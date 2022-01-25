/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name javaModifier
 * @version
 * @description
 * @date 20/Set/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */
package org.fcsolutions.omc.lang.java;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.fcsolutions.omc.Util;

public class javaModifier extends javaItem {

    /* constants for modifier. */
    public static final int PRIVATE = 1;
    public static final int PUBLIC = 2;
    public static final int PROTECTED = 3;
    public static final int STATIC = 4;
    public static final int FINAL = 5;
    public static final int ABSTRACT = 6;
    public static final int STRICTFP = 7;
    public static final int TRANSIENT = 8;
    public static final int VOLATILE = 9;
    public static final int SYNCHRONIZED = 10;
    public static final int NATIVE = 11;

    /**
     * Initialize the modifier object.
     * Includes the initialization of the internal list of modifier
     * values.
     */
    public javaModifier() {
        setValues(new Vector<Integer>());
    }

    /**
     * Add a modifier to this modifier.
     * @param modifier the reference.
     */
    public void add(int modifier) {

        /* only insert valid modifiers.
         * we should probably return/throw a error message.
         */
        if ((modifier < javaModifier.PRIVATE)
                || (modifier > javaModifier.NATIVE)) {
            return;
        }

        /* insert it. */
        getValues().add(new Integer(modifier));
    }

    /**
     * Get the value of values
     * @return the value of values
     */
    public List<Integer> getValues() {
        return _values;
    }

    /**
     * Set the value of values
     * @param values new value of values
     */
    public void setValues(List<Integer> values) {
        this._values = values;
    }

    /**
     * Pretty print the language construct.
     * @param indent the indent value.
     * @return the string representation.
     */
    @Override
    public String prettyPrint(int indent) {

        /* if the internal values is null or empty,
         * we dont print anything.
         */
        if ((getValues() == null) || (getValues().size() == 0)) {
            return "";
        }

        /* otherwise we print the representation of the modifiers,
         * by the order that they were specified.
         */
        String ret = Util.blankString(indent);
        Iterator<Integer> itr = getValues().iterator();
        while (itr.hasNext()) {
            switch (itr.next().intValue()) {
                case javaModifier.PRIVATE:
                    ret += "private";
                    break;
                case javaModifier.PUBLIC:
                    ret += "public";
                    break;
                case javaModifier.PROTECTED:
                    ret += "protected";
                    break;
                case javaModifier.STATIC:
                    ret += "static";
                    break;
                case javaModifier.FINAL:
                    ret += "final";
                    break;
                case javaModifier.ABSTRACT:
                    ret += "abstract";
                    break;
                case javaModifier.STRICTFP:
                    ret += "strictfp";
                    break;
                case javaModifier.TRANSIENT:
                    ret += "transient";
                    break;
                case javaModifier.VOLATILE:
                    ret += "volatile";
                    break;
                case javaModifier.SYNCHRONIZED:
                    ret += "synchronized";
                    break;
                case javaModifier.NATIVE:
                    ret += "native";
                    break;
                default:
                    break;
            }

            /* if there are more hen insert the sperating space. */
            if (itr.hasNext()) {
                ret += " ";
            }
        }

        return ret;
    }

    /* internal values. */
    private List<Integer> _values = null;
}
