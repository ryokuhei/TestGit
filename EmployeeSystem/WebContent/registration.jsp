<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>従業員登録画面</title>
</head>
<body>
	<form action="MenuServlet" method="POST">
		<h2 align="center">登録したい従業員の情報を入力してください</h2>
		<br>
		<div align="left">

			従業員コード：<input type="text" name="CODE" maxlength="4" size="5" required>
			<br> 氏名： <br> &nbsp;&nbsp;苗字<input type="text"
				name="F_NAME" maxlength="16" size="18" required>&nbsp;&nbsp;名前<input
				type="text" name="L_NAME" maxlength="16" size="18" required>
			<br> 氏名(フリガナ)： <br> &nbsp;&nbsp;苗字(フリガナ)<input type="text"
				name="F_KANA_NAME" maxlength="24" size="28">&nbsp;&nbsp;名前(フリガナ)<input
				type="text" name="L_KANA_NAME" maxlength="24" size="28"> <br>

			性別：<select name="SEX">
				<option value="blank"></option>
				<option value="男性">男性</option>
				<option value="女性">女性</option>
			</select> <br> 生年月日： <input type="text" name="B_YEAR" size="5"
				maxlength="4">年 <input type="text" name="B_MONTH" size="5"
				maxlength="2">月 <input type="text" name="B_DATE"
				maxlength="2" size="5">日 <br> 所属部署：<select
				name="SECTION_NAME">
				<option value="bleank"></option>
				<%ArrayList<String> list = (ArrayList<String>)request.getAttribute("SECTION_NAME");
			for(String sectionName : list) { %>
				<option value="<%=sectionName %>"><%=sectionName %></option>
				<% } %>
			</select> <br> 入社日：<input type="text" name="E_YEAR" maxlength="4" size="5">年
			<input type="text" name="E_MONTH" maxlength="2" size="5">月 <input
				type="text" name="E_DATE" maxlength="2" size="5">日
		</div>
		<table align="center">
			<tr>
				<td><input type="submit" name="ACTION" value="登録"> <input
					type="reset" value="クリア"></td>
			</tr>
		</table>
		<br>
		</form>
		<form action="MenuServlet" method="POST">
		<div align="center">
			<input type="submit" value="メニュー画面へ" name="ACTION">
		</div>
	</form>
</body>
</html>