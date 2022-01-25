using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Butterfly.Runtime;

namespace Butterfly
{
    public class EntryPoint
    {
        /// <summary>
        /// Entry point for program.
        /// </summary>
        /// <param name="args">list of command line arguments from operating system</param>
        public static void Main(string[] args)
        {
            _ProcessOptions(_ParseCommandLineArguments(args));
        }

        /// <summary>
        /// Parse the command line options.
         /// </summary>
        /// <param name="args">the list of command line options</param>
        /// <returns>the options structure for further processing</returns>
        private static string _ParseCommandLineArguments(string[] args)
        {
            string startFile = default(string);

            /* extract option references and values. */
            for (int i = 0; i < args.Length; i++)
            {
                /* input file. */
                if (args[i].ToLower().Equals("-h"))
                {
                    _ShowHelp();
                    return null;
                }

                /* compiler specfication file. */
                if (args[i].ToLower().Equals("-s") && (i + 1) < args.Length)
                {
                  startFile = args[i + 1];
                  i++;
                }
            }

            /* all done, return the option structure. */
            return startFile;
        }

        /// <summary>
        /// Take a option structure and process it.
        /// </summary>
        /// <param name="opt"></param>
        private static void _ProcessOptions(string startFile)
        {
            /* check if start file has any values. */
            if (string.IsNullOrEmpty(startFile))
            {
                _ErrorMessage("there is nothing to do, no file specified!");
                _ErrorMessage("use -h to show help on options");
            }

            /* process options. */
            try
            {
                Butterfly.Runtime.Runtime runtime = new Runtime.Runtime();

                /* read from file what to do, and possibly more options. */
                runtime.Context.Options.ReadFromXML(startFile);

                /* read engine options, if any. */
                if (runtime.Context.Options.HasEngineOptionsFilename)
                {
                    runtime.Context.EngineOptions.ReadFromXML(runtime.Context.Options.EngineOptionsFilename);
                }

                /* read the compiler spec. */
                if (runtime.Context.Options.HasCompilerFilename)
                {
                    runtime.Compiler.ReadFromXML(runtime.Context.Options.CompilerFilename);
                }

                /* finally run the spec. */
                runtime.Run();               
            }
            catch (Exception e)
            {
                _ErrorMessage(e.Message + "\n" + e.StackTrace);
            }
        }

        /// <summary>
        /// Parse the command line options.
         /// </summary>
        /// <param name="args">the list of command line options</param>
        /// <returns>the options structure for further processing</returns>
        private static void _ShowHelp()
        {
            System.Console.WriteLine("Butterfly Compiler System");
            System.Console.WriteLine("Options:");
            System.Console.WriteLine("  -c  [INPUT FILE]: specify compiler specification");
            System.Console.WriteLine("  -eo [INPUT FILE]: specify options for compiler");
        }

        /// <summary>
        /// Output an error message to the defautl error strea,
        /// </summary>
        /// <param name="obj">the message to output</param>
        private static void _ErrorMessage(object obj)
        {
            System.Console.Error.WriteLine(obj.ToString());
        }
    }
}
