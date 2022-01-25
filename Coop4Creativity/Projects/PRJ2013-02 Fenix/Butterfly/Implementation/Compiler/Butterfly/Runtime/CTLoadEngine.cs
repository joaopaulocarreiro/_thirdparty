using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;
using Butterfly.Error;
using Butterfly.Runtime;
using System.Reflection;

namespace Butterfly.Runtime
{
    public class CTLoadEngine:Construct
    {
        /* const stuff. */
        private const string XML_TAG_NAME = "load-engine";

        /* name for engine. */
        private string _Name;
        private string _ModuleName;
        private string _Class;

        /* actual engine object. */
        private IEngine _Engine;

        /// <summary>
        /// Constructor.
        /// Pass the compier reference up the class tree.
        /// </summary>
        /// <param name="runtime"></param>
        public CTLoadEngine()
        {
            base.XmlNameTag = XML_TAG_NAME;
            _Name = default(string);
            _ModuleName = default(string);
            _Class = default(string);
            _Engine = default(IEngine);
        }

        /// <summary>
        /// Do the actual load of the class.
        /// </summary>
        public override void Run()
        {
            /* get assembly. */
            Assembly module;
            CurrentContext.GetModule(_ModuleName, out module);
            if (null != module)
            {
                /* we now have the assembly, try to load the engine. */
                /*foreach (Module m in module.GetLoadedModules())
                {
                    System.Console.WriteLine(m.FullyQualifiedName);
                }
                */
                /*
                CurrentContext.Verbose("module fullname:" + module.FullName, OptionsGlobal.VerboseLevel.High);
                CurrentContext.Verbose("module in GAC?:" + module.GlobalAssemblyCache, OptionsGlobal.VerboseLevel.High);
                CurrentContext.Verbose("module is fully trusted?:" + module.IsFullyTrusted, OptionsGlobal.VerboseLevel.High);
                CurrentContext.Verbose("module location:" + module.Location, OptionsGlobal.VerboseLevel.High);

                CurrentContext.Verbose("module types:", OptionsGlobal.VerboseLevel.High);
                foreach (Type t in module.GetTypes())
                {
                    CurrentContext.Verbose("  type[" + i++ + "]:" + t.FullName, OptionsGlobal.VerboseLevel.High);
                }
                */
                /* ok, now we just need to instantiate a new class and add it to the pool. */

                Type engineType = module.GetType(_Class);
                if (null != engineType)
                {
                    _Engine = (IEngine)System.Activator.CreateInstance(engineType);
  
                    /* add the engine to this system. */
                    if (null != _Engine)
                    {
                        _Engine.SetName(_Name);
                        _Engine.SetModuleName(_ModuleName);
                        _Engine.SetClass(_Class);

                        CurrentContext.AddEngine(_Name, _Engine);

                        VerboseHigh("engine loaded [" + _Name + ", " + _Class + "]");
                    }
                }

            }
            else
            {
                Runtime._FatalError("module '" + _ModuleName + "' not found in system!");
            }
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
            if (elm.Attribute("module") == null)
            {
                throw new InvalidXMLFormatException("element does not have an attribute 'module'");
            }
            _ModuleName = elm.Attribute("module").Value;

            /* extract class for engine. */
            if (elm.Attribute("class") == null)
            {
                throw new InvalidXMLFormatException("element does not have an attribute 'class'");
            }
            _Class = elm.Attribute("class").Value;
        }

        /// <summary>
        /// Write out the construct XML spec.
        /// </summary>
        /// <returns></returns>
        public override XElement WriteToXML()
        {
            XElement elm = base.WriteToXML();
            elm.Add(new XAttribute("name", _Name));
            elm.Add(new XAttribute("module", _ModuleName));
            elm.Add(new XAttribute("class", _Class));
            return elm;
        }
    }
}
