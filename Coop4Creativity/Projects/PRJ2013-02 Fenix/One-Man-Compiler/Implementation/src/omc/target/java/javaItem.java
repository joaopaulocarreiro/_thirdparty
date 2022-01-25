/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.

 * @name javaItem
 * @version
 * @description
 * @date 18/Set/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */
package omc.target.java;

public abstract class javaItem {

    /**
     * Pretty print of language construct.
     * @return The string representation of the language construct.
     */
    public String prettyPrint() {
        return prettyPrint(0);
    }

    /**
     * Pretty print of language construct.
     * @param indent The indent space for the pretty print
     * representation.
     * @return the pretty print representation.
     */
    public String prettyPrint(int indent) {
        return getClass().getName();
    }

    /**
     * String representation for item.
     * @return The string representation.
     */
    @Override
    public String toString() {
        return prettyPrint();
    }
}
