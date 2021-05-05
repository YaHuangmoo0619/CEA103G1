<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=utf-8"%>
<html>
<head>
<title>使用者登入</title>
</head>
<body bgcolor="#e3e3e3">
	<center>
		<form action="check.jsp" method="post">
			<table>
				<caption>使用者登入</caption>
				<tr>
					<td>使用者名稱:</td>
					<td><input type="text" name="username" size="20" /></td>
				</tr>
				<tr>
					<td>密碼:</td>
					<td><input type="text" name="pwd" size="20" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="登入" />
					<td><input type="reset" value="重置" />
			</table>
		</form>
		如果您還沒有註冊，請單擊<a href="register.jsp">這裡</a>註冊！
</body>
</center>
</html>