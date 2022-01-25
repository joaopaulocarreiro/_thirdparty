using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;
using Butterfly.Error;

namespace Butterfly.Runtime
{
    public abstract class Construct:RunUnit, IConstruct
    {
        /// <summary>
        /// Name for the XML tag of the constructor.
        /// </summary>
        public string XmlNameTag;

        /// <summary>
        /// Default constructor.
        /// </summary>
        public Construct()
        {
            XmlNameTag = GetType().Name;
        }

        /// <summary>
        /// Read the compiler construct from an XML file.
        /// </summary>
        /// <param name="xmlfile">the name and location of the file to read</param>
        public void ReadFromXML(string xmlfile)
        {
            ReadFromXML(XElement.Load(xmlfile));
        }

        /// <summary>
        /// Read the construct spec from a XML element.
        /// </summary>
        /// <param name="elm">the element to read from</param>
        public virtual void ReadFromXML(XElement elm)
        {
            /* check name of element. */
            if (elm.Name != XmlNameTag)
            {
                throw new InvalidXMLFormatException("element is not named '" + XmlNameTag + "'");
            }
        }

        /// <summary>
        /// Geenrate a XML element from the construct.
        /// </summary>
        /// <returns>the XML element that reflects the construct</returns>
        public virtual XElement WriteToXML()
        {
            XElement elm = new XElement(XmlNameTag);
            return elm;
        }

        /// <summary>
        /// String representation.
        /// Implemented as an XML output.
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
            return WriteToXML().ToString();
        }
    }
}
