<%--
  Created by IntelliJ IDEA.
  User: gordo
  Date: 30.07.2019
  Time: 2:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="classes.Mail" %>
<html>
<head>
    <title>TestView</title>
</head>
<body>
<%-- Examples of Include Others files
 Preprocess Directive '@' for access variables 'i' --%>
<%--<%@ include file="example.jsp" %>--%>
<%--<%= i %>--%>
<%-- Must to use jsp:include --%>
<%--<jsp:include page="example.jsp"/>--%>
<%--<jsp:include page="/MailClient"/>--%>

<jsp:text>Some jsp text</jsp:text>

<%--<jsp:forward page="/MailClient"/>--%>
<%--Scope Beans--%>
<jsp:useBean id="mail" class="classes.Mail" scope="request"/>
<jsp:useBean id="mailSession" class="classes.Mail" scope="session"/>
<%--page scope - default attribute--%>
<jsp:useBean id="mailPage" class="classes.Mail" scope="page"/>
<jsp:useBean id="mailApplication" class="classes.Mail" scope="application"/>

<%--Request scope--%>
<%--<% request.setAttribute("Mail", mail); %>--%>
<%--Session scope--%>
<%--<% request.getSession().setAttribute("Mail", mail); %>--%>
<%--Application scope--%>
<%--<% request.getServletContext().setAttribute("Mail", mail); %>--%>
<%--<% RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/TestView.jsp"); %>--%>


<%--<% ((Mail) request.getAttribute("Mail")).getPassword();%>--%>
<% Mail secondMail = new Mail(); %>
<%--<jsp:useBean id="mail" class="classes.JSPHelper" type="java.lang.Runnable"/>--%>
<%--<% Runnable runnable = (Runnable) new JSPHelper();%>--%>
<% String status = "Max"; %>
<jsp:setProperty name="mail" property="status" value="<%=status%>"/>
<% secondMail.setStatus("status");%>
<jsp:getProperty name="mail" property="status"/>
<% secondMail.getStatus();%>

<jsp:useBean id="Mail" class="classes.Mail" scope="request"/>
<jsp:useBean id="list" scope="request" type="java.util.List"/>
<jsp:setProperty name="Mail" property="status" value="send"/>
<br>
<%-- RUN Error: value='Mike' are not Array value --%>
<%-- <jsp:setProperty name="Mail" property="sender" value="Mike"/><br>--%>
<jsp:getProperty name="Mail" property="status"/>
<br>
<jsp:getProperty name="Mail" property="receiver"/>
<br>
<%-- Expression language --%>
${Mail.getIndexField("sender",0) }<br>
${Mail.getIndexField("receiver",1)}<br>
${Mail.getIndexField("password",2) }<br>
${Mail.getIndexField("subjectMessage",2)}<br>
${Mail.getIndexField("bodyMessage",0)}<br>
${Mail.getIndexField("wrongField",0)} <br>
${Mail["subjectMessage"]} <br>
${Mail["status"]}<br>
${list[0]}<br>
${requestScope.Mail.sender} <br>
${sessionScope.Mail.sender}<br>
${applicationScope.Mail.sender}<br>

</body>
</html>
