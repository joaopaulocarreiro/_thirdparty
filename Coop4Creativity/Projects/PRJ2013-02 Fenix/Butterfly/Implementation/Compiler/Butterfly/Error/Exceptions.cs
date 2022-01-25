using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Butterfly.Error
{
    /* Invalid XML Format. */
    [Serializable()]
    public class InvalidXMLFormatException : System.Exception
    {
        public InvalidXMLFormatException() : base() { }
        public InvalidXMLFormatException(string message) : base(message) { }
        public InvalidXMLFormatException(string message, System.Exception inner) : base(message, inner) { }
        protected InvalidXMLFormatException(System.Runtime.Serialization.SerializationInfo info,
            System.Runtime.Serialization.StreamingContext context) { }
    }
}
