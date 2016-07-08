<%@page import="Model.EmployeeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; Widows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>従業員一覧画面</title>
</head>
<body>
	<form action="MenuServlet" method="POST">

	<h2 align="center">従業員一覧</h2>
	<br>
		<table border="solid" align="center">
			<tr>
				<td>削除</td>
				<td>従業員コード</td>
				<td>氏名</td>
				<td>氏名（フリガナ）</td>
				<td>性別</td>
				<td>生年月日</td>
				<td>所属部署</td>
				<td>入社日</td>
			</tr>
			<%
				ArrayList<EmployeeBean> list = (ArrayList<EmployeeBean>) request
						.getAttribute("LIST");
				if (list.size() != 0) {
					for (EmployeeBean bean : list) {
			%>
			<tr>
				<td><input type="checkbox" name="DELETE" value="<%=bean.getCode()%>"></td>
				<td><%=bean.getCode()%></td>
				<td><%=bean.getFName()%>&nbsp;<%=bean.getLName()%></td>
				<td><%=bean.getFKanaName()%>&nbsp;<%=bean.getLKanaName()%></td>
				<td><%=bean.getSexName()%></td>
				<td><%=bean.getBirthDay()%></td>
				<td><%=bean.getSectionName()%></td>
				<td><%=bean.getEmpDate()%></td>
			</tr>

			<%
				}
				}
			%>
		</table>
		<br>
		<div align="center">
			<input type="submit" name="ACTION" value="削除">
			 <input type="submit" name="ACTION" value="メニュー画面へ">
		</div>
	</form>
</body>
</html>