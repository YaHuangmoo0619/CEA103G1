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

	<h2 align="center">�ѰO�K�X</h2>
	<br>
	<form method="post" class="form-signin"
		action="<%=request.getContextPath()%>/member/member.do">
		<input type="email" id="mail" class="form-control"
			placeholder="�q�l�l��a�}" required autofocus autocomplete="off"
			name="mail">
		<input type="hidden" name="action"
			value="forgetPwd">

		<%-- ���~��C --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">�Эץ��H�U���~:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<br>
		<button class="btn btn-lg btn-primary btn-block btn-signin"
			type="submit">�e�X</button>
	</form>

</body>
</html>