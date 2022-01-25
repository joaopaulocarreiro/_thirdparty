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
using System.Xml.Linq;
using Butterfly.Error;

namespace LightForms.IR
{
    public class AppInfo
    {
        /// <summary>
        /// _Name for application.
        /// </summary>
        private string _Name = default(string);
        public string Name
        {
            get { return this._Name; }
            set { this._Name = value; }
        }

        /// <summary>
        /// Version for application.
        /// </summary>
        private string _Version = default(string);
        public string Version
        {
            get { return this._Version; }
            set { this._Version = value; }
        }

        /// <summary>
        /// Description.
        /// </summary>
        private string _Description = default(string);
        public string Description
        {
            get { return this._Description; }
            set { this._Description = value; }
        }

        /// <summary>
        /// Date for application.
        /// </summary>
        private string _Date = default(string);
        public string Date
        {
            get { return this._Date; }
            set { this._Date = value; }
        }

        /// <summary>
        /// Author of application.
        /// </summary>
        private string _Author = default(string);
        public string Author
        {
            get { return this._Author; }
            set { this._Author = value; }
        }

        /// <summary>
        /// Icon for application.
        /// </summary>
        private string _Icon = default(string);
        public string Icon
        {
            get { return this._Icon; }
            set { this._Icon = value; }
        }

        /// <summary>
        /// Splash image for application.
        /// </summary>
        private string _Splash = default(string);
        public string Splash
        {
            get { return this._Splash; }
            set { this._Splash = value; }
        }

        /// <summary>
        /// ReadSpecificationFromXMLFile an application info specification an XElement. Build the internal state for this object
        /// based on the values found inside.
        /// </summary>
        /// <param name="elm">the XElement to read</param>
        public void ReadFromXML(XElement elm)
        {
            /* check name of element. */
            if (elm.Name != "app-info")
            {
                throw new InvalidXMLFormatException("element is not named 'app-info'");
            }

            /* get all children elements. */
            IEnumerable<XElement> elms = from item in elm.Elements() select item;

            /* based on name, choose the right property. */
            foreach (XElement specElm in elms)
            {
                switch (specElm.Name.LocalName.ToLower())
                {
                    case "name":
                        Name = specElm.Value;
                        break;
                    case "version":
                        Version = specElm.Value;
                        break;
                    case "description":
                        Description = specElm.Value;
                        break;
                    case "date":
                        Date = specElm.Value;
                        break;
                    case "author":
                        Author = specElm.Value;
                        break;
                    case "icon":
                        Icon = specElm.Value;
                        break;
                    case "splash":
                        Splash = specElm.Value;
                        break;
                    default:
                        break;
                }
            }
        }

        /// <summary>
        /// Write the app info to an XElement.
        /// </summary>
        /// <returns>the XElement datatype</returns>
        public XElement WriteToXML()
        {
            /* start creating the element. */
            XElement elm = new XElement("app-info");
          
            /* add elements only if they are not empty or null. */
            if (!string.IsNullOrEmpty(Name))
            {
                XElement name = new XElement("name");
                name.SetValue(Name); 
                elm.Add(name);
            }

            if (!string.IsNullOrEmpty(Version))
            {
                XElement version = new XElement("version");
                version.SetValue(Version);
                elm.Add(version);
            }

            if (!string.IsNullOrEmpty(Description))
            {
                XElement description = new XElement("description");
                description.SetValue(Description);
                elm.Add(description);
            }

            if (!string.IsNullOrEmpty(Date))
            {
                XElement date = new XElement("date");
                date.SetValue(Date);
                elm.Add(date);
            }

            if (!string.IsNullOrEmpty(Author))
            {

                XElement author = new XElement("author");
                author.SetValue(Author);
                elm.Add(author);
            }

            if (!string.IsNullOrEmpty(Icon))
            {
                XElement icon = new XElement("icon");
                icon.SetValue(Icon);
                elm.Add(icon);
            }

            if (!string.IsNullOrEmpty(Splash))
            {
                XElement splash = new XElement("splash");
                splash.SetValue(Splash);
                elm.Add(splash);
            }

            /* all done. */
            return elm;
        }

        /// <summary>
        /// String representation.
        /// Implemented as an XML output.
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
            return WriteToXML().ToString();
        }
    }
}
