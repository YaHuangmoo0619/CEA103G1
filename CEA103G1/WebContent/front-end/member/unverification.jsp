<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>尚未驗證</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/memTen/memTen.do" class="" name="">
		<h2>信箱尚未驗證，請盡速至您的信箱點選驗證連結。</h2>	
		<input type="hidden" name="action" value="sendAuthMail">
		<input type="hidden" name="mem_username" value="${param.mem_username}"> 
		<input type="submit" value="重寄驗證信">
	</form>
</body>
</html>