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

public class javaDeclPackage extends javaItem {

    /**
     * Initialize the import declaration.
     * Implies the initialization of the internal path of the
     * package declaration.
     */
    public javaDeclPackage() {
        setPath(new Vector<String>());
    }

    /**
     * Add a packagae name to the end of this declaration.
     * @param val the value for the package name.
     */
    public void add(String val) {
        getPath().add(val);
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
     * @param path new value of path
     */
    public void setPath(List<String> path) {
        this._path = path;
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
        pathStr = pathStr.substring(1, pathStr.length() - 2);

        /* pretty print the path of packages. */
        String ret = "";
        ret += Util.blankString(indent);
        ret += "package " + pathStr + ";";
        return ret;
    }

    /* internal package reference. */
    private List<String> _path = null;
}
