using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;
using System.Reflection;
using Butterfly.Error;
using Butterfly.Runtime;

namespace Butterfly.Runtime
{
    public class CTLoadModule:Construct
    {
        /* const stuff. */
        private const string XML_TAG_NAME = "load-module";

        /* name for engine. */
        private string _Path;
        private string _Name;

        /// <summary>
        /// Constructor.
        /// Pass the compier reference up the class tree.
        /// </summary>
        /// <param name="runtime"></param>
        public CTLoadModule()
        {
            base.XmlNameTag = XML_TAG_NAME;
            _Path = default(string);
            _Name = default(string);  
        }

        /// <summary>
        /// Do the actual load of the class.
        /// </summary>
        public override void Run()
        {
            CurrentContext.Verbose("loading assembly '" + _Path + "'", OptionsGlobal.VerboseLevel.High);
            Assembly assembly = Assembly.LoadFrom(_Path);

            CurrentContext.Verbose("adding module '" + _Name + "' to system", OptionsGlobal.VerboseLevel.High);
            CurrentContext.AddModule(_Name, assembly);
        }

        /// <summary>
        /// Read a load engine specification from XML. 
        /// </summary>
        /// <param name="elm"></param>
        public override void ReadFromXML(XElement elm)
        {
            /* common tasks. */
            base.ReadFromXML(elm);

            /* extract name for engine. */
            if (elm.Attribute("name") == null)
            {
                throw new InvalidXMLFormatException("element does not have an attribute 'name'");
            }
            _Name = elm.Attribute("name").Value;

            /* extract assembly. */
            if (elm.Attribute("path") == null)
            {
                throw new InvalidXMLFormatException("element does not have an attribute 'path'");
            }
            _Path = elm.Attribute("path").Value;
        }

        /// <summary>
        /// Write out the construct XML spec.
        /// </summary>
        /// <returns></returns>
        public override XElement WriteToXML()
        {
            XElement elm = base.WriteToXML();
            elm.Add(new XAttribute("name", _Name));
            elm.Add(new XAttribute("path", _Path));
            return elm;
        }
    }
}
