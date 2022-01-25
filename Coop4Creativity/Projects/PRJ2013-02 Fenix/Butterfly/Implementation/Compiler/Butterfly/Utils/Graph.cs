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

namespace Butterfly.Utils
{
    /// <summary>
    /// Graph class.
    /// Implement a graph. Nodes are identified with a string.
    /// </summary>
    /// <typeparam name="N">datatype for information stored inside nodes</typeparam>
    /// <typeparam name="E">datatype for information stored in the edges</typeparam>
    public class Graph<N, E>
    {
        /* graph internal data structures. */
        private string _Name;
        private SortedDictionary<string, N> _Nodes;
        private SortedDictionary<string, SortedList<string, E>> _Edges;

        /// <summary>
        /// Create a new graph.
        /// </summary>
        /// <param name="name">name for graph</param>
        public Graph(string name)
        {
            _Name = name;
            _Nodes = new SortedDictionary<string, N>();
            _Edges = new SortedDictionary<string, SortedList<string, E>>();
        }

        /// <summary>
        /// Add a new node to the graph.
        /// </summary>
        /// <param name="id">the id for the node</param>
        /// <param name="value">the value for the node</param>
        public void AddNode(string id, N value)
        {
            if (!string.IsNullOrEmpty(id) && !string.IsNullOrWhiteSpace(id))
            {
                _Nodes.Add(id, value);
            }
        }

        /// <summary>
        /// Add a new edge to the graph.
        /// </summary>
        /// <param name="from">id of from node</param>
        /// <param name="to">id of to node</param>
        /// <param name="value">value to store in the edge</param>
        public void AddEdge(string from, string to, E value)
        {
            /* standard checks. */
            if (string.IsNullOrEmpty(from) || string.IsNullOrWhiteSpace(from)) return;
            if (string.IsNullOrEmpty(to) || string.IsNullOrWhiteSpace(to)) return;

            /* check if nodes exist in the graph. */
            N fromValue = default(N);
            N toValue = default(N);
            if ((_Nodes.TryGetValue(from, out fromValue) && _Nodes.TryGetValue(to, out toValue)))
            {
                SortedList<string, E> adjList = default(SortedList<string, E>);
                if (!_Edges.TryGetValue(from, out adjList))
                {
                    adjList = new SortedList<string, E>();
                }
                adjList.Add(to, value);
            }
        }

        /// <summary>
        /// Generate an XML representation of the graph.
        /// </summary>
        /// <returns></returns>
        public XElement WriteToXML()
        {
            XElement elm = new XElement("graph");
            elm.Add(new XAttribute("name", _Name));

            /* output node info. */
            elm.Add(new XComment(" nodes "));
            foreach (string node in _Nodes.Keys)
            {
                XElement nodeXml = new XElement("node");
                nodeXml.Add(new XAttribute("id", node));
                elm.Add(nodeXml);
            }

            /* output edge info. */
            elm.Add(new XComment(" edges "));
            foreach (string fromId in _Edges.Keys)
            {
                SortedList<string, E> adjLst = _Edges[fromId];
                foreach (string toId in adjLst.Keys)
                {
                    XElement edgeXml = new XElement("edge");
                    edgeXml.Add(new XAttribute("from", fromId));
                    edgeXml.Add(new XAttribute("to", toId));
                    elm.Add(edgeXml);
                }
            }

            /* all done. */
            return elm;
        }

        /// <summary>
        /// Generate a full XML document.
        /// </summary>
        /// <returns>the XML document object</returns>
        public XDocument WriteToXMLDocument()
        {
            /* main xml document. */
            XDocument doc = new XDocument();
            doc.Add(WriteToXML());
            return doc;
        }
    }
}
