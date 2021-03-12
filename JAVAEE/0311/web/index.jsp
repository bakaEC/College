
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,java.text.*" pageEncoding="utf-8" %>

<html>
  <head>
    <title>网页</title>
  </head>
  <body bgcolor="#696969" size="4"/>

  <%!
    int x=0;
  %>
  <form>
    <input type="text" name="inputnum" >
    <input type="submit" name="submit" value="提交">
  </form>
  <%


    SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
    String strtime=format.format(new Date());
    x++;
  %>

  <%
    if (request.getParameter("submit")!=null) {
      String str = request.getParameter("inputnum");

  %>
  <p>您输入的是<%=str%></p>
  <%}%>

  当前时间<%=strtime%>,你是第<%=x%>个访问的人
  </html>

