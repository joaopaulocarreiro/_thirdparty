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

import org.dom4j.Element;

public final class XMLUtil {

    /**
     * Verify XML tag name.
     * @param elm the element to check.
     * @param tagName the name to verify.
     * @throws ScriptErrorException If the XML tag does not match
     */
    public static void checkXMLTag(Element elm, String tagName) throws Exception {

        /* check parameters in. */
        assert (elm != null);

        /* check the tag of the element, if they
         * dont match, thro an exception.
         */
        if (!elm.getName().equals(tagName)) {
            String errMsg = "";
            errMsg += "invalid XML tag:" + elm.getName();
            errMsg += ", was expecting :" + tagName;
            ErrorUtil.set(-1, errMsg);
        }
    }

    /**
     * Check a mandatory XML attribute.
     * @param elm the xml element to check for.
     * @param attName the name of the attribute.
     */
    public static String checkXMLAtt(Element elm, String attName) throws Exception {

        /* extract the value of the attribute, if the attribute
         * does not exist, this will be null.
         */
        String val = elm.attributeValue(attName);
        if (null == val) {
            String errMsg = "";
            errMsg += "attribute '" + attName + "' not found";
            errMsg += " in XML tag '" + elm.getName() + "'";
            ErrorUtil.set(-1, errMsg);
        }

        /* return the value of the attribute, if any. */
        return val;
    }
}
