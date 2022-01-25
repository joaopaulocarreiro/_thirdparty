using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;

namespace Janua.Core.Schema
{
    public class ContentType
    {
        /* Identifier for ContentType. */
        private int _Id;
        public int Id
        {
            get { return this._Id; }
            set { this._Id = value; }
        }

        /* Name of ContentType. */
        private string _Name;
        public string Name
        {
            get { return this._Name; }
            set { this._Name = value; }
        }

        /* Parent of ContentType. */
        private string _Parent = default(string);
        public string Parent
        {
            get { return this._Parent; }
            set { this._Parent = value; }
        }

        /* Fields for ContentType Template. */
        private SortedDictionary<string, Field> _Fields;
        public SortedDictionary<string, Field> Fields
        {
            get { return this._Fields; }
            set { this._Fields = value; }
        }

        /**
         * Empty constructor.
         * Initialization of properties.
         */
        public ContentType()
        {
            Name = default(string);
            Fields = new SortedDictionary<string, Field>();
        }

        /**
         * Find the field map based on a name.
         * Return true if the field map exists, returning its value 
         * in 'temp', or false if the name is not a field map.
         */
        public bool FindFieldTemplate(string name, out Field temp)
        {
            return Fields.TryGetValue(name, out temp);
        }

        /**
         * ReadFromXML map from a XML element.
         */
        public void ReadFromXML(XElement elm)
        {
            /* check name of element. */
            if (elm.Name.LocalName.ToLower() != "content-type")
            {
                throw new Exception("element is not named 'content-type'");
            }

            /* check if element has an attribute 'name'.*/
            if (elm.Attribute("name") == null)
            {
                throw new Exception("element does not have an attribute 'name'");
            }

            /* start extracting information. */
            Name = elm.Attribute("name").Value;

            /* check if element has an attribute 'parent'.*/
            if (elm.Attribute("parent") != null)
            {
                Parent = elm.Attribute("parent").Value;
            }

            /* add every field. */
            IEnumerable<XElement> fieldElms = from item in elm.Elements("field") select item;
            foreach (XElement fieldElm in fieldElms)
            {
                Field field = new Field();
                field.ReadFromXML(fieldElm);
                Fields.Add(field.Name, field);
            }
        }

        /**
         * ReadSpecificationFromXMLFile from an XML file.
         */
        public void ReadFromXML(string xmlfile)
        {
            ReadFromXML(XElement.Load(xmlfile));
        }

        /**
         * Write the entity to an XML element.
         */
        public XElement WriteToXML()
        {
            /* start creating the element. */
            XElement elm = new XElement("content-type");

            /* add name. */
            if (!string.IsNullOrEmpty(Name))
            {
                elm.Add(new XAttribute("name", Name));
            }

            /* add parent content type, if any. */
            if (!string.IsNullOrEmpty(Parent))
            {
                elm.Add(new XAttribute("parent", Parent));
            }

            /* add every field map. */
            foreach (Field fld in Fields.Values)
            {
                elm.Add(fld.WriteToXML());
            }

            /* all done. */
            return elm;
        }

        /**
         * Fuse two entities.
         * The basic algorithm is: If the map does not exist,
         * we add it. If it exists then we fuse all of its atributes.
         */
        public void Fuse(ContentType ent)
        {
            /* check if the two templates are equal. */
            if (!(Name == ent.Name)) return;

            /* add every field map. */
            foreach (Field fld in ent.Fields.Values)
            {
                Field fldTemplate;
                if (FindFieldTemplate(fld.Name, out fldTemplate))
                {
                    fldTemplate.Fuse(fld);
                }
                else
                {
                    Fields.Add(fld.Name, fldTemplate);
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
