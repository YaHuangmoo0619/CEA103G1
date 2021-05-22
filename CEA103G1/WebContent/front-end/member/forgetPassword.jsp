<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>

	<h2 align="center">忘記密碼</h2>
	<br>
	<form method="post" class="form-signin"
		action="<%=request.getContextPath()%>/member/member.do">
		<input type="email" id="mail" class="form-control"
			placeholder="電子郵件地址" required autofocus autocomplete="off"
			name="mail">
		<input type="hidden" name="action"
			value="forgetPwd">

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<br>
		<button class="btn btn-lg btn-primary btn-block btn-signin"
			type="submit">送出</button>
	</form>

</body>
</html>