using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;

namespace Butterfly.Runtime
{
    public interface IConstruct
    {
        string GetFullName();
        void BeforeRun(Context cont);
        void Run();
        void ReadFromXML(XElement elm);
        XElement WriteToXML();
    }
}
