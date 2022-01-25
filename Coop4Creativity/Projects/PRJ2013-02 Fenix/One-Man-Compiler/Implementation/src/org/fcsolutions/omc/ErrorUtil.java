/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name Misc
 * @version
 * @description
 * @date 17/Set/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */

package org.fcsolutions.omc;

public final class ErrorUtil {

    /**
     * Signal an error with the script items.
     * @param msg the error message.
     */
    public static void set(int code, String msg) throws Exception {
        throw new Exception(msg);
    }
}
