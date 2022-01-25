/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name Compiler
 * @version
 * @description
 * @date 29/Ago/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */
package omc.compiler;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import omc.engines.iEngine;

public class Compiler {

    /**
     * Parse the command line options and populate this object 
     * to reflect just that.
     * @param args the command line arguments
     */
    public void parseCommandLineOptions(String args[]) throws Exception {

        /* required options for compiler, without these the compiler
         * cant run.
         */
        boolean engine_options = false;
        boolean engine_classes = false;

        /* traverse the command line arguments. */
        for (int i = 0; i < args.length; i++) {

            /* extract option name, and if exists its value. */
            String option = args[i];
            String value = ((i + 1) < args.length) ? args[i + 1] : null;
            boolean requiresValue = false;

            /* engine options file. */
            if (option.equals("--engine-options")
                    || option.equals("-o")) {
                engineOptionsFilename = value;
                engine_options = true;
                requiresValue = true;
            } else if (option.equals("--engines")
                    || option.equals("-e")) {
                engineClassesFilename = value;
                engine_classes = true;
                requiresValue = true;
            }

            /* check if we should throw an error. */
            if ((requiresValue) && (value == null)) {
                throw new Exception("option '" + option + "' requires a value!");
            }
        }

        /* test if the required options where found. */
        if (!engine_classes) {
            throw new Exception("--engines option not found!");
        }
    }

    /**
     * Parse the option file apecified in the command line.
     * @throws Exception if some error happens with the loading of the file.
     */
    public void parseOptionFile() throws Exception {

        /* if the filename for the engine options is not set we bail out. */
        if (engineOptionsFilename == null) {
            return;
        }
        
        /* load the file with the engine options. */
        InputStream ios = new FileInputStream(engineOptionsFilename);
        engineOptions = new Properties();
        engineOptions.load(ios);

        /* process the options. */
    }

    /**
     * Parse the engine classes file apecified in the command line.
     * @throws Exception if some error happens with the loading of the file.
     */
    public void parseClassesFile() throws Exception {

        /* only load if the filename was specified in the cmd line. */
        if (engineClassesFilename == null) {
            return;
        }

        /* load the file with the engine classes. */
        FileInputStream ios = new FileInputStream(engineClassesFilename);
        engineClasses = new Properties();
        engineClasses.load(ios);

        /* build the instances. */
        engineInstances = new Hashtable<String, iEngine>();
        Iterator itr = engineClasses.keySet().iterator();
        while (itr.hasNext()) {

            /* extract the name for the engine, and the
             * class name. the class name will be used for
             * instantiating a new object.
             */
            String engineName = (String) itr.next();
            String engineClass = (String) engineClasses.get(engineName);

            /* check to see if this engine name is unique. */
            if (engineInstances.containsKey(engineName)) {
                throw new Exception("engine '" + engineName + "' is duplicated in file '" + engineClassesFilename + "'");
            }

            /* use reflection. THIS ONLY WORKS IF THE ENGINE HAS
             * A ZERO PARAMETER CONSTRUCTOR.
             */
            iEngine eng = (iEngine) Class.forName(engineClass).newInstance();
            if (eng != null) {
                engineInstances.put(engineName, eng);
            }
        }
    }

    /**
     * ToString representation of compiler.
     * @return The string value
     */
    @Override
    public String toString() {

        /* preprocess the engine classes string value. */
        String engClassStr = engineClasses.toString();
        engClassStr = engClassStr.replace("{", "{\n  ");
        engClassStr = engClassStr.replace("}", ";\n }");
        engClassStr = engClassStr.replace(",", ";\n ");
        engClassStr = engClassStr.replace("=", " = ");

        /* preprocess the engine instances string value. */
        String engInstStr = engineInstances.toString();
        engInstStr = engInstStr.replace("{", "{\n  ");
        engInstStr = engInstStr.replace("}", ";\n }");
        engInstStr = engInstStr.replace(",", ";\n ");
        engInstStr = engInstStr.replace("=", " = ");

        /* preprocess the engine options string value. */
        String engOptionsStr = "";
        if (engineOptions != null) {
            engOptionsStr += " " + "engine-options " + engineOptions.toString();
            engOptionsStr = engOptionsStr.replace("{", "{\n  ");
            engOptionsStr = engOptionsStr.replace("}", ";\n }");
            engOptionsStr = engOptionsStr.replace(",", ";\n ");
            engOptionsStr = engOptionsStr.replace("=", " = ");
            engOptionsStr += "\n";
        }

        /* build the string representation of the compiler. */
        String out = "";
        out += "compiler {" + "\n";
        out += " " + "engine-classes " + engClassStr + "\n";
        out += " " + "engine-instances " + engInstStr + "\n";
        out += engOptionsStr;
        out += "}" + "\n";
        return out;
    }

    /** Name for engine options filename. */
    private String engineOptionsFilename = null;

    /** Name for engine classes filename. */
    private String engineClassesFilename = null;

    /** Engine options that are specified. */
    private Properties engineOptions = null;

    /** Engine mappings between name of engines and their class names. */
    private Properties engineClasses = null;

    /** Engine mappings between their names and theirs instances. */
    private java.util.Map<String, iEngine> engineInstances = null;
}
