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
package omc;

import java.util.Hashtable;
import java.util.Map;

public class Pipeline {

    /**
     * Constructor for pipeline.
     * @param opt The options for the pipeline.
     */
    public Pipeline(Options opt) {
        setOptions(opt);
        setEngineInstances(new Hashtable<String, iEngine>());
    }

    /**
     * Execute the compiler pipeline.
     */
    public void run() throws Exception {

        /* setup engine instances. */
        addEngine("appXMLParser", "omc.engines.engXMLApplicationParser");
        addEngine("appPrettyPrint", "omc.engines.engApplicationPrettyPrint");
        addEngine("appJavaBeans", "omc.engines.engJavaGenerateBeans");

        Object[] r0 = runEngine("appXMLParser", null);
        runEngine("appPrettyPrint", r0);
        runEngine("appJavaBeans", r0);
    }

    /**
     * Run an engine.
     * @param eng the engine to run.
     * @param args the arguments to pass to the engine.
     * @return the output of the engine.
     * @throws Exception
     */
    private Object[] runEngine(String name, Object[] args) throws Exception {

        /* get the instance. */
        iEngine eng = getEngine(name);
        if (eng == null) {
            throw new Exception("engine not found! '" + name + "'");
        }

        /* execute it. */
        eng.start(getOptions());
        Object[] ret = eng.run(args);
        eng.finish();

        /* return output to caller. */
        return ret;
    }

    /**
     * Get the option value.
     * @return the option structure.
     */
    protected Options getOptions() {
        return _options;
    }

    /**
     * Set the option structutre.
     * @param _options the option structure.
     */
    protected void setOptions(Options _options) {
        this._options = _options;
    }

    /**
     * Add an engine to this pipeline.
     * @param name The name for the engine.
     * @param className the class name for the engine. to be instatiated
     * with reflection.
     * @throws Exception
     */
    public void addEngine(String name, String className) throws Exception {
        iEngine eng = (iEngine) Class.forName(className).newInstance();
        getEngineInstances().put(name, eng);
    }

    /**
     * Get the engine instance based on name.
     * @param name The name for the engine.
     * @return the engien instance, or null if no instance exists
     * with this name.
     */
    public iEngine getEngine(String name) {
        if (!getEngineInstances().containsKey(name)) {
            return null;
        }
        return getEngineInstances().get(name);
    }

    /**
     * Return the engine instance map.
     * @return the engine instance map.
     */
    public Map<String, iEngine> getEngineInstances() {
        return _engineInstances;
    }

    /**
     * Set the engine instance map.
     * @param _engineInstances the new engine instance map.
     */
    public void setEngineInstances(Map<String, iEngine> _engineInstances) {
        this._engineInstances = _engineInstances;
    }

    /* options from outside. */
    private Options _options = null;

    /* map of engines name/instance. */
    private Map<String, iEngine> _engineInstances = null;
}
