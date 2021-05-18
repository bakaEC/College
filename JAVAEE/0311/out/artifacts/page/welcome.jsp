<%--
  User: bakaEC
  Date: 2021-03-18
  Time: 17:03
  github: http://github.com/bakaEC
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<%
    String name = request.getParameter("username");
%>
<h1><%=name%>,欢迎登陆！</h1>
</body>
</html>
