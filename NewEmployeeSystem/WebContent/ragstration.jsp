<%@page import="model.entity.SectionBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>新規登録</title>

<!-- script src="js/jquery-3.0.0.min.js" type="text/javascript"></script-->
<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
<!-- validation -->
<link href="css/styleSheet.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/validationEngine.jquery.css"
	type="text/css" />
<script src="js/jquery.validationEngine.js" type="text/javascript"></script>
<script src="js/languages/jquery.validationEngine-ja.js"
	type="text/javascript" charset="UTF-8"></script>

</head>
<body>
	<form action="RagstrationServlet" method="post" id="formID">
		<p class="title">従業員一覧</p>
		<table>
			<tr>
				<th>従業員コード：</th>
				<td><input type="text" name="EMPLOYEE_NUMBER"
					class="validate[required]"></td>
			</tr>
			<tr>
				<th>苗字：</th>
				<td><input type="text" name="LAST_NAME"
					class="validate[required]"></td>
			</tr>
			<tr>
				<th>名前：</th>
				<td><input type="text" name="FIRST_NAME"
					class="validate[required]"></td>
			</tr>
			<tr>
				<th>苗字（カナ）：</th>
				<td><input type="text" name="PHONETIC_LAST_NAME"
					class="validate[custom[katakana]]"></td>
			</tr>
			<tr>
				<th>名前（カナ）：</th>
				<td><input type="text" name="PHONETIC_FIRST_NAME"
					class="validate[custom[katakana]]"></td>
			</tr>
			<tr>
				<th>性別：</th>
				<td><input type="radio" name="SEX" value="0">男 <input
					type="radio" name="SEX" value="1">女</td>
			</tr>
			<tr>
				<th>生年月日：</th>
				<td><input type="date" name="BIRTH_DAY" placeholder="yyyy-mm-dd"></td>
			</tr>
			<tr>
				<th>所属：</th>
				<td><select name="SECTION_CODE">
						<%
							List<SectionBean> list = (List<SectionBean>) request
									.getAttribute("SECTION_LIST");
							if (list.size() != 0) {
								for (SectionBean bean : list) {
						%>
						<option value="<%=bean.getSectionCode()%>"><%=bean.getSectionName()%></option>
						<%
							}
							}
						%>
				</select></td>
			</tr>
			<tr>
				<th>入社日：</th>
				<td><input type="date" name="HIRE_DATE" placeholder="yyyy-mm-dd" class="validate[required]"></td>
			</tr>
		</table>
		<input type="submit" name="EMPLOYEE_DATA" value="登録"><input
			type="reset" value="クリア"
			onClick="return confirm('リセットボタンがクリックされました。本当に入力内容を削除してもよろしいですか？')">
		<br>
	</form>
	<form action="ListServlet" method="get">
		<input type="submit" name="ACTION" value="一覧画面へ戻る">
	</form>
	<script src="js/javascript.js" type="text/javascript"></script>
</body>
</html>