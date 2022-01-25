using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Janua.Core.VM
{
    public class Machine
    {
        /* Name of machine. */
        private string _Name;
        public string Name
        {
            get { return _Name; }
            set { _Name = value; }
        }

        /* List of registered commands. */
        private SortedDictionary<string, SchemaInsn> _Commands;
        public SortedDictionary<string, SchemaInsn> Commands
        {
            get { return _Commands; }
            set { _Commands = value; }
        }

        /// <summary>
        /// Add a new command to virtual machine.
        /// If command is null or if name of command is not valid
        /// then it is NOT added. Also, is a command with the same name already
        /// exists, no command is added.
        /// </summary>
        /// <param name="cmd">the command to add</param>
        public void Add(SchemaInsn cmd)
        {
            /* check if object is valid. */
            if (null != cmd)
            {
                /* check if command name is valid. */
                if (!string.IsNullOrEmpty(cmd.Name))
                {
                    /* add command if it does not exist. */
                    if (!Commands.ContainsKey(cmd.Name))
                    {
                        Commands.Add(cmd.Name, cmd);
                    }
                }
            }
        }
    }
}
