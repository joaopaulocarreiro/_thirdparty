using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Reflection;

namespace Butterfly.Runtime
{
    public class Context
    {
        /* state internal data structures. */
        private SortedDictionary<string, Object> _Vars;
        private SortedDictionary<string, IEngine> _Engines;
        private SortedDictionary<string, CTMethod> _Methods;
        private SortedDictionary<string, Assembly> _Modules;
        private OptionsEngineGlobal _EngineOptions;
        private OptionsGlobal _Options;
        private Stack<string> _CurrentEnginePath;

        /// <summary>
        /// List of engine options.
        /// </summary>
        public OptionsEngineGlobal EngineOptions
        {
            get
            {
                return _EngineOptions;
            }
            set
            {
                _EngineOptions = value;
            }
        }

        /// <summary>
        /// List of engine options.
        /// </summary>
        public OptionsGlobal Options
        {
            get
            {
                return _Options;
            }
            set
            {
                _Options = value;
            }
        }

        /// <summary>
        /// Current engine path.
        /// </summary>
        public Stack<string> CurrentEnginePath
        {
            get
            {
                return _CurrentEnginePath;
            }
            set
            {
                _CurrentEnginePath = value;
            }
        }

        /// <summary>
        /// Create a new context.
        /// </summary>
        public Context()
        {
            _Vars = new SortedDictionary<string, Object>();
            _Engines = new SortedDictionary<string, IEngine>();
            _Methods = new SortedDictionary<string, CTMethod>();
            _Modules = new SortedDictionary<string, Assembly>();
            _EngineOptions = new OptionsEngineGlobal();
            _Options = new OptionsGlobal();
            _CurrentEnginePath = new Stack<string>();
        }

        /// <summary>
        /// Add a variable value to the context.
        /// </summary>
        /// <param name="name"></param>
        /// <param name="val"></param>
        public void AddVar(string name, Object val)
        {
            /* standard parameter checks. */
            if (null == val) return;
            if (string.IsNullOrEmpty(name) || string.IsNullOrWhiteSpace(name)) return;

            /* add variable to state. */
            _Vars.Add(name, val);
        }

        /// <summary>
        /// Get a variable value based on its name.
        /// </summary>
        /// <param name="name">name for variable</param>
        /// <param name="value">output parameter, returned value</param>
        /// <returns>true if variable exists, false otherwise</returns>
        public bool GetVar(string name, out Object value)
        {
            return _Vars.TryGetValue(name, out value);
        }

        /// <summary>
        /// Add new engine to compiler.
        /// </summary>
        /// <param name="name">name for engine</param>
        /// <param name="val">value of class</param>
        public void AddEngine(string name, IEngine val)
        {
            /* standard checks for parameters. */
            if (string.IsNullOrEmpty(name) || string.IsNullOrWhiteSpace(name)) return;
            if (null == val) return;

            /* add engine if it does not exist. */
            if (!_Engines.Keys.Contains(name))
            {
                _Engines.Add(name, val);
            }
        }

        /// <summary>
        /// Get a engine value based on its name.
        /// </summary>
        /// <param name="name">name for engine</param>
        /// <param name="val">returned object</param>
        /// <returns>true if engine exists, false otherwise</returns>
        public bool GetEngine(string name, out IEngine val)
        {
            return _Engines.TryGetValue(name, out val);
        }

        /// <summary>
        /// Add new method to context.
        /// </summary>
        /// <param name="name">name for method</param>
        /// <param name="val">value of method</param>
        public void AddMethod(string name, CTMethod val)
        {
            /* standard checks for parameters. */
            if (string.IsNullOrEmpty(name) || string.IsNullOrWhiteSpace(name)) return;
            if (null == val) return;

            /* add engine if it does not exist. */
            if (!_Methods.Keys.Contains(name))
            {
                _Methods.Add(name, val);
            }
        }

        /// <summary>
        /// Get a method value based on its name.
        /// </summary>
        /// <param name="name">name for method</param>
        /// <param name="val">returned object</param>
        /// <returns>true if method exists, false otherwise</returns>
        public bool GetMethod(string name, out CTMethod val)
        {
            return _Methods.TryGetValue(name, out val);
        }

        /// <summary>
        /// Add new module to context.
        /// </summary>
        /// <param name="name">name for module</param>
        /// <param name="val">value of module</param>
        public void AddModule(string name, Assembly val)
        {
            /* standard checks for parameters. */
            if (string.IsNullOrEmpty(name) || string.IsNullOrWhiteSpace(name)) return;
            if (null == val) return;

            /* add engine if it does not exist. */
            if (!_Modules.Keys.Contains(name))
            {
                _Modules.Add(name, val);
            }
        }

        /// <summary>
        /// Get a method value based on its name.
        /// </summary>
        /// <param name="name">name for method</param>
        /// <param name="val">returned object</param>
        /// <returns>true if method exists, false otherwise</returns>
        public bool GetModule(string name, out Assembly val)
        {
            return _Modules.TryGetValue(name, out val);
        }

        public void PushEngineLocation(string engineName)
        {
            _CurrentEnginePath.Push(engineName);
        }

        public string PopEngineLocation()
        {
            return _CurrentEnginePath.Pop();
        }

        public string EngineLocation()
        {
            string output = string.Empty;
            int len = _CurrentEnginePath.Count;
            int i = 0;

            foreach (string loc in _CurrentEnginePath)
            {
                output += loc;
                if (i + 1 < len)
                {
                    output += ".";
                }
                i++;
            }

            return output;
        }

        /// <summary>
        /// Output a verbose message based on a certain level.
        /// </summary>
        /// <param name="msg"></param>
        /// <param name="verboseLevel"></param>
        public void Verbose(object msg, OptionsGlobal.VerboseLevel verboseLevel)
        {
            /* if verbose level is none, than nothing is outputed. */
            if (Options.Verbose == OptionsGlobal.VerboseLevel.None) return;

            /* if verbose level is low, then only messages with low level are outputed. */
            if (verboseLevel <= Options.Verbose)
            {
                System.Console.WriteLine(msg.ToString());
            }
        }
    }
}
