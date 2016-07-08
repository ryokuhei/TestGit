<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>登録失敗画面</title>
</head>
<body>
	<form action="MenuServlet" method="POST">

		<h2 align="center">登録に失敗しました</h2>
		<br>
		<%
			List<String> mes = (List<String>) request.getAttribute("MESSAGE");
			for (int i = 0; i < mes.size(); i++) {
		%>

		<h4 align="center"><%=mes.get(i)%></h4>
		<%
			}
		%>

		<br> <br>
		<div align="center">
			<input type="submit" value="メニュー画面へ" name="ACTION">
		</div>
	</form>
</body>
</html>