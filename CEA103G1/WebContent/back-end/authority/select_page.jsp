<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*" %>
<%@ page import="com.function.model.*" %>
<!DOCTYPE html>
<html lang="zh-tw">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>Authority: Home</title>
<%@ include file="/partOfCampion_backTop_css.txt"%>
<%@ include file="/partOfCampion_backLeft_css.txt"%>
<style>
body{
	background-color: #4e5452;
	color: #4e5452;
}
div.left{
	margin-top: 20px;
}
div.right{
	background-color: #fff;
	margin-top: 40px;
	padding: 50px 50px;
	border-radius: 5px;
}
a.content{
	color: #80c344;
}
a.content:hover {
	color: #4B7F52;
}

form{
	margin: 0px auto;
}
</style>
</head>
<body>
<%@ include file="/partOfCampion_backTop_body.txt"%>
<div class="container">
	<div class="row">
		<div class= "left col-3">
		<%@ include file="/partOfCampion_backLeft_body.txt"%></div>
		<div class="right col-9">
			<h2>This is the Home page for Authority</h2>
			<hr>
			<h3>資料查詢:</h3>
			<%-- 錯誤列表 map --%>
			${errorMsgs}
			<ul>
				<li>
					<a class="content" href="<%=request.getContextPath() %>/authority/listAllAuthority.jsp">List</a><br>
				</li>
				<jsp:useBean id="employeeSvc" scope="page" class="com.employee.model.EmployeeService"/>
				<li>
				<form method="post" action="<%=request.getContextPath() %>/authority/authority.do">
					<b>選擇網站管理員姓名:</b>
					<select size="1" name="emp_no">
						<option value="0">--請選擇--
						<c:forEach var="employeeVO" items="${employeeSvc.all}">
							<c:if test="${employeeVO.emp_no != 90001}">
								<option value="${employeeVO.name}">${employeeVO.name}
							</c:if>
						</c:forEach>
					</select>
					<input type="submit" value="送出">${errorMsgs.name}
					<input type="hidden" name="action" value="">	
				</form>
				</li>
				<jsp:useBean id="functionSvc" scope="page" class="com.function.model.FunctionService"/>
				<li>
				<form method="post" action="<%=request.getContextPath() %>/authority/authority.do">
					<b>選擇權限名稱:</b>
					<select size="1" name="fx_no">
						<option value="0">--請選擇--
						<c:forEach var="functionVO" items="${functionSvc.all}">
							<option value="${functionVO.fx_no}">${functionVO.fx_name}
						</c:forEach>
					</select>
					<input type="submit" value="送出">${errorMsgs.fx_no}
					<input type="hidden" name="action" value="">	
				</form>
				</li>
			</ul>
			<h3>網站管理員權限管理:</h3>
			<ul>
				<li>
					<a class="content" href="<%=request.getContextPath()%>/authority/authority.do">add</a>
				</li>
			</ul>
		</div>
	</div>
</div>
</body>
</html>