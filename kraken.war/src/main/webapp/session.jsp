<%--
 Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 Use is subject to license terms.
--%>

<%@page contentType="text/html"%>

<HTML>
<HEAD><TITLE>Cluster - Ha JSP Sample </TITLE></HEAD>
<BODY BGCOLOR="white">
<H1>Cluster - HA JSP Sample </H1>
<B>HttpSession Information:</B>
<UL>
<LI>Served From Server:   <b><%= request.getServerName() %></b></LI>
<LI>Server Port Number:   <b><%= request.getServerPort() %></b></LI>
<LI>Executed From Server: <b><%= java.net.InetAddress.getLocalHost().getHostName() %></b></LI>
<LI>Served From Server instance: <b><%= System.getProperty("com.sun.aas.instanceName") %></b></LI>
<LI>Executed Server IP Address: <b><%= java.net.InetAddress.getLocalHost().getHostAddress() %></b></LI>
<LI>Session ID:    <b><%= session.getId() %></b></LI>
<LI>Session Created:  <%= new java.util.Date(session.getCreationTime())%></LI>
<LI>Last Accessed:    <%= new java.util.Date(session.getLastAccessedTime())%></LI>
<LI>Session will go inactive in  <b><%= session.getMaxInactiveInterval() %> seconds</b></LI>
</UL>
<BR>
<B>Headers</B>
<% 

	java.util.Enumeration headNames = request.getHeaderNames();
	if (!headNames.hasMoreElements()) {
	    System.out.println("No parameter entered for this request");
	} else {
    out.println("<UL>");
	while (headNames.hasMoreElements()) {
	    String param = (String) headNames.nextElement();
	    String value = request.getHeader(param).toString();
	    System.out.println(param+"-"+value);
	    out.println("<LI>" + param + " = " + value + "</LI>");
    }
    out.println("</UL>");
	}

    
%>
<HR><BR>
<B>Session</B>
<% 

    java.util.Enumeration attNames = session.getAttributeNames();
    if (!attNames.hasMoreElements()) {
        System.out.println("No parameter entered for this request");
    } else {
        out.println("<UL>");
        while (attNames.hasMoreElements()) {
            String param = (String) attNames.nextElement();
            String value = session.getAttribute(param).toString();
            out.println("<LI>" + param + " = " + value + "</LI>");
        }
        out.println("</UL>");
    }

%>
<BR><BR>
<HR>
<B>Request</B>
<%
/*
if(request.getAttributeNames()!=null){
    java.util.Enumeration valueNames = request.getAttributeNames();
    if (!valueNames.hasMoreElements()) {
        System.out.println("No parameter entered for this request");
    } else {
        out.println("<UL>");
        while (valueNames.hasMoreElements()) {
            String param = (String) valueNames.nextElement();
            String value = session.getAttribute(param).toString();
            out.println("<LI>" + param + " = " + value + "</LI>");
        }
        out.println("</UL>");
    }
}  
*/
%>
</BODY>
</HTML>
