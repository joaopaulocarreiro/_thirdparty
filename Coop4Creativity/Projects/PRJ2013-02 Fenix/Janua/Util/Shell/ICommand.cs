using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Janua.Util.Shell
{
    public interface ICommand
    {
        /// <summary>
        /// Run the shell command.
        /// </summary>
        /// <param name="parameters">the parameters as typed by user</param>
        /// <param name="context">the context object</param>
        /// <returns>true if shell should exit, false otherwise</returns>
        bool Run(string[] parameters, object context);
    }
}
