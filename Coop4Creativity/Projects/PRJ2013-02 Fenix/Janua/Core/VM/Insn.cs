using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Janua.Core.VM
{
    public class Insn
    {
        /* Name of instruction to execute */
        private string _Name;
        public string Name
        {
            get { return _Name; }
            set { _Name = value; }
        }

        /* List or arguments. */
        private string[] _Arguments;
        public string[] Arguments
        {
            get { return _Arguments; }
            set { _Arguments = value; }
        }
    }
}
