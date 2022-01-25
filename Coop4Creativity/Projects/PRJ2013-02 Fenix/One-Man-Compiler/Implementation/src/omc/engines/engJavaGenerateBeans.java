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
package omc.engines;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import omc.aEngine;
import omc.domain.model.bModel;
import omc.domain.model.bConcept;
import omc.domain.model.bEntity;
import omc.target.java.javaClass;
import omc.target.java.javaModifierDecl;
import omc.target.java.javaPackageDecl;

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
        List<javaClass> allClasses = genAllJavaBeans((bModel) args[0]);

        /* next we need to generate the files for each java bean.
         * lets use the out dir option as the base directory for
         * the location of the java files.
         */
        Iterator<javaClass> itr = allClasses.iterator();
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
    private List<javaClass> genAllJavaBeans(bModel model) {

        /* intialize the container for the java classes. */
        List<javaClass> jclasses = new Vector<javaClass>();
        
        /* get some information from the model, the model 
         * name will be used for naming the packages, this will
         * gice a nice, tidy packaging to the generated
         * functionality.
         */
        String modelName = model.getName();
        Iterator<bConcept> conItr = model.conceptItr();
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
                javaClass bean = genJavaBean(entItr.next());

                /* after generating the bean, we still need to
                 * set tp the package for this bean, we will use
                 * the model.concept.entity packaging system
                 */
                javaPackageDecl packDecl = new javaPackageDecl();
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
    private javaClass genJavaBean(bEntity ent) {

        /* initialize the java class, with its name and modifier. */
        javaClass jclass = new javaClass();
        jclass.setName(ent.getName());

        /* the modifier for the class, they will be public. */
        javaModifierDecl jclassMod = new javaModifierDecl();
        jclassMod.add(javaModifierDecl.PUBLIC);
        jclass.setModifier(jclassMod);
        
        return jclass;
    }
}
