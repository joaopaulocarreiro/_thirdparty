/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name Pipeline
 * @version
 * @description
 * @date 20/Set/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */
package org.fcsolutions.omc;

import java.util.Hashtable;
import java.util.Map;

public class Pipeline {

    /**
     * Constructor for pipeline.
     * @param opt The options for the pipeline.
     */
    public Pipeline(Options opt) {
        setOptions(opt);
        setEngineMap(new Hashtable<String, iEngine>());
    }

    /**
     * Execute the compiler pipeline.
     */
    public void run() throws Exception {

        /* setup engine instances. */
        loadEngine("appXMLParser", "org.fcsolutions.omc.engines.engXMLApplicationParser");
        loadEngine("appPrettyPrint", "org.fcsolutions.omc.engines.engApplicationPrettyPrint");
        loadEngine("appJavaBeans", "org.fcsolutions.omc.engines.engJavaGenerateBeans");

        Object[] r0 = evalCall("appXMLParser", null);
        evalCall("appPrettyPrint", r0);
        evalCall("appJavaBeans", r0);
    }

    /**
     * Run an engine.
     * @param eng the engine to run.
     * @param args the arguments to pass to the engine.
     * @return the output of the engine.
     * @throws Exception
     */
    protected Object[] evalCall(String name, Object[] args) throws Exception {

        /* get the engine instance, on error, throw a not found
         * exception.
         */
        if (!getEngineMap().containsKey(name)) {
            throw new Exception("engine not found! '" + name + "'");
        }

        iEngine eng = getEngineMap().get(name);

        /* execute it. */
        eng.start(getOptions());
        Object[] ret = eng.run(args);
        eng.finish();

        /* return output to caller. */
        return ret;
    }

    /**
     * Evaluate the variable name.
     * @param name the name of the variable.
     * @return the value for the variable or null, if the variable
     * does not exist.
     * @throws Exception if the variable name does not exist.
     */
    protected Object evalVar(String name) throws Exception {

        /* check variable name, if the variable does not exist
         * in the current context, throw an exception.
         */
        if (!getVarMap().containsKey(name)) {
            throw new Exception("variable '" + name + "' not found!");
        }

        /* return the value. */
        return getVarMap().get(name);
    }

    /**
     * Add an engine to this pipeline.
     * @param name The name for the engine.
     * @param className the class name for the engine. to be instatiated
     * with reflection.
     * @throws Exception
     */
    protected void loadEngine(String name, String className) throws Exception {

        /* lets instantiate the engine here.
         * NOTE: this only works if the engine has a no-argument constructor.
         */
        iEngine eng = (iEngine) Class.forName(className).newInstance();

        /* if no error occurs, then we add the engine to the
         * the engine instance map. if an engine with that name
         * already exists we throw an exception signaling just that.
         */
        if (getEngineMap().containsKey(name)) {
            throw new Exception("engine already loaded '" + name + "'");
        }

        getEngineMap().put(name, eng);
    }
    
    /**
     * Add a variable to the mappinf.
     * @param name the name of the variable.
     * @param value the value for the variable.
     */
    protected void addVar(String name, Object value) {
        getVarMap().put(name, value);
    }

    /**
     * Get the option value.
     * @return the option structure.
     */
    protected Options getOptions() {
        return _options;
    }

    /**
     * Get the value of the variable map.
     * @return the value of varMap
     */
    protected Map<String, Object> getVarMap() {
        return _varMap;
    }

    /**
     * Return the engine instance map.
     * @return the engine instance map.
     */
    protected Map<String, iEngine> getEngineMap() {
        return _engineMap;
    }

    /**
     * Set the option structutre.
     * @param _options the option structure.
     */
    protected void setOptions(Options _options) {
        this._options = _options;
    }

    /**
     * Set the value of varMap
     * @param varMap new value of varMap
     */
    protected void setVarMap(Map<String, Object> varMap) {
        this._varMap = varMap;
    }

    /**
     * Set the engine instance map.
     * @param _engineInstances the new engine instance map.
     */
    protected void setEngineMap(Map<String, iEngine> _engineInstances) {
        this._engineMap = _engineInstances;
    }

    /* options from outside. */
    private Options _options = null;

    /* map of engines name/instance. */
    private Map<String, iEngine> _engineMap = null;

    /* map of variables. */
    private Map<String, Object> _varMap = null;
}
