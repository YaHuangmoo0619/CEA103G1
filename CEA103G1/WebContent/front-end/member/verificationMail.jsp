<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>

<!-- Required source start -->
<!-- jquery 這行有需要的人在自己的頁面加 -->
<%-- 	<script src="<%=request.getContextPath()%>/template_front-end/js/jquery-1.12.1.min.js"></script> --%>
<!-- Required source end -->
</head>
<body>

	<h2 align="center">帳號驗證</h2>
	<br>
	<c:if test="${not empty errorMsgs}">
		<c:forEach var="message" items="${errorMsgs}">
			<h4 align="center" style="color: red">${message}</h4>
		</c:forEach>
		<script>
// 					if(${not empty errorMsgs}){
// 						console.log("AAAAAA");
// 						$('#successVerify').hide();
// 					} else {
// 						console.log("FFFFFFFFF");
// 						$('#failureVerify').show();	
// 					}
					
// 					console.log($('#successVerify'));
// 					console.log($('#failureVerify'));
								
				</script>
	</c:if>
	<c:if test="${empty errorMsgs}">
		<h4 align="center">成功</h4>
	</c:if>

	<br>
	<button id="successVerify"
		class="btn btn-lg btn-primary btn-block btn-signin"
		onclick="location='<%=request.getContextPath()%>/front-end/member/login.jsp'">返回登入頁
	</button>
	<button id="failureVerify"
		class="btn btn-lg btn-primary btn-block btn-signin"
		onclick="location='<%=request.getContextPath()%>/front-end/member/unverification.jsp?mail=${param.mail}'">重寄驗證信
	</button>


	<script>
					if(${empty errorMsgs}){
						$('#failureVerify').hide();	
					}else if(${not empty errorMsgs}) {
						$('#successVerify').hide();
					}
			</script>



</body>
</html>