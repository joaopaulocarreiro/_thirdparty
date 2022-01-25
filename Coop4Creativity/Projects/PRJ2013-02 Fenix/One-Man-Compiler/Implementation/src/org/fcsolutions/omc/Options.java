/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name Options
 * @version
 * @description
 * @date 29/Ago/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */
package org.fcsolutions.omc;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Options {

    /**
     * Initialization of option structure.
     * Includes the creation of the internal structures used
     * in this object.
     */
    public Options() {
        setOptionMap(new Hashtable<String, String>());
        setOrphanValueList(new Vector<String>());
    }

    /**
     * Parse the command line options and populate this object 
     * to reflect just that.
     * @param args the command line arguments
     */
    public void readCommandLine(String args[]) throws Exception {

        /* first of all lets clear the options that are here. */
        clear();

        /* read every option in the command line and populate this
         * structure.
         */
        for (int i = 0; i < args.length; i++) {

            /* extract option name, and if exists its value. */
            String optionName = null;
            String optionValue = null;
            String argValue = args[i];

            if (argValue.startsWith("-")) {

                /* we found an option?
                 * extract the name of the option.
                 */
                optionName = argValue.substring(1);

                /* move option pointer. */
                i++;

                /* check the next argument. */
                if (i < args.length) {

                    argValue = args[i];

                    if (argValue.startsWith("-")) {
                        /* if the next argument starts with '-'
                         * then the previous has no value.
                         * we then move the option point back
                         * so that we can process this option.
                         */
                        optionValue = null;
                        i--;
                    } else {
                        /* the value does not start with '-'
                         * so this must be an option value.
                         */
                        optionValue = argValue;
                    }
                }
            } else {
                /* if we get to this point then this value must
                 * be an orphan value.
                 */
                optionName = null;
                optionValue = argValue;
            }

            /* put the option/value into the map.
             * we have 3 possibilities:
             *  - option/value
             *  - value
             *  - option
             */
            add(optionName, optionValue);
        }
    }

    /**
     * Clear all options in this container.
     */
    public void clear() {
        getOptionMap().clear();
        getOrphanValueList().clear();
    }

    /**
     * Add a option. Add its name, and its value.
     * @param name the option name.
     * @param value the option value.
     */
    public void add(String name, String value) {

        /* choose the store based on the input value.
         * We have three possible choices:
         *  - option/value
         *  - value
         *  - option
         */
        if ((null != name) && (null != value)) {

            /* add this to the option/value map. */
            getOptionMap().put(name, value);

        } else if ((null == name) && (null != value)) {

            /* add this to the orphan value list. */
            getOrphanValueList().add(value);

        } else if ((null != name) && (null == value)) {

            /* add this to the option with no value. */
            getOptionMap().put(name, null);
        }
    }

    /**
     * Check to see if an option exists.
     * @param name The name for the option.
     * @return true if the option exists, false otherwise.
     */
    public boolean exists(String name) {
        return getOptionMap().containsKey(name);
    }

    /**
     * Check to see if an option has a value.
     * @param name the option name.
     * @return true if option has a value, false otherwise.
     */
    public boolean hasValue(String name) {
        if (exists(name)) {
            return getOptionMap().get(name) != null;
        }
        return false;
    }

    /**
     * Get the value of an option.
     * @param name the name of the option.
     * @return the option's value, or null if the option does not exist
     * or does not have a value.
     */
    public String getValue(String name) {
        if (!exists(name)) {
            return null;
        }
        return getOptionMap().get(name);
    }

    /**
     * Return a iterator for the orphan values.
     * Orphan values, are values that are not associated with 
     * any option.
     * @return iterator.
     */
    public Iterator<String> orphanValueItr() {
        return getOrphanValueList().iterator();
    }

    /**
     * Return an iterator for the names of the options.
     * @return the iterator.
     */
    public Iterator<String> optionNameItr() {
        return getOptionMap().keySet().iterator();
    }

    /**
     * ToString representation of options.
     * @return The string value
     */
    @Override
    public String toString() {

        /* open option bracket. */
        String str = "options {\n";

        /* print option/value map.. */
        Iterator<String> itr = optionNameItr();
        while (itr.hasNext()) {
            String name = itr.next();
            if (hasValue(name)) {
                str += " -" + name + ":" + getValue(name) + "\n";
            }
        }

        /* print options with no value. */
        itr = optionNameItr();
        while (itr.hasNext()) {
            String name = itr.next();
            if (!hasValue(name)) {
                str += " -" + name + "\n";
            }
        }

        /* print orphaned values. */
        itr = orphanValueItr();
        while (itr.hasNext()) {
            str += " -" + itr.next() + "\n";
        }

        /* close option bracket. */
        str += "}\n";

        return str;
    }

    /**
     * Get the option map container.
     * @return the option map.
     */
    protected Map<String, String> getOptionMap() {
        return _optionMap;
    }

    /**
     * Get the list of orphan values.
     * @return the orphan values container.
     */
    protected List<String> getOrphanValueList() {
        return _orphanValueList;
    }

    /**
     * Set the option map container.
     * @param _optionMap the new option map container.
     */
    protected void setOptionMap(Map<String, String> _optionMap) {
        this._optionMap = _optionMap;
    }

    /**
     * Set the orphan value container.
     * @param _orphanValueList the new orphan value container.
     */
    protected void setOrphanValueList(List<String> _orphanValueList) {
        this._orphanValueList = _orphanValueList;
    }

    /* option map - for name/value pairs. */
    private Map<String, String> _optionMap = null;

    /* orphan values. */
    private List<String> _orphanValueList = null;
}
