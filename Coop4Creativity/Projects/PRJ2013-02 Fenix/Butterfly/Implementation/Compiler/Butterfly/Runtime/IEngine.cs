/**
 * Butterfly Runtime v1.0
 * Generation System
 * Author: João Paulo Carreiro
 * Date: MAy 14, 2011
 */
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Butterfly.Runtime
{
    public interface IEngine
    {
        void SetName(string name);
        void SetModuleName(string name);
        void SetClass(string name);

        string GetName();
        string GetModuleName();
        string GetClass();

        void BeforeRun(Context cont);
        void Run();
        void AfterRun();
    }
}
