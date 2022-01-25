using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;
using Butterfly.Error;

namespace Butterfly.Runtime
{
    public abstract class RunUnit
    {
        /* current context. */
        protected Context CurrentContext = default(Context);

        /// <summary>
        /// Get the full name of the compiler construct.
        /// </summary>
        /// <returns>the full name of the construct</returns>
        public string GetFullName()
        {
            return this.GetType().FullName;
        }

        /// <summary>
        /// Execute this methos before running the construct.
        /// </summary>
        /// <param name="cont"></param>
        public void BeforeRun(Context cont)
        {
            CurrentContext = cont;
        }

        /// <summary>
        /// Mrthod to do the actual work.
        /// </summary>
        public abstract void Run();

        /// <summary>
        /// Output a high verbose message.
        /// </summary>
        /// <param name="msg"></param>
        /// <param name="verboseLevel"></param>
        public void VerboseHigh(object msg)
        {
            CurrentContext.Verbose(msg, OptionsGlobal.VerboseLevel.High);
        }

        /// <summary>
        /// Output a high verbose message.
        /// </summary>
        /// <param name="msg"></param>
        /// <param name="verboseLevel"></param>
        public void VerboseMedium(object msg)
        {
            CurrentContext.Verbose(msg, OptionsGlobal.VerboseLevel.Medium);
        }

        /// <summary>
        /// Output a high verbose message.
        /// </summary>
        /// <param name="msg"></param>
        /// <param name="verboseLevel"></param>
        public void VerboseLow(object msg)
        {
            CurrentContext.Verbose(msg, OptionsGlobal.VerboseLevel.Low);
        }
    }
}
