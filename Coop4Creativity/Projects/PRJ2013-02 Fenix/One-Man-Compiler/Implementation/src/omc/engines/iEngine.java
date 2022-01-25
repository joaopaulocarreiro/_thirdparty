/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name iEngine
 * @version
 * @description
 * @date 29/Ago/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */
package omc.engines;

public interface iEngine {

    /**
     * Starts the the compiler engine.
     * This methods is usefull for initializaton of state.
     * @param options the options passed to this engine.
     */
    public void start(java.util.Map<String, String> options) throws Exception;

    /**
     * Executes and performs the code for the engine.
     * @param args runtime arguments to pass to the engine.
     * @return if the engine returns values, they will be here.
     */
    public Object[] run(Object[] args) throws Exception;

    /**
     * Finish engine execution.
     * Usefull for releasing resources used by the engine.
     */
    public void finish() throws Exception;
}
