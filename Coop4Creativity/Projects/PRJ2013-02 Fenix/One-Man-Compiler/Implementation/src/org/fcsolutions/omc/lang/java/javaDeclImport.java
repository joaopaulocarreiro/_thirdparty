/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name javaID
 * @version
 * @description
 * @date 20/Set/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */

package org.fcsolutions.omc.lang.java;

import java.util.List;
import java.util.Vector;
import org.fcsolutions.omc.Util;

public class javaDeclImport extends javaItem {

    /**
     * Initialize the import declaration.
     * Implies the initialization of the internal path of the
     * package declaration.
     */
    public javaDeclImport() {
        setPath(new Vector<String>());
    }

    /**
     * Add a packagae name to the end of this declaration.
     * @param val the value for the package name.
     */
    public javaDeclImport add(String val) {
        getPath().add(val);
        return this;
    }

    /**
     * Add a wildcard to the package path.
     * wilcard in java are represented by '*'.
     */
    public javaDeclImport addWildcard() {
        getPath().add("*");
        return this;
    }

    /**
     * Get the value of path.
     * @return the value of path
     */
    public List<String> getPath() {
        return _path;
    }

    /**
     * Set the value of path.
     * @param v new value of path
     */
    public void setPath(List<String> v) {
        _path = v;
    }

    /**
     * Pretty print the package declaration.
     * @param indent the indent value.
     * @return the string representation.
     */
    @Override
    public String prettyPrint(int indent) {

        /* check if we do have a path of packages to print, if we dont,
         * no need to pretty print anything.
         */
        if ((getPath() == null) || (getPath().size() == 0)) {
            return "";
        }

        /* do some processing on the toString method of the Vector
         * class. If they someday change this, then this prettyPrint method
         * stops working correctly.
         */
        String pathStr = getPath().toString().trim();
        pathStr = pathStr.replaceAll(", ", ".");
        pathStr = pathStr.substring(1, pathStr.length() - 1);

        /* pretty print the path of packages. */
        String ret = "";
        ret += Util.blankString(indent);
        ret += "import " + pathStr + ";";
        return ret;
    }

    /* internal package reference. */
    private List<String> _path = null;
}
