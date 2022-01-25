using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Butterfly.Runtime
{
    public abstract class Engine:RunUnit, IEngine
    {
        protected string Name;
        protected string ModuleName;
        protected string Class;

        public void SetName(string name)
        {
            Name = name;
        }

        public void SetModuleName(string name)
        {
            ModuleName = name;
        }

        public void SetClass(string name)
        {
            Class = name;
        }

        public string GetName()
        {
            return Name;
        }

        public string GetModuleName()
        {
            return ModuleName;
        }

        public string GetClass()
        {
            return Class;
        }

        public void AfterRun()
        {
        }

    }
}
