<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="model.entity.SectionBean"%>
<%@page import="model.dao.SectionDAO"%>
<%@page import="model.entity.EmployeeBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>詳細画面</title>
<link href="css/styleSheet.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<p class ="title">従業員詳細画面</p>
	<%
		EmployeeBean bean = (EmployeeBean) session.getAttribute("EMPLOYEE");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
	%>
	<form action="DetailServlet" method="post">
		<table>
			<tr>
				<th>従業員コード：</th>
				<td><%=bean.getEmployeeNumber()%></td>
			</tr>
			<tr>
				<th>苗字：</th>
				<td><%=bean.getLastName()%></td>
			</tr>
			<tr>
				<th>名前：</th>
				<td><%=bean.getFirstName()%></td>
			</tr>
			<tr>
				<th>苗字（カナ）：</th>
				<td>
					<%
						String phoneticLastName = bean.getPhoneticLastName();
						if (phoneticLastName == null) {
					%>未入力<%
						} else {
					%><%=phoneticLastName%> <%
						}
					%>


				</td>
			</tr>
			<tr>
				<th>名前（カナ）：</th>
				<td>
					<%
						String phoneticFirstName = bean.getPhoneticFirstName();
						if (phoneticFirstName == null) {
					%>未入力<%
						} else {
					%><%=phoneticFirstName%> <%
						}
						%>
				</td>
			</tr>
			<tr>
				<th>性別：</th>
				<td>
					<%
						if (bean.getSex() == 0) {
					%>男性<%
						} else if (bean.getSex() == 1) {
					%>女性<%
						} else {
					%>未入力<%
						}
					%>
				</td>
			</tr>
			<tr>
				<th>生年月日：</th>
				<td>
					<%
						Date bDay = bean.getBirthDay();
						if (bDay != null) {
							String birthDay = sdf.format(bDay);
					%><%=birthDay%> <%
 	} else {
 %>未入力<%
 	}
 %>
				</td>
			</tr>
			<tr>
				<th>部署：</th>
				<td><%=bean.getSectionName()%></td>
			</tr>
			<tr>
				<th>入社日：</th>
				<td>
					<%
						Date hDate = bean.getHireDate();
						if (hDate != null) {
							String hireDate = sdf.format(hDate);
					%><%=hireDate%> <%
 	} else {
 %>未入力<%
 	}
 %>
				</td>
			</tr>
		</table>
		<input type="submit" name="ACTION" value="編集"> <input
			type="submit" name="ACTION" value="削除"
			onClick="return confirm('ID=<%=bean.getEmployeeNumber()%>を削除しますがよろしいですか？')">
		<input type="hidden" name="EMPLOYEE_CODE"
			value="<%=bean.getEmployeeNumber()%>">
	</form>
	<form action="ListServlet" method="get">
		<input type="submit" name="ACTION" value="一覧画面へ">
	</form>
</body>
</html>