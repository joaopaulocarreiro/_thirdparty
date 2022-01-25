<%@ Page Title="Start" Language="C#" MasterPageFile="~/butterfly.master" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="Butterfly_Web._Default" %>

<asp:Content ID="HeaderContent" runat="server" ContentPlaceHolderID="HeadContent"></asp:Content>

<asp:Content ID="LeftNavigation" runat="server" ContentPlaceHolderID="LeftNavigationContent">
  <asp:Menu ID="NavigationMenu" runat="server" CssClass="menu" EnableViewState="false" IncludeStyleBlock="false" Orientation="Vertical">
    <Items>
      <asp:MenuItem NavigateUrl="~/Default.aspx" Text="Home"/>
      <asp:MenuItem NavigateUrl="~/About.aspx" Text="About"/>
    </Items>
  </asp:Menu>
</asp:Content>

<asp:Content ID="BodyContent" runat="server" ContentPlaceHolderID="MainContent">
    <h2>
        Welcome to the Butterfly Compiler System
    </h2>

    <p>
      The Butterfly Compiler System is a set of tools to help you build compilers. 
    </p>
</asp:Content>
