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
using System.Reflection;

namespace Butterfly.Runtime
{
    public class Runtime
    {
        /* public properties state. */
        public CTCompiler Compiler;
        public Context Context;

        /// <summary>
        /// Create a new compiler specification.
        /// </summary>
        public Runtime()
        {
            Compiler = new CTCompiler();
            Context = new Context();
        }

        /// <summary>
        /// Run the compiler.
        /// </summary>
        public void Run()
        {
            RunConstruct(Compiler, Context);
        }

        /// <summary>
        /// Read a compiler construct from a XML element.
        /// </summary>
        /// <param name="elm">the XElement to read</param>
        public static IConstruct ReadConstructFromXML(XElement elm)
        {
            /* default construct is null. */
            IConstruct cons = null;

            /* based on name, choose the right class. */
            switch (elm.Name.LocalName.ToLower())
            {
                case "load-engine":
                    cons = new CTLoadEngine();
                    break;
                case "load-module":
                    cons = new CTLoadModule();
                    break;
                case "pipeline":
                    cons = new CTPipeline();
                    break;
                case "call":
                    cons = new CTCall();
                    break;
                case "fork":
                    cons = new CTFork();
                    break;
                case "method":
                    cons = new CTMethod();
                    break;
                case "call-method":
                    cons = new CTCallMethod();
                    break;
                default:
                    break;
            }

            cons.ReadFromXML(elm);
            /* all done, return construct. */
            return cons;
        }

        /// <summary>
        /// Run a compiler construct.
        /// </summary>
        /// <param name="cons">the construct to run</param>
        public static void RunConstruct(IConstruct cons, Context cont)
        {
            cont.Verbose("[cb]:" + cons.GetFullName() + " location:" + cont.EngineLocation() , OptionsGlobal.VerboseLevel.High);
            cons.BeforeRun(cont);

            cont.Verbose("[cr]:" + cons.GetFullName(), OptionsGlobal.VerboseLevel.High);
            cons.Run();
        }

        public static void _Warning(object msg)
        {
            System.Console.WriteLine(msg.ToString());
        }

        public static void _FatalError(object msg)
        {
            System.Console.WriteLine(msg.ToString());
        }

        /// <summary>
        /// String representation.
        /// Implemented as an XML output.
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
            return Compiler.ToString();
        }        
    }
}
