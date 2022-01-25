/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name aEngine
 * @version
 * @description
 * @date 20/Set/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */

package org.fcsolutions.omc;

public abstract class aEngine implements iEngine {

    /** @see omc.iEngine.start */
    public void start(Options options) throws Exception {
        assert(null != options);
        setOptions(options);
    }

    /** @see omc.iEngine.run */
    public abstract Object[] run(Object[] args) throws Exception;

    /** @see omc.iEngine.finish */
    public void finish() throws Exception {}

    /**
     * Signal an error in the engine.
     * @param code the code for the error.
     * @param msg he message for the error.
     */
    protected void error(int code, String msg) throws Exception {
        String appendedMsg = "[" + this.getClass().getName() + "] " + msg;
        ErrorUtil.set(code, appendedMsg);
    }

    /**
     * Get the value of options
     * @return the value of options
     */
    public Options getOptions() {
        return _options;
    }

    /**
     * Set the value of options
     * @param options new value of options
     */
    public void setOptions(Options options) {
        this._options = options;
    }

    /* engine options. */
    protected Options _options = null;

}
