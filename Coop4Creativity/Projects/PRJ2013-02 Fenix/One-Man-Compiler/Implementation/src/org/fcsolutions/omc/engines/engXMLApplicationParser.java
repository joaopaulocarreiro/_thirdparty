/**
 * One-Man-Compiler
 * OMC is a system to generate applications out of declarative
 * specifications. Its goal is to accelerate application development.
 *
 * @name engXMLApplicationParser
 * @version
 * @description
 * @date 29/Ago/2010
 * @author Joao Paulo Carreiro - joaopaulocarreiro@gmail.com
 */
package org.fcsolutions.omc.engines;

import org.fcsolutions.omc.XMLUtil;
import org.fcsolutions.omc.domain.model.bModel;
import org.fcsolutions.omc.domain.model.bConcept;
import org.fcsolutions.omc.domain.model.bEntity;
import org.fcsolutions.omc.domain.model.bField;
import org.fcsolutions.omc.domain.model.bFieldAtt;
import org.dom4j.Element;
import java.util.Iterator;
import org.fcsolutions.omc.aEngine;

public class engXMLApplicationParser extends aEngine {

    /** @see omc.engines.iEngine.run */
    @Override
    public Object[] run(Object[] args) throws Exception {

        /* get the filename to parse, if no name exists then
         * we have an error.
         */
        if (!getOptions().exists("file"))
            error(-1, "input file was not specified in engine options.");

        /* after that, let parse the model and construct the return
         * values. the returned values are just one, the parse data
         * model.
         */
        Object[] ret = new Object[1];
        ret[0] = parseModel(getOptions().getValue("file"));
        return ret;
    }

    /**
     * Do the actual parsing and building of the application.
     * @param url the location of the file to read.
     * @return The parsed data model.
     * @throws Exception is file is not reachable, or if there is some
     * semantic error with the XML.
     */
    private bModel parseModel(String url) throws Exception {

        /* create the reader and parse the XML file. */
        org.dom4j.io.SAXReader reader = new org.dom4j.io.SAXReader();
        org.dom4j.Document xmldoc = reader.read(url);

        /* get the root element and verify it. */
        Element app = xmldoc.getRootElement();
        XMLUtil.checkXMLTag(app, "model");

        /* get info about data model. */
        bModel mModel = new bModel();
        mModel.setName(XMLUtil.checkXMLAtt(app, "name"));
        mModel.setVersion(XMLUtil.checkXMLAtt(app, "version"));

        /* traverse all concepts. */
        Iterator itrCon = app.selectNodes("concept").iterator();
        while (itrCon.hasNext()) {

            /* get element and create model concept. */
            Element con = (Element) itrCon.next();
            bConcept mConcept = new bConcept();
            mConcept.setName(XMLUtil.checkXMLAtt(con, "name"));

            /* traverse all entities inside the concept. */
            Iterator itrEnt = con.selectNodes("entity").iterator();
            while (itrEnt.hasNext()) {

                /* get element and create mdel entity. */
                Element ent = (Element) itrEnt.next();
                bEntity mEntity = new bEntity();
                mEntity.setName(XMLUtil.checkXMLAtt(ent, "name"));

                /* traverse all fields. */
                Iterator itrFld = ent.selectNodes("field").iterator();
                while (itrFld.hasNext()) {

                    /* get element and create model field. */
                    Element fld = (Element) itrFld.next();
                    bField mField = new bField();
                    mField.setName(XMLUtil.checkXMLAtt(fld, "name"));
                    mField.setType(XMLUtil.checkXMLAtt(fld, "type"));

                    /* get all attributes for field. */
                    Iterator itrAttr = fld.selectNodes("attr").iterator();
                    while (itrAttr.hasNext()) {

                        /* get element and create model attribute. */
                        Element fat = (Element) itrAttr.next();
                        bFieldAtt mAtt = new bFieldAtt();
                        mAtt.setName(XMLUtil.checkXMLAtt(fat, "name"));
                        mAtt.setValue(XMLUtil.checkXMLAtt(fat, "value"));

                        /* add attribute to field. */
                        mField.add(mAtt);
                    }
                    /* add field to entity. */
                    mEntity.add(mField);
                }
                /* add entity to concept. */
                mConcept.add(mEntity);
            }
            /* add concept to application. */
            mModel.add(mConcept);
        }
        /* model all done. */
        return mModel;
    }
}
