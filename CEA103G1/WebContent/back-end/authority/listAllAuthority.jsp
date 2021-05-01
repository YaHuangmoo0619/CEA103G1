<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.authority.model.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<title>所有網站管理員權限列表 - listAllAuthority</title>
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

table{
	width: 600px;
	margin: 30px auto;
	border: 1px solid #4e5452;
}
th, td{
	text-align: center;
	border: 1px solid #4e5452;
	padding: 10px 15px;
}
td.function{
	text-align: justify;	
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
			<h2>所有公告列表 - listAllAuthority.jsp&nbsp;<a class="content" href="<%=request.getContextPath()%>/back-end/authority/select_page.jsp">回首頁</a></h2>
			<hr>
			<h3>資料列表:</h3>
			<table>
				<tr>
					<th style="width:150px">網站管理員姓名</th>
					<th style="width:400px">網站管理權限</th>
				</tr>
				<jsp:useBean id="employeeSvc" scope="page" class="com.employee.model.EmployeeService"/>
				<jsp:useBean id="functionSvc" scope="page" class="com.function.model.FunctionService"/>
				<jsp:useBean id="authoritySvc" scope="page" class="com.authority.model.AuthorityService"/>
				<c:forEach var="employeeVO" items="${employeeSvc.all}">
					<tr>
						<c:if test="${employeeVO.emp_no != 90001}">
						<td>${employeeVO.name}</td>
						<td class="function">
						<c:forEach var="functionVO" items="${functionSvc.all}" varStatus="nextLine">
							<input type="radio" ${authoritySvc.getOneAuthority(employeeVO.emp_no,functionVO.fx_no).fx_no == functionVO.fx_no ? 'checked':''}/>${functionVO.fx_name}
							${nextLine.count%3 == 0 ? '<br>':''}
						</c:forEach>
						</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
</body>
</html>