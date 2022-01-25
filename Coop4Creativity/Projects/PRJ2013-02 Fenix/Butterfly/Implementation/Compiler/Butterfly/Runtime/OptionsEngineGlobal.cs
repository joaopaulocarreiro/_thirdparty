using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;
using Butterfly.Error;

namespace Butterfly.Runtime
{
    public class OptionsEngineGlobal
    {
        /* const stuff. */
        private const string XML_TAG_NAME = "options";

        private SortedDictionary<ComparableKeyValuePair, string> _Options;

        /// <summary>
        /// Default constructor.
        /// </summary>
        public OptionsEngineGlobal()
        {
            _Options = new SortedDictionary<ComparableKeyValuePair, string>();
        }

        public void AddOption(string engine, string name, string value)
        {
            /* standard parameter checks. */
            if (null == value) return;
            if (string.IsNullOrEmpty(name) || string.IsNullOrWhiteSpace(name)) return;

            /* add variable to state. */
            _Options[new ComparableKeyValuePair(engine, name)] = value;
        }

        public bool GetOption(string engine, string name, out string value)
        {
            return _Options.TryGetValue(new ComparableKeyValuePair(engine, name), out value);
        }

        /// <summary>
        /// Read the engine options from an XML file.
        /// </summary>
        /// <param name="xmlfile">the name and location of the file to read</param>
        public void ReadFromXML(string xmlfile)
        {
            ReadFromXML(XElement.Load(xmlfile));
        }

        /// <summary>
        /// Read a load engine specification from XML. 
        /// </summary>
        /// <param name="elm">the XML element to parse</param>
        public void ReadFromXML(XElement elm)
        {
            /* check name of element. */
            if (elm.Name != XML_TAG_NAME)
            {
                throw new InvalidXMLFormatException("element is not named '" + XML_TAG_NAME + "'");
            }
          
            /* get all children elements. */
            IEnumerable<XElement> elms = from item in elm.Elements() select item;
            foreach (XElement child in elms)
            {
                string optionEngine;
                string optionName;
                string optionValue;

                if (child.Name == "option")
                {
                    /* check if element has an attribute 'engine'.*/
                    if (child.Attribute("engine") == null)
                    {
                        throw new InvalidXMLFormatException("element does not have an attribute 'engine'");
                    }

                    /* check if element has an attribute 'name'.*/
                    if (child.Attribute("name") == null)
                    {
                        throw new InvalidXMLFormatException("element does not have an attribute 'name'");
                    }

                    /* check if element has an attribute 'name'.*/
                    if (child.Attribute("value") == null)
                    {
                        throw new InvalidXMLFormatException("element does not have an attribute 'value'");
                    }

                    /* extract values. */
                    optionEngine = child.Attribute("engine").Value;
                    optionName = child.Attribute("name").Value;
                    optionValue = child.Attribute("value").Value;

                    /* add it to options. */
                    AddOption(optionEngine, optionName, optionValue);
                }
            }
        }

        private class ComparableKeyValuePair:IComparable
        {
            public string Key;
            public string Value;

            public ComparableKeyValuePair(string a, string b)
            {
                Key = a;
                Value = b;
            }

            public int CompareTo(object obj)
            {
                if (obj is ComparableKeyValuePair)
                {
                    ComparableKeyValuePair val = (ComparableKeyValuePair)obj;
                    if ((val.Key == Key) && (val.Value == Value)) return 0;
                    return val.Key.CompareTo(Key);
                }
                return -1;
            }
        }
    }
}
