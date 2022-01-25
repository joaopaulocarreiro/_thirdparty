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

package omc.target.java;

import omc.Util;

public class javaClass extends javaItem {

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

        /* next we pretty print the java class, first with
         * its package declaration, next with its list of imports,
         * and finally with its name and contents.
         */
        String ret = "";
        ret += getPackageDecl() == null ? "" : spc + getPackageDecl() + "\n\n";
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
    public javaModifierDecl getModifier() {
        return _modifier;
    }

    /**
     * Get the value of package declaration.
     * @return the value of packageDecl
     */
    public javaPackageDecl getPackageDecl() {
        return packageDecl;
    }

    /**
     * Set the name of the java class.
     * @param _name new value of _name
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * Set the modifier for this class.
     * @param mod the modifier.
     */
    public void setModifier(javaModifierDecl mod) {
        this._modifier = mod;
    }

    /**
     * Set the value of package declaration.
     * @param packageDecl new value of packageDecl
     */
    public void setPackageDecl(javaPackageDecl packageDecl) {
        this.packageDecl = packageDecl;
    }

    /* name for class. */
    private String _name = null;

    /* modifier for the class. */
    private javaModifierDecl _modifier = null;

    /* package declaration. */
    private javaPackageDecl packageDecl = null;

}
