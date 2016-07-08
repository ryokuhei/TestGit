<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<link href="./CSS/menu.css" rel="stylesheet" type="text/css">
<title>メニュー画面</title>
</head>
<body>
	<form action="MenuServlet" method="POST">
		<div align="center">
			<h2>メニュー画面</h2>
			<br>
			<br>
			<input type="submit" name="ACTION" value="一覧表示メニュー"> <br>
			<br> <input type="submit" name="ACTION" value="従業員登録メニュー">
			<br> <br> <input type="submit" name="ACTION" value="ログアウト">
			<br> <br>
		</div>
	</form>
</body>
</html>