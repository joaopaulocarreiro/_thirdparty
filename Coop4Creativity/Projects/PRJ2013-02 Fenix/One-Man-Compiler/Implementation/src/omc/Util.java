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

package omc;

public final class Util {

    /**
     * Generate a blank string with n white characters.
     * @param size the number of characters.
     * @return The blank string with soze white characters.
     */
    public static String blankString(int size) {
        String res = "";
        for (int i = 0; i < size; i++) {
            res += " ";
        }
        return res;
    }
}
