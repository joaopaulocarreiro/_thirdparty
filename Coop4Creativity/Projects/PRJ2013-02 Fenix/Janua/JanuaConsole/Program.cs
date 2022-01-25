using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Janua.Util.Shell;

namespace JanuaConsole
{
    public class Program
    {
        public static void Main(string[] args)
        {
            Janua.Util.Shell.CmdLine cmd = new CmdLine();
            cmd.Execute();
        }
    }
}
