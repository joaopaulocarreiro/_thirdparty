/**
 * Butterfly Runtime v1.0
 * Generation System
 * Author: João Paulo Carreiro
 * Date: MAy 14, 2011
 */
using System;
using System.Collections.Generic;
using System.Linq;
using System.Xml.Linq;
using System.Web;
using Butterfly.Error;

namespace LightForms.IR
{
    /// <summary>
    /// FieldMap
    /// Class to model a list of field mappings.
    /// Field mappings allow data sources to read a set of field names and offer
    /// aplications another set of fields. 
    /// </summary>
    public class FieldMap
    {
        /// <summary>
        /// _Name for field map.
        /// Used to identify it uniquely.
        /// </summary>
        private string _Name;
        public string Name {
            get { return this._Name; }
            set { this._Name = value; }
        }

        /// <summary>
        /// Mapping data structure.
        /// Implemented as a sorted dictionary.
        /// </summary>
        private SortedDictionary<string, string> _Map;
        public SortedDictionary<string, string> Map {
            get { return this._Map; }
            set { this._Map = value; }
        }

        /// <summary>
        /// Instatiation of class.
        /// Initialize object state. Default value for name is
        /// null.
        /// </summary>
        public FieldMap()
        {
            Name = default(string);
            Map = new SortedDictionary<string, string>(); 
        }

        /// <summary>
        /// Try to find a field map.
        /// </summary>
        /// <param name="src">the source field to find</param>
        /// <param name="dst">the destination field name, if any</param>
        /// <returns>true if the field was found, false otherwise</returns>
        public bool Find(string src, out string dst) {
            return Map.TryGetValue(src, out dst);
        }

        /// <summary>
        /// ReadFromXML field map from an XElement. Build the internal state for this object
        /// based on the values found inside.
        /// </summary>
        /// <param name="elm">the XElement to read</param>
        public void ReadFromXML(XElement elm)
        {
            /* check name of element. */
            if (elm.Name.LocalName.ToLower() != "field-map")
            {
                throw new InvalidXMLFormatException("element is not named 'field-map'");
            }

            /* check if element has an attribute 'name'.*/
            if (elm.Attribute("name") == null)
            {
                throw new InvalidXMLFormatException("element does not have an attribute 'name'");
            }

            /* start extracting information. */
            Name = elm.Attribute("name").Value;

            /* add every field map. */
            IEnumerable<XElement> fieldElms = from item in elm.Elements("map") select item;

            /* add all the map elements. */
            foreach (XElement mapElm in fieldElms)
            {
                if ((!string.IsNullOrEmpty(mapElm.Attribute("src").Value)) && 
                    (!string.IsNullOrEmpty(mapElm.Attribute("dst").Value)))
                {
                    Map.Add(mapElm.Attribute("src").Value, mapElm.Attribute("dst").Value);
                }
            }
        }

        /// <summary>
        /// ReadFromXML from XML file. ReadFromXML the field map from an XML file.
        /// </summary>
        /// <param name="xmlfile">the name for the file</param>
        public void Read(string xmlfile) 
        {
            ReadFromXML(XElement.Load(xmlfile));
        }

        /// <summary>
        /// Write the field map to an XElement.
        /// </summary>
        /// <returns>the XElement datatype</returns>
        public XElement WriteToXML()
        {
            /* start creating the element. */
            XElement elm = new XElement("field-map");
            elm.Add(new XAttribute("name", Name));

            /* add every field map. */
            foreach (string src in Map.Keys) 
            {
                XElement map = new XElement("map");
                map.Add(new XAttribute("src", src));
                map.Add(new XAttribute("dst", Map[src]));
                elm.Add(map);
            }            

            /* all done. */
            return elm;
        }

        /// <summary>
        /// Fuse two field maps. This method fuses with this object another
        /// field map. This changes this instance augumenting it with field
        /// maps found.
        /// </summary>
        /// <param name="map">the map to fuse</param>       
        public void Fuse(FieldMap map)
        {
            /* check if the two field maps are equal. */
            if (!(Name == map.Name)) return;

            /* add every field map. */
            foreach (string src in map.Map.Keys)
            {
                string dst = default(string);
                if (!Find(src, out dst))
                {
                    if ((null != dst) && (string.Empty != dst))
                    {
                        Map.Add(src, dst);
                    }
                }

            }
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

