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

namespace LightForms.IR
{
    public class App
    {
        /// <summary>
        /// Application info.
        /// </summary>
        private AppInfo _Info;
        public AppInfo Info
        {
            get { return this._Info; }
            set { this._Info = value; }
        }

        /// <summary>
        /// Place holder for entity information.
        /// Indexed by name.
        /// </summary>
        private SortedDictionary<string, ContentType> _ContentTypes;
        public SortedDictionary<string, ContentType> ContentTypes
        {
            get { return _ContentTypes; }
            set { _ContentTypes = value; }
        }

        /// <summary>
        /// Placeholder for field maps.
        /// Indexed by name.
        /// </summary>
        private SortedDictionary<string, FieldMap> _FieldMaps;
        public SortedDictionary<string, FieldMap> FieldMaps
        {
            get { return _FieldMaps; }
            set { _FieldMaps = value; }
        }

        /// <summary>
        /// Initialize internal object state.
        /// </summary>
        public App()
        {
            ContentTypes = new SortedDictionary<string, ContentType>();
            FieldMaps = new SortedDictionary<string, FieldMap>();
        }

        /// <summary>
        /// ReadFromXML an application specification an XElement. Build the internal state for this object
        /// based on the values found inside.
        /// </summary>
        /// <param name="elm">the XElement to read</param>
        public void ReadFromXML(XElement elm)
        {
            /* check name of element. */
            if (elm.Name != "app")
            {
                throw new InvalidXMLFormatException("element is not named 'app'");
            }

            /* get all children elements. */
            IEnumerable<XElement> elms = from item in elm.Elements() select item;

            /* based on name, choose the right class. */
            foreach (XElement specElm in elms)
            {
                switch (specElm.Name.LocalName.ToLower())
                {
                    case "app-info":
                        Info = new AppInfo();
                        Info.ReadFromXML(specElm);
                        break;
                    case "content-type":
                        ContentType ent = new ContentType();
                        ent.ReadFromXML(specElm);
                        ContentTypes.Add(ent.Name, ent);
                        break;
                    case "field-map":
                        FieldMap fldMap = new FieldMap();
                        fldMap.ReadFromXML(specElm);
                        FieldMaps.Add(fldMap.Name, fldMap);
                        break;
                    default:
                        break;
                }
            }
        }

        /// <summary>
        /// Read from XML file. ReadSpecificationFromXMLFile the field map from an XML file.
        /// </summary>
        /// <param name="xmlfile">the name for the file</param>
        public void ReadFromXMLFile(string xmlfile)
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
            XElement elm = new XElement("app");           

            /* output info. */
            elm.Add(new XComment(" application info "));
            elm.Add(Info.WriteToXML());

            /* output entities. */
            elm.Add(new XComment(" data model content types "));
            foreach (ContentType ent in ContentTypes.Values)
            {
                elm.Add(ent.WriteToXML());
            }

            /* output field maps. */
            elm.Add(new XComment(" field maps "));
            foreach (FieldMap fmap in FieldMaps.Values)
            {
                elm.Add(fmap.WriteToXML());
            }

            /* all done. */
            return elm;
        }

        /// <summary>
        /// Generate a full XML document.
        /// </summary>
        /// <returns>the XML document object</returns>
        public XDocument WriteToXMLDocument()
        {
            /* main xml document. */
            XDocument doc = new XDocument();
            doc.Add(WriteToXML());
            return doc;
        }

        /// <summary>
        /// String representation.
        /// Implemented as an XML output.
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
            return WriteToXMLDocument().ToString();
        }
    }
}
