/**
 * Butterfly Runtime v1.0
 * Generation System
 * Author: João Paulo Carreiro
 * Date: MAy 14, 2011
 */
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;
using Butterfly.Error;

namespace Butterfly.Runtime
{
    public class CTCompiler:CTPipeline
    {
        /* const stuff. */
        private const string XML_TAG_NAME = "compiler";

        private string _Name;

        /// <summary>
        /// Constructor.
        /// Pass the compier reference up the class tree.
        /// </summary>
        /// <param name="runtime"></param>
        public CTCompiler()
        {
            base.XmlNameTag = XML_TAG_NAME;
        }

        /// <summary>
        /// Read a load engine specification from XML. 
        /// </summary>
        /// <param name="elm">the XML element to parse</param>
        public override void ReadFromXML(XElement elm)
        {
            /* common tasks. */
            base.ReadFromXML(elm);

            /* check if element has an attribute 'name'.*/
            if (elm.Attribute("name") == null)
            {
                throw new InvalidXMLFormatException("element does not have an attribute 'name'");
            }

            /* name for compiler. */
            _Name = elm.Attribute("name").Value;
        }

        /// <summary>
        /// Write out the construct XML spec.
        /// </summary>
        /// <returns></returns>
        public override XElement WriteToXML()
        {
            XElement elm = base.WriteToXML();
            elm.Add(new XAttribute("name", _Name));
            return elm;
        }
    }
}
