<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>従業員登録失敗</title>
<link href="css/styleSheet.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form action="RagstrationServlet" method="get">
<p class ="title">登録失敗</p>
<%List<String> error = (List<String>)request.getAttribute("ERROR");
if(error.size() != 0) {
	for(String errorMessage: error) {
	%><%=errorMessage %><br>
	<%
	}
}
%>
<input type="submit" value="登録画面へ戻る">
</form>
</body>
</html>