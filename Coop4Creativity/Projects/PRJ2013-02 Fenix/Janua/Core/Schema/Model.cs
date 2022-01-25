using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;

namespace Janua.Core.Schema
{
    public class Model
    {
        /// <summary>
        /// Model info.
        /// </summary>
        private ModelInfo _Info;
        public ModelInfo Info
        {
            get { return this._Info; }
            set { this._Info = value; }
        }

        /// <summary>
        /// Place holder for content type information.
        /// Indexed by name.
        /// </summary>
        private SortedDictionary<string, ContentType> _ContentTypes;
        public SortedDictionary<string, ContentType> ContentTypes
        {
            get { return _ContentTypes; }
            set { _ContentTypes = value; }
        }

        /// <summary>
        /// Place holder for storage unit information.
        /// Indexed by name.
        /// </summary>
        private SortedDictionary<string, StorageUnit> _StorageUnits;
        public SortedDictionary<string, StorageUnit> StorageUnits
        {
            get { return _StorageUnits; }
            set { _StorageUnits = value; }
        }

        /// <summary>
        /// Initialize internal object state.
        /// </summary>
        public Model()
        {
            ContentTypes = new SortedDictionary<string, ContentType>();
            StorageUnits = new SortedDictionary<string, StorageUnit>();
        }

        /// <summary>
        /// ReadFromXML an application specification an XElement. Build the internal state for this object
        /// based on the values found inside.
        /// </summary>
        /// <param name="elm">the XElement to read</param>
        public void ReadFromXML(XElement elm)
        {
            /* check name of element. */
            if (elm.Name != "model")
            {
                throw new Exception("element is not named 'model'");
            }

            /* get all children elements. */
            IEnumerable<XElement> elms = from item in elm.Elements() select item;

            /* based on name, choose the right class. */
            foreach (XElement specElm in elms)
            {
                switch (specElm.Name.LocalName.ToLower())
                {
                    case "model-info":
                        {
                            Info = new ModelInfo();
                            Info.ReadFromXML(specElm);
                        }
                        break;
                    case "content-type":
                        {
                            ContentType x = new ContentType();
                            x.ReadFromXML(specElm);
                            ContentTypes.Add(x.Name, x);
                        }
                        break;
                    case "storage-unit":
                        {
                            StorageUnit x = new StorageUnit();
                            x.ReadFromXML(specElm);
                            StorageUnits.Add(x.Name, x);
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        /// <summary>
        /// Read the model specification from an XML file.
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
            XElement elm = new XElement("model");

            /* output info. */
            elm.Add(new XComment(" info "));
            elm.Add(Info.WriteToXML());

            /* output content types. */
            elm.Add(new XComment(" content types "));
            foreach (ContentType x in ContentTypes.Values)
            {
                elm.Add(x.WriteToXML());
            }

            /* output storage units. */
            elm.Add(new XComment(" storage units "));
            foreach (StorageUnit x in StorageUnits.Values)
            {
                elm.Add(x.WriteToXML());
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
