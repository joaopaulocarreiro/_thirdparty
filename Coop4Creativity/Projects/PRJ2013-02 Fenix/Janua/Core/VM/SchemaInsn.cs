using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Janua.Core.VM
{
    /// <summary>
    /// Execution delegate type for vm commands.
    /// </summary>
    /// <param name="parameters">the list of parameters</param>
    /// <param name="context">the virtual machine context</param>
    /// <returns>true if execution should abort, false otherwise</returns>
    public delegate bool CommandDelegate(string[] parameters, object context);

    /// <summary>
    /// Model virtual machine commands.
    /// </summary>
    public class SchemaInsn
    {
        /* Name of command. */
        private string _Name;
        public string Name
        {
            get { return _Name; }
            set { _Name = value; }
        }

        /* Execution delegate. */
        private CommandDelegate _Delegate;
        public CommandDelegate Delegate
        {
            get { return _Delegate;  }
            set { _Delegate = value; }
        }
    }
}
