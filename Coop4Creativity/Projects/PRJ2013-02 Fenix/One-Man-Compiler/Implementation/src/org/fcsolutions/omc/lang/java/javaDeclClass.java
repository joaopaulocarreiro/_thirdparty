/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name javaClass
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

public class javaDeclClass extends javaItem {

    /**
     * Constructor for java class descrption object.
     * Initializes the internal data structures.
     */
    public javaDeclClass() {
        setImportList(new Vector<javaDeclImport>());
    }

    /**
     * Get the name of the java class.
     * @return the value of _name
     */
    public String getName() {
        return _name;
    }

    /**
     * Get the modifier for the class.
     * @return the modifier.
     */
    public javaModifier getModifier() {
        return _modifier;
    }

    /**
     * Get the value of package declaration.
     * @return the value of packageDecl
     */
    public javaDeclPackage getPackageDecl() {
        return _packageDecl;
    }

    /**
     * Return the list of imports from this class.
     * @return the list with the imports.
     */
    public List<javaDeclImport> getImportList() {
        return _importList;
    }

    /**
     * Set the name of the java class.
     * @param _name new value of _name
     */
    public void setName(String v) {
        _name = v;
    }

    /**
     * Set the modifier for this class.
     * @param v the modifier.
     */
    public void setModifier(javaModifier v) {
        _modifier = v;
    }

    /**
     * Set the value of package declaration.
     * @param v new value of packageDecl
     */
    public void setPackageDecl(javaDeclPackage v) {
        _packageDecl = v;
    }

    /**
     * Set the list of imports.
     * @param v the new list for the imports.
     */
    public void setImportList(List<javaDeclImport> v) {
        _importList = v;
    }

    /**
     * Pretty print the language construct.
     * @param indent the indent space.
     * @return the string.
     */
    @Override
    public String prettyPrint(int indent) {

        /* we build a empty string with the indent space,
         * just to turn the method into a more readable state.
         */
        String spc = Util.blankString(indent);

        /* prepare the import list string. */
        String importStr = "";
        Iterator<javaDeclImport> impItr = getImportList().iterator();
        while (impItr.hasNext()) {
            importStr += impItr.next().prettyPrint(indent) + "\n";
        }

        /* next we pretty print the java class, first with
         * its package declaration, next with its list of imports,
         * and finally with its name and contents.
         */
        String ret = "";
        ret += spc;
        ret += getPackageDecl() == null ? "" : spc + getPackageDecl() + "\n\n";
        ret += spc;
        ret += importStr + "\n";
        ret += spc;
        ret += getModifier() == null ? "" : getModifier().toString() + " ";
        ret += "class ";
        ret += getName();
        ret += " {" + "\n";
        ret += spc + "}" + "\n";

        /* all done, return the generated string that represents
         * this java class.
         */
        return ret;
    }

    /* name for class. */
    private String _name = null;

    /* modifier for the class. */
    private javaModifier _modifier = null;

    /* package declaration. */
    private javaDeclPackage _packageDecl = null;

    /* list of imports. */
    private List<javaDeclImport> _importList = null;
}
