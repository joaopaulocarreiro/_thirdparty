/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 * 
 * @name EntryPoint
 * @version
 * @description
 * @date 29/Ago/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */
package org.fcsolutions.omc;

public class EntryPoint {

    /**
     * Entry point for the OMC compiler.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {

        /* build the compiler object, parse the command line
         * arguments, load the engines and the option files,
         * then run the compiler.
         */
        try {
            Options opt = new Options();
            opt.readCommandLine(args);
            Pipeline pipe = new Pipeline(opt);
            pipe.run();
        } catch (Exception ex) {
            System.err.println("OMC: [ERROR] " + ex.getMessage() + "\n");
        }
    }
}
