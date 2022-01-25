/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name engJavaGenerateBeans
 * @version
 * @description
 * @date 29/Ago/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */
package org.fcsolutions.omc.engines;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.fcsolutions.omc.aEngine;
import org.fcsolutions.omc.domain.model.bModel;
import org.fcsolutions.omc.domain.model.bConcept;
import org.fcsolutions.omc.domain.model.bEntity;
import org.fcsolutions.omc.domain.model.bField;
import org.fcsolutions.omc.lang.java.javaDeclClass;
import org.fcsolutions.omc.lang.java.javaDeclImport;
import org.fcsolutions.omc.lang.java.javaModifier;
import org.fcsolutions.omc.lang.java.javaDeclPackage;

public class engJavaGenerateBeans extends aEngine {

    /** @see omc.engines.iEngine.run */
    @Override
    public Object[] run(Object[] args) throws Exception {

        /* check if the output dir option is set.
         * if not set, end with an error.
         */
        if (!getOptions().exists("out-dir")) {
            error(-1, "output directory option not set 'out-dir'.");
        }
        
        /* extract the first argument, this will be the object
         * that contains our data model, then prety print it,
         * finally return a empty set of return values.
         */
        List<javaDeclClass> allClasses = genAllJavaBeans((bModel) args[0]);

        /* next we need to generate the files for each java bean.
         * lets use the out dir option as the base directory for
         * the location of the java files.
         */
        Iterator<javaDeclClass> itr = allClasses.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next());
        }

        /* no arguments are returned from this engine.*/
        return null;
    }

    /**
     * Do the actual pretty printing of the data model structure.
     * @param app the application object.
     * @return the string representation of the app spec.
     */
    private List<javaDeclClass> genAllJavaBeans(bModel model) {

        /* intialize the container for the java classes. */
        List<javaDeclClass> jclasses = new Vector<javaDeclClass>();
        
        /* get some information from the model, the model 
         * name will be used for naming the packages, this will
         * gice a nice, tidy packaging to the generated
         * functionality.
         */
        String modelName = model.getName();
        Iterator<bConcept> conItr = model.getConceptMap().values().iterator();
        while (conItr.hasNext()) {

            /* get some information from the concept, this is usefull
             * because concepts will be used for packaging the classes.
             */
            bConcept con = conItr.next();
            String conceptName = con.getName();

            /* generate one java bean for each entity in the model. */
            Iterator<bEntity> entItr = con.entityItr();
            while (entItr.hasNext()) {

                /* use the auxiliary method to generate the java bean.
                 * each entity in the model is a java bean.
                 */
                javaDeclClass bean = genJavaBean(entItr.next());

                /* after generating the bean, we still need to
                 * set tp the package for this bean, we will use
                 * the model.concept.entity packaging system
                 */
                javaDeclPackage packDecl = new javaDeclPackage();
                packDecl.add(modelName);
                packDecl.add(conceptName);
                bean.setPackageDecl(packDecl);
                
                /* after adding the package informating, the bean is
                 * ready, lets add it to the list of beans.
                 */
                jclasses.add(bean);
            }
        }
        
        /* all done, return the generated java beans. */
        return jclasses;
    }

    /**
     * Generate a java class from a model entity.
     * @param ent the entity.
     * @return the corresponding java bean.
     */
    private javaDeclClass genJavaBean(bEntity ent) {

        /* initialize the java class, with its name and modifier. */
        javaDeclClass jclass = new javaDeclClass();
        jclass.setName(ent.getName());

        /* the modifier for the class, they will be public. */
        javaModifier jclassMod = new javaModifier();
        jclassMod.add(javaModifier.PUBLIC);
        jclass.setModifier(jclassMod);

        /* add the import list. (blindly) */
        List<javaDeclImport> jclassImpLst = jclass.getImportList();
        javaDeclImport mapImport =
                new javaDeclImport().add("java").add("util").addWildcard();
        jclassImpLst.add(mapImport);

        /* next, generate the property fields. */
        Iterator<bField> fldItr = ent.getFieldMap().values().iterator();
        while (fldItr.hasNext()) {
            
        }

        /* generate get's. */
        fldItr = ent.getFieldMap().values().iterator();
        while (fldItr.hasNext()) {

        }

        /* generate set's. */
        fldItr = ent.getFieldMap().values().iterator();
        while (fldItr.hasNext()) {

        }

        /* all done, return the generated class. */
        return jclass;
    }
}
