using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Janua.Util.Shell
{
    public class CmdLine
    {
        private SortedDictionary<string, ICommand> _Commands;
        private object _Context;

        /// <summary>
        /// Initialize shell command line.
        /// </summary>
        public CmdLine()
        {
            _Commands = new SortedDictionary<string, ICommand>();
            _Context = default(object);
        }

        /// <summary>
        /// Execute shell.
        /// </summary>
        public void Execute()
        {
            /* command name. */
            string command;

            /* optional parameters. */
            string[] parameters;

            /* run cycle until command returns a true value. */
            do
            {
                ParseCommand(ReadCommandLine(), out command, out parameters);
            } while (!ProcessCommand(command, parameters));
        }

        /// <summary>
        /// Read the shell command from standard input.
        /// </summary>
        /// <returns></returns>
        public string ReadCommandLine()
        {
            WritePrompt();
            return System.Console.ReadLine();
        }

        /// <summary>
        /// Parse the command line options.
        /// </summary>
        /// <param name="input"></param>
        /// <param name="command"></param>
        /// <param name="parameters"></param>
        public void ParseCommand(string input, out string command, out string[] parameters)
        {
            /* default values. */
            command = null;
            parameters = null;

            /* standard check. */
            if (string.IsNullOrEmpty(input)) return;

            /* split the input string into parcels. */
            char[] sep = { ' ' };
            string[] parcels = input.Split(sep, StringSplitOptions.RemoveEmptyEntries);

            /* first parcel is the command name. */
            command = parcels[0];

            /* rest of parcels are the command options. */
            parameters = new string[parcels.Length - 1];
            Array.Copy(parcels, 1, parameters, 0, parcels.Length - 1);
        }

        /// <summary>
        /// Process the parsed command.
        /// </summary>
        /// <param name="command"></param>
        /// <param name="parameters"></param>
        public bool ProcessCommand(string command, string[] parameters)
        {
            /* return value, by default value is false. */
            bool retVal = false;

            /* check for command name. */
            if (!string.IsNullOrEmpty(command))
            {
                /* get the command handler and execute command. */
                if (_Commands.ContainsKey(command))
                {
                    ICommand cmd = _Commands[command];

                    /* to be on the safe side, check if command object is valid. */
                    if (null != cmd)
                    {
                        retVal = cmd.Run(parameters, _Context);
                    }
                }
            }

            /* return value from command execution. */
            return retVal;
        }

        /// <summary>
        /// Write the shell prompt information.
        /// </summary>
        public void WritePrompt()
        {
            System.Console.Write("janua>");
        }

        /// <summary>
        /// Output an error message to the defautl error strea,
        /// </summary>
        /// <param name="obj">the message to output</param>
        public void WriteErrorMessage(object obj)
        {
            System.Console.Error.WriteLine(obj.ToString());
        }
    }
}
