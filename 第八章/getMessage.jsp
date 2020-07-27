<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<HTTP>
	<HEAD>
		<TITLE>Get-HTTP-MESSAGE</TITLE>
	</HEAD>
	<BODY>
		<%
		String message=request.getParameter("message");
		String result=new String(message.getBytes("iso-8859-1"),"gb2312");
		out.println("<H1>Android-message:"+result+"</H>");
		%>
	</BODY>
</HTML>