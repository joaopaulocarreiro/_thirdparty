/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name javaType
 * @version
 * @description
 * @date 25/Set/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */
package org.fcsolutions.omc.lang.java;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.fcsolutions.omc.Util;

public class javaType extends javaItem {

    /**
     * Empty constructor.
     */
    public javaType() {
        this(null);
    }

    /**
     * Constructor 
     * Initialize the data structures for the object.
     * @param fullName the full name for the type, with package.
     */
    public javaType(String fullName) {
        parseFullName(fullName);
        setTypeParamLst(new Vector<javaType>());
    }

    /**
     * Parse a string that represents a java full name type.
     * @param value the value to parse.
     */
    private void parseFullName(String value) {

        /* if value if null, do nothing. */
        if (null == value) {
            return;
        }

        /* locals, auxiliary vars. */
        int i = 0;
        String p[] = value.split("[ \n\t]*[.][ \n\t]*");

        /* check if we have something to parse. */
        if (p.length < 1) {
            return;
        }

        /* build package name. */
        for (i = 0; i < p.length - 1; i++) {
            getPackage().add(p[i]);
        }

        /* set the name. */
        setName(p[i]);
    }

    /**
     * Get the value of path.
     * @return the value of path
     */
    public List<String> getPackage() {
        return _package;
    }

    /**
     * Return the name of the type.
     * @return the name of the type.
     */
    public String getName() {
        return _name;
    }

    /**
     * Get the type parameter list.
     * @return the type parameter list.
     */
    public List<javaType> getTypeParamLst() {
        return _paramList;
    }

    /**
     * Set the value of path.
     * @param v new value of path
     */
    public void setPackage(List<String> v) {
        _package = v;
    }

    /**
     * Set the name for the type.
     * @param v the new name for the type.
     */
    public void setName(String v) {
        _name = v;
    }

    /**
     * Set the parameter type list.
     * @param v the new parameter type list.
     */
    public void setTypeParamLst(List<javaType> v) {
        _paramList = v;
    }

    /**
     * Pretty print the type.
     * @param indent the indentation space.
     * @return the string representation.
     */
    public String prettyPrint(int indent) {

        /* print type parameters. */
        String packageStr = "";
        if ((getPackage() != null) && (getPackage().size()) > 0) {
            Iterator<String> packageItr = getPackage().iterator();
            while (packageItr.hasNext()) {
                packageStr += packageItr.next();
                if (packageItr.hasNext()) {
                    packageStr += ".";
                }
            }
            packageStr += ".";
        }

        /* print type parameters. */
        String paramStr = "";
        if ((_paramList != null) && (_paramList.size()) > 0) {
            paramStr += "<";
            Iterator<javaType> paramItr = getTypeParamLst().iterator();
            while (paramItr.hasNext()) {
                paramStr += paramItr.next();
                if (paramItr.hasNext()) {
                    paramStr += ", ";
                }
            }
            paramStr += ">";
        }

        /* put everything together. */
        String ret = "";
        ret += Util.blankString(indent);
        ret += packageStr;
        ret += getName();
        ret += paramStr;

        /* return the pretty print for the type.*/
        return ret;
    }

    /* name for type. */
    private String _name = null;

    /* package location. */
    private List<String> _package = null;

    /* if the type contains generics, these are the types. */
    private List<javaType> _paramList = null;
}
