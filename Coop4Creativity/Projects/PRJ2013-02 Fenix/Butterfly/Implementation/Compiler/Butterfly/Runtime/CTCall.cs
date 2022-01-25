using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;
using Butterfly.Error;

namespace Butterfly.Runtime
{
    public class CTCall:Construct
    {
        /* const stuff. */
        private const string XML_TAG_NAME = "call";

        /// <summary>
        /// Name of engine to call, this shoud be the name given in the
        /// load engine "name" attribute.
        /// </summary>
        private string _Name;

        /// <summary>
        /// Constructor.
        /// Pass the compiler reference up the class tree.
        /// </summary>
        /// <param name="runtime"></param>
        public CTCall()
        {
            base.XmlNameTag = XML_TAG_NAME;
        }

        /// <summary>
        /// Execute the engine, passing it the current execution context.
        /// </summary>
        public override void Run()
        {
            IEngine eng;
            CurrentContext.GetEngine(_Name, out eng);

            /* run engine object. */
            if (null != eng)
            {
                CurrentContext.PushEngineLocation(eng.GetName());

                /* verbose messages (before run). */
                VerboseLow("[eb]:" + eng.GetName() + " location: " + CurrentContext.EngineLocation());
                eng.BeforeRun(CurrentContext);

                /* verbose messages (run). */
                VerboseLow("[er]:" + eng.GetName());
                eng.Run();

                /* verbose messages (after run). */
                VerboseLow("[ea]:" + eng.GetName());
                eng.AfterRun();

                CurrentContext.PopEngineLocation();
            }
        }

        /// <summary>
        /// Read a load engine specification from XML. 
        /// </summary>
        /// <param name="elm">the element to read</param>
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
        }

        /// <summary>
        /// Write out the construct XML spec.
        /// </summary>
        /// <returns></returns>
        public override XElement WriteToXML()
        {
            XElement elm = base.WriteToXML();
            elm.Add(new XAttribute("name", _Name));
            return elm;
        }
    }
}
