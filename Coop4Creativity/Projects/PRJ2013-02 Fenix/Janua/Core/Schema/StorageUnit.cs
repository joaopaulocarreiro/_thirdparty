using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;

namespace Janua.Core.Schema
{
    public class StorageUnit
    {
        /* Identifier for Storage Unit. */
        private int _Id;
        public int Id
        {
            get { return this._Id; }
            set { this._Id = value; }
        }

        /* Name of Storage Unit. */
        private string _Name;
        public string Name
        {
            get { return this._Name; }
            set { this._Name = value; }
        }

        /* List of Content Types identifiers. */
        private List<string> _ContentTypes;
        public List<string> ContentTypes
        {
            get { return this._ContentTypes; }
            set { this._ContentTypes = value; }
        }

        /**
         * Empty constructor.
         * Initialization of properties.
         */
        public StorageUnit()
        {
            Id = -1;
            Name = default(string);
            ContentTypes = new List<string>();
        }

        /**
         * Check if storage unit supports a content type.
         * Returns true/false in case this storage unit supports the 
         * content type name.
         */
        public bool SupportsContentType(string name)
        {
            return ContentTypes.Contains(name);
        }

        /**
         * Read from an XML element.
         */
        public void ReadFromXML(XElement elm)
        {
            /* check name of element. */
            if (elm.Name.LocalName.ToLower() != "storage-unit")
            {
                throw new Exception("element is not named 'storage-unit'");
            }

            /* check if element has an attribute 'name'.*/
            if (elm.Attribute("name") == null)
            {
                throw new Exception("element does not have an attribute 'name'");
            }

            /* start extracting information. */
            Name = elm.Attribute("name").Value;

            /* add content type reference */
            IEnumerable<XElement> fieldElms = from item in elm.Elements("content-type") select item;
            foreach (XElement ctypeElm in fieldElms)
            {
                /* check if element has an attribute 'name'.*/
                if (ctypeElm.Attribute("name") == null)
                {
                    throw new Exception("element 'content-type' does not have an attribute 'name'");
                }

                /* start extracting information. */
                string ctypeName = ctypeElm.Attribute("name").Value;

                /* add to existing content types. */
                ContentTypes.Add(ctypeName);                    
            }
        }

        /**
         * Read the storage unit specification from an XML file.
         */
        public void ReadFromXML(string xmlfile)
        {
            ReadFromXML(XElement.Load(xmlfile));
        }

        /**
         * Write the storage unit specification to a XML element.
         */
        public XElement WriteToXML()
        {
            /* start creating the element. */
            XElement elm = new XElement("storage-unit");

            /* add name. */
            if (!string.IsNullOrEmpty(Name))
            {
                elm.Add(new XAttribute("name", Name));
            }

            /* add every content type name. */
            foreach (string ctypeName in ContentTypes)
            {
                XElement ctypeElm = new XElement("content-type");
                ctypeElm.Add(new XAttribute("name", ctypeName));
                elm.Add(ctypeElm);
            }

            /* all done. */
            return elm;
        }

        /**
         * Fuse two storage units.
         * The basic algorithm is: Import all the content type 
         * names associated with the storage unit to this one. Only if
         * the two storage units have the same name.
         */
        public void Fuse(StorageUnit unit)
        {
            /* check if the two templates are equal. */
            if (!(Name == unit.Name)) return;

            /* add every content type name if it doesnt exist. */
            foreach (string ctypeName in unit.ContentTypes)
            {
                if (!ContentTypes.Contains(ctypeName))
                {
                    ContentTypes.Add(ctypeName);
                }
            }
        }

        /**
         * ToString method for form map.
         * XML representation.
         */
        public override string ToString()
        {
            return WriteToXML().ToString();
        }
    }
}
