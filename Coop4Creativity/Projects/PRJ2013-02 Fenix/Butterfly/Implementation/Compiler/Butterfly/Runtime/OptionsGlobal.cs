using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;
using Butterfly.Error;

namespace Butterfly.Runtime
{
    public class OptionsGlobal
    {
        /// <summary>
        /// Levels for verbose output.
        /// </summary>
        public enum VerboseLevel
        {
            None = 0,
            Low = 1,
            Medium = 2,
            High = 3
        }

        /* const stuff. */
        private const string XML_TAG_NAME = "options";

        public string CompilerFilename;
        public string EngineOptionsFilename;
        public VerboseLevel Verbose;

        public bool HasCompilerFilename
        {
            get
            {
                return CompilerFilename != null;
            }
        }

        public bool HasEngineOptionsFilename
        {
            get
            {
                return EngineOptionsFilename != null;
            }
        }

        public OptionsGlobal()
        {
            CompilerFilename = default(string);
            EngineOptionsFilename = default(string);
            Verbose = VerboseLevel.None;
        }

        /// <summary>
        /// Read the options from an XML file.
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
            if (elm.Name.ToString().ToLower() != XML_TAG_NAME)
            {
                throw new InvalidXMLFormatException("element is not named '" + XML_TAG_NAME + "'");
            }

            /* get all children elements. */
            IEnumerable<XElement> elms = from item in elm.Elements() select item;
            foreach (XElement child in elms)
            {
                string optionName;
                string optionValue;

                if (child.Name.ToString().ToLower() == "option")
                {
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
                    optionName = child.Attribute("name").Value;
                    optionValue = child.Attribute("value").Value;

                    /* add it to options. */
                    switch (optionName.ToLower())
                    {
                        case "compiler-filename":
                            CompilerFilename = optionValue;
                            break;
                        case "engine-options-filename":
                            EngineOptionsFilename = optionValue;
                            break;
                        case "verbose-level":
                            switch (optionValue.ToLower())
                            {
                                case "none":
                                    Verbose = VerboseLevel.None;
                                    break;
                                case "low":
                                    Verbose = VerboseLevel.Low;
                                    break;
                                case "medium":
                                    Verbose = VerboseLevel.Medium;
                                    break;
                                case "high":
                                    Verbose = VerboseLevel.High;
                                    break;
                                default:
                                    throw new Exception("Invalid value for option verbose-level ('" + optionValue + "')");
                            }
                            break;
                        default:
                            throw new Exception("Option '" + optionName + "' not recognized!");
                    }
                }
            }
        }

    }
}
