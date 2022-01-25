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

namespace Butterfly.Runtime
{
    public class CTPipeline:Construct
    {
        /* const stuff. */
        private const string XML_TAG_NAME = "pipeline";

        /* the actual pipeline of compiler constructs. */
        protected List<IConstruct> _Pipeline;

        /// <summary>
        /// Constructor.
        /// Pass the compier reference up the class tree.
        /// </summary>
        /// <param name="runtime"></param>
        public CTPipeline()
        {
            base.XmlNameTag = XML_TAG_NAME;
        }

        /// <summary>
        /// Run the pipeline, executing every construct in turn.
        /// </summary>
        public override void Run()
        {
            foreach (IConstruct cons in _Pipeline)
            {
                Runtime.RunConstruct(cons, CurrentContext);
            }
        }

        /// <summary>
        /// Read a load engine specification from XML. 
        /// </summary>
        /// <param name="elm">the XML element to parse</param>
        public override void ReadFromXML(XElement elm)
        {
            /* common tasks. */
            base.ReadFromXML(elm);

            /* read all sub elements. */
            _Pipeline = new List<IConstruct>();

            /* get all children elements. */
            IEnumerable<XElement> elms = from item in elm.Elements() select item;
            foreach (XElement child in elms)
            {
                IConstruct cons = Runtime.ReadConstructFromXML(child);
                if (null != cons)
                {
                    _Pipeline.Add(cons);
                }
            }
        }

        /// <summary>
        /// Write out the construct XML spec.
        /// </summary>
        /// <returns></returns>
        public override XElement WriteToXML()
        {
            XElement elm = base.WriteToXML();
            foreach (IConstruct cons in _Pipeline)
            {
                elm.Add(cons.WriteToXML());
            }
            return elm;
        }
    }
}
