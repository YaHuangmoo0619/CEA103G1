<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>���U����</title>
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
			alert("�ϥΪ̦W�٤��ର��");
			return false;
		}
		if (form.pwd.value != form.pwd2.value) {
			alert("�⦸��J���K�X���P�I");
			return false;
		} else if (form.pwd.value == "") {
			alert("�ϥΪ̱K�X���ର�šI");
			return false;
		} else
			return true;
	}
</script>
</head>
<body>
	<center>
		<body bgcolor="#e3e3e3">
			<h2>�ϥΪ̵��U</h2>
			<form action="check2.jsp" method="post"
				onSubmit="return isValid(this);">
				<table>
					<tr>
						<td>�ϥΪ̦W��:</td>
						<td><input type="text" name="username" size="20" /></td>
					</tr>
					<tr>
						<td>��J�K�X:</td>
						<td><input type="text" name="pwd" size="20" /></td>
					</tr>
					<tr>
						<td>�A���T�{�K�X:</td>
						<td><input type="text" name="pwd2" size="20" /></td>
					<tr>
					<tr>
						<td><input type="submit" value="���U" />
						<td><input type="reset" value="���m" />
				</table>
			</form>
	</center>
	<br>
</body>
</html>