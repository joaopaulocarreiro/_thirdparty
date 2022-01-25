using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;

namespace Janua.Core.Schema
{
    public class Field
    {
        /* Identifier for field. */
        private int _Id;
        public int Id
        {
            get { return this._Id; }
            set { this._Id = value; }
        }

        /* Name for field. */
        private string _Name;
        public string Name
        {
            get { return this._Name; }
            set { this._Name = value; }
        }

        /* Type name for field. */
        private string _TypeName;
        public string TypeName
        {
            get { return this._TypeName; }
            set { this._TypeName = value; }
        }

        /* Attributes for the field declaration. */
        private SortedDictionary<string, string> _Attributes;
        public SortedDictionary<string, string> Attributes
        {
            get { return this._Attributes; }
            set { this._Attributes = value; }
        }

        /**
         * Empty constructor.
         * Initialize properties.
         */
        public Field()
        {
            Name = null;
            Attributes = new SortedDictionary<string, string>();
        }

        /**
         * Find an attribute value, if it exists.
         * It returns true and sets 'out' if the attribute exists,
         * otherwise this returns false, and 'value' is set to null.
         */
        public bool FindAttributeValue(string name, out string value)
        {
            return Attributes.TryGetValue(name, out value);
        }

        /**
         * ReadFromXML field map from an XML element.
         */
        public void ReadFromXML(XElement elm)
        {
            /* check element name. */
            if (elm.Name.LocalName.ToLower() != "field")
            {
                throw new Exception("element is not named 'field'");
            }

            /* check if element has an attribute called name. */
            if (elm.Attribute("name") == null)
            {
                throw new Exception("element does not have a 'name' attribute");
            }

            /* extract field map name. */
            Name = elm.Attribute("name").Value;

            /* check if element has an attribute called name. */
            if (elm.Attribute("type") == null)
            {
                throw new Exception("element does not have a 'type' attribute");
            }

            /* extract field map name. */
            TypeName = elm.Attribute("type").Value;

            /* extract the attributes of the field. */
            IEnumerable<XElement> attrsElms = from item in elm.Elements("attr") select item;
            foreach (XElement attrElm in attrsElms)
            {
                if (attrElm.Attribute("name") != null)
                {
                    if (attrElm.Attribute("value") != null)
                    {
                        Attributes.Add(attrElm.Attribute("name").Value, attrElm.Attribute("value").Value);
                    }
                }
            }
        }

        /**
         * Write the field definition information to an XML element.
         */
        public XElement WriteToXML()
        {
            /* create a new XML element. */
            XElement elm = new XElement("field");
            elm.Add(new XAttribute("name", Name));
            elm.Add(new XAttribute("type", TypeName));

            /* add every attribute of the field. */
            foreach (string name in Attributes.Keys)
            {
                string value;
                if (Attributes.TryGetValue(name, out value))
                {
                    XElement attrElm = new XElement("attr");
                    attrElm.Add(new XAttribute("name", name));
                    attrElm.Add(new XAttribute("value", value));
                    elm.Add(attrElm);
                }
            }

            /* all done. */
            return elm;
        }

        /**
         * Fuse the passed field with this one.
         * Fusing field templates meanings adding all the 
         * second map's attributes to this one. All attributes
         * that are NOT already defined, are added.
         */
        public void Fuse(Field template)
        {
            /* check if the two templates have the same name. */
            if (Name != template.Name) return;

            /* check if the two types are the same. */
            if (TypeName != template.TypeName) return;

            /**
             * Fusion implies taking the second map attributes and
             * add them to this field map.
             */
            foreach (string name in template.Attributes.Keys)
            {
                string new_value;
                string current_value;

                /**
                 * If the field exists in both templates, then we fuse 
                 * the attributes.
                 */
                if (template.Attributes.TryGetValue(name, out new_value))
                {
                    /**
                     * If the both field tempaltes ave the same attributes name, 
                     * then we keep the existing one. The new one is`*NOT* fused.
                     */
                    if (!(FindAttributeValue(name, out current_value)))
                    {
                        Attributes.Add(name, new_value);
                    }
                }
            }
        }

        /**
         * ToString method.
         * This is just a XML representation of the field map.
         */
        public override string ToString()
        {
            return WriteToXML().ToString();
        }
    }
}
