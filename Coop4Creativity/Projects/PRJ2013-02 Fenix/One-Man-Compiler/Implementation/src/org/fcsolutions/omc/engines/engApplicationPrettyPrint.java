/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name engApplicationPrettyPrint
 * @version
 * @description
 * @date 29/Ago/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */
package org.fcsolutions.omc.engines;

import java.util.Iterator;
import org.fcsolutions.omc.aEngine;
import org.fcsolutions.omc.domain.model.bModel;
import org.fcsolutions.omc.domain.model.bConcept;
import org.fcsolutions.omc.domain.model.bEntity;
import org.fcsolutions.omc.domain.model.bField;
import org.fcsolutions.omc.domain.model.bFieldAtt;

public class engApplicationPrettyPrint extends aEngine {

    /** @see omc.engines.iEngine.run */
    @Override
    public Object[] run(Object[] args) throws Exception {

        /* extract the first argument, this will be the object
         * that contains our data model, then prety print it,
         * finally return a empty set of return values.
         */
        System.out.print(prettyPrint((bModel) args[0]));
        return null;
    }

    /**
     * Do the actual pretty printing of the data model structure.
     * @param app the application object.
     * @return the string representation of the app spec.
     */
    private String prettyPrint(bModel app) {

        /* build space/indent variables. */
        String spcL0 = "";
        String spcL1 = spcL0 + " ";
        String spcL2 = spcL1 + " ";
        String spcL3 = spcL2 + " ";
        String spcL4 = spcL3 + " ";
        String spcL5 = spcL4 + " ";

        /* start building the string. */
        String out = "";

        /**/
        out += spcL0 + "model {" + "\n";
        out += spcL1 + "name:" + app.getName() + ";" + "\n";
        out += spcL1 + "version:" + app.getVersion() + ";" + "\n";
        out += "\n";
        /**/

        Iterator<bConcept> conItr = app.getConceptMap().values().iterator();
        while (conItr.hasNext()) {
            bConcept con = conItr.next();

            /**/
            out += spcL1 + "concept {" + "\n";
            out += spcL2 + "name:" + con.getName() + ";" + "\n";
            out += "\n";
            /**/

            Iterator<bEntity> entItr = con.entityItr();
            while (entItr.hasNext()) {
                bEntity ent = entItr.next();
                /**/
                out += spcL2 + "entity {" + "\n";
                out += spcL3 + "name:" + ent.getName() + ";" + "\n";
                out += "\n";
                /**/

                Iterator<bField> fldItr = ent.getFieldMap().values().iterator();
                while (fldItr.hasNext()) {
                    bField fld = fldItr.next();
                    /**/
                    out += spcL3 + "field {" + "\n";
                    out += spcL4 + "name:" + fld.getName() + ";" + "\n";
                    out += spcL4 + "type:" + fld.getType() + ";" + "\n";
                    out += "\n";
                    /**/

                    Iterator<bFieldAtt> attItr = fld.attributeItr();
                    while (attItr.hasNext()) {
                        bFieldAtt att = attItr.next();
                        /**/
                        out += spcL4 + "attribute {" + "\n";
                        out += spcL5 + "name:" + att.getName() + ";" + "\n";
                        out += spcL5 + "value:" + att.getValue() + ";" + "\n";
                        out += spcL4 + "}\n";
                        /**/
                    }
                    /**/ out += spcL3 + "}" + "\n"; /**/
                }
                /**/ out += spcL2 + "}" + "\n"; /**/
            }
            /**/ out += spcL1 + "}" + "\n"; /**/
        }
        /**/ out += spcL0 + "}" + "\n"; /**/

        /* all done, return the generated string. */
        return out;
    }
}
