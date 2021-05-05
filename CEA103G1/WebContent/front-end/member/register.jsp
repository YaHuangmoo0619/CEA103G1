<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>註冊頁面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
<link rel="stylesheet" type="text/css" href="styles.css">
-->
<script language="javascript">
	function isValid(form) {
		if (form.username.value == "") {
			alert("使用者名稱不能為空");
			return false;
		}
		if (form.pwd.value != form.pwd2.value) {
			alert("兩次輸入的密碼不同！");
			return false;
		} else if (form.pwd.value == "") {
			alert("使用者密碼不能為空！");
			return false;
		} else
			return true;
	}
</script>
</head>
<body>
	<center>
		<body bgcolor="#e3e3e3">
			<h2>使用者註冊</h2>
			<form action="check2.jsp" method="post"
				onSubmit="return isValid(this);">
				<table>
					<tr>
						<td>使用者名稱:</td>
						<td><input type="text" name="username" size="20" /></td>
					</tr>
					<tr>
						<td>輸入密碼:</td>
						<td><input type="text" name="pwd" size="20" /></td>
					</tr>
					<tr>
						<td>再次確認密碼:</td>
						<td><input type="text" name="pwd2" size="20" /></td>
					<tr>
					<tr>
						<td><input type="submit" value="註冊" />
						<td><input type="reset" value="重置" />
				</table>
			</form>
	</center>
	<br>
</body>
</html>