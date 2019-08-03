<%--
  Created by IntelliJ IDEA.
  User: gordo
  Date: 29.07.2019
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%--Import Libs--%>
<%@ page import="static java.lang.System.*" %>
<%@ page import="java.util.Date" %>
<%--Page Directive--%>
<%--<%@ page buffer="8b" autoFlush="true" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>

<%--Import others classes--%>
<%@ page import="classes.JSPHelper" %>

<html>
<head>
    <title>examples JSP</title>
</head>
<body>
<%--initialization fields--%>
<%! int i = 5;%>
<%! private void doJob() {
    out.println("hello");
}%>
<%= "hello world"%>
<%= i + 1 + 2%>

<%= JSPHelper.minux(5, 1)%>

<%--PreInstalled Variables--%>
<%= request.getMethod()%>
<%--<%= response.setStatus(HttpServletResponse.SC_OK)%>--%>
<%= session.getAttribute("one")%>
<%= application.getAttribute("")%>
<%= application.getServerInfo()%>
<%= application.getServletContextName()%>
<%= config.getServletContext()%>


<%= new Date()%>
<%--Screeplet--%>
<%
    class Student {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
%>
<%= new Student().getName()%>
<% if (Math.random() > 5) { %>
<b>hello World</b>
<% } %>
</body>
</html>