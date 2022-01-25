using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Janua.Core.VM
{
    public class Function
    {
        /* Name of function. */
        private string _Name;
        public string Name
        {
            get { return _Name; }
            set { _Name = value; }
        }

        /* Sequential list of instructions to execute. */
        private List<Insn> _Body;
        public List<Insn> Body
        {
            get { return _Body; }
            set { _Body = value; }
        }

        /// <summary>
        /// Method constructor.
        /// Initialize method object.
        /// </summary>
        public Function()
        {
            Body = new List<Insn>();
        }

        /// <summary>
        /// Add a new instruction to the end of this method.
        /// </summary>
        /// <param name="cmd"></param>
        public void Add(Insn cmd)
        {
            /* check if command object is valid. */
            if (null != cmd)
            {
                Body.Add(cmd);
            }
        }
    }
}
