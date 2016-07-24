<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="model.entity.SectionBean"%>
<%@page import="java.util.List"%>
<%@page import="model.dao.SectionDAO"%>
<%@page import="model.entity.EmployeeBean"%>
<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<!-- jQuery -->
<!-- script src="js/jquery-3.0.0.min.js" type="text/javascript"></script-->
<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"
	type="text/javascript"></script>
<!-- jQuery UI -->
<link type="text/css"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/start/jquery-ui.css"
	rel="stylesheet">

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>

<!-- validation -->
<link rel="stylesheet" href="css/validationEngine.jquery.css"
	type="text/css" />
<script src="js/jquery.validationEngine.js" type="text/javascript"></script>
<script src="js/languages/jquery.validationEngine-ja.js"
	type="text/javascript" charset="UTF-8"></script>

<title>編集画面</title>
<link href="css/styleSheet.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<p class="title">従業員を編集する画面です</p>
	<form action="DetailServlet" method="post" id="formID">
		<%
			EmployeeBean bean = (EmployeeBean) session.getAttribute("EMPLOYEE");
			List<SectionBean> sectionList = (List<SectionBean>) request
					.getAttribute("SECTION_LIST");
		%>
		<table>
			<tr>
				<th>従業員コード：</th>
				<td><input type="text" name="EMPLOYEE_NUMBER"
					value="<%=bean.getEmployeeNumber()%>" class="validate[required]"></td>
			</tr>
			<tr>
				<th>苗字：</th>
				<td><input type="text" name="LAST_NAME"
					value="<%=bean.getLastName()%>" class="validate[required]"></td>
			</tr>
			<tr>
				<th>名前：</th>
				<td><input type="text" name="FIRST_NAME"
					value="<%=bean.getFirstName()%>" class="validate[required]"></td>
			</tr>
			<tr>
				<th>苗字（カナ）：</th>
				<%
						String phoneticLastName = bean.getPhoneticLastName();
						if (phoneticLastName == null) {
							phoneticLastName = "";
						}%>
				<td><input type="text" name="PHONETIC_LAST_NAME"
					value="<%= phoneticLastName%>" class="validate[custom[katakana]]">
			</tr>
			<tr>
				<th>名前（カナ）：</th>
				<td>
					<%
						String phoneticFirstName = bean.getPhoneticFirstName();
						if (phoneticFirstName == null) {
							phoneticFirstName = "";
						}%> <input type="text" name="PHONETIC_FIRST_NAME"
					value="<%=phoneticFirstName%>" class="validate[custom[katakana]]">
				</td>
			</tr>
			<tr>
				<th>性別：</th>
				<td>
					<%
						int sex = bean.getSex();
									if (sex == 0) {
					%> <input type="radio" name="SEX" value="0" checked="checked">男性<input
					type="radio" name="SEX" value="1">女性<%
 	} else if (sex == 1) {
 %> <input type="radio" name="SEX" value="0">男性<input
					type="radio" name="SEX" value="1" checked="checked">女性<%
 	} else {
 %> <input type="radio" name="SEX" value="0">男性<input
					type="radio" name="SEX" value="1">女性<%
 	}
 %>
				</td>
			</tr>
			<tr>
				<th>生年月日：</th>
				<td>
					<%Date birthDay = bean.getBirthDay();
				if(birthDay != null) {
				%> <input type="date" name="BIRTH_DAY" value="<%=birthDay%>"
					placeholder="yyyy-mm-dd"> <% } else {%> <input type="date"
					name="BIRTH_DAY" value="" placeholder="yyyy-mm-dd"> <%} %>
				</td>
			</tr>
			<tr>
				<th>部署：</th>
				<td><select name="SECTION_CODE">
						<option value="<%=bean.getSectionCode()%>"><%=bean.getSectionName()%></option>
						<%
							SectionDAO dao = new SectionDAO();
											for (SectionBean sctionBean : sectionList) {
						%>
						<option value="<%=sctionBean.getSectionCode()%>"><%=sctionBean.getSectionName()%></option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
				<th>入社日：</th>
				<td><input type="date" name="HIRE_DATE"
					value="<%=bean.getHireDate()%>" placeholder="yyyy-mm-dd"
					class="validate[required]"></td>
			</tr>
		</table>
		<input type="submit" name="ACTION" value="編集完了"> <input
			type="reset" value="クリア"
			onclick="return confirm('リセットボタンがクリックされました。本当に入力内容を削除してもよろしいですか？')">
		<br> <input type="submit" value="詳細画面へ戻る" name="ACTION">
	</form>
	<script src="js/javascript.js" type="text/javascript"></script>
</body>
</html>