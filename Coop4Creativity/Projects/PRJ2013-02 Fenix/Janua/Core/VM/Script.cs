using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Janua.Core.VM
{
    public class Script
    {
        /* Name of program/script. */
        private string _Name;
        public string Name
        {
            get { return _Name; }
            set { _Name = value; }
        }

        /* Entry point for script. */
        private string _EntryPoint;
        public string EntryPoint
        {
            get { return _EntryPoint; }
            set { _EntryPoint = value; }
        }
       
        /* List of declared funcitons. */
        private SortedDictionary<string, Function> _Functions;
        public SortedDictionary<string, Function> Functions
        {
            get { return _Functions; }
            set { _Functions = value; }
        }
    }
}
