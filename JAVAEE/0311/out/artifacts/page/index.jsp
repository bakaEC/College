
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,java.text.*" pageEncoding="utf-8" %>
<html>
  <head>
    <title>网页</title>
  </head>
  <body bgcolor="#fefefe" size="4"/>
    <%
      out.print("sessionid:"+session.getId());
    %>
    <br/>
    <a href="son.jsp">子窗口</a>
  </body>
  </html>

