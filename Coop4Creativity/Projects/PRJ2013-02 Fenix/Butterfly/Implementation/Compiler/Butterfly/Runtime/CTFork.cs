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
    public class CTFork:CTPipeline
    {
        /* const stuff. */
        private const string XML_TAG_NAME = "fork";

        /// <summary>
        /// Constructor.
        /// Pass the compier reference up the class tree.
        /// </summary>
        /// <param name="runtime"></param>
        public CTFork()
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
    }
}
