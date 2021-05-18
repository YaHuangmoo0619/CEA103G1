<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*" %>
<%@ page import="com.employee.model.*" %>
<%@ page import="com.authority.model.*" %>
 
<jsp:useBean id="employeeSvc" scope="page" class="com.employee.model.EmployeeService"/>
<jsp:useBean id="functionSvc" scope="page" class="com.function.model.FunctionService"/>
<%-- <jsp:useBean id="authoritySvc" scope="page" class="com.authority.model.AuthorityService"/> --%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>網站管理員資料 - listOneEmployee.jsp</title>
<%@ include file="/part-of/partOfCampion_backTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_backLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>
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
	font-size: 0.6em;
}
a.content:hover {
	color: #4B7F52;
}

table{
	width: 700px;
	margin: 30px auto;
/* 	border: 1px solid #4e5452; */
}
th, td{
	text-align: center;
/* 	border: 1px solid #4e5452; */
	padding: 10px 15px;
}
td.function{
	text-align: justify;	
}
label.spotlight{
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}

input.change{
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	margin-top: 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}
input.change:hover{
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}
tr {
/* 	border-top: 1px solid #eee; */
	border-bottom: 2px solid #eee;
}
</style>
</head>
<body>
<%@ include file="/part-of/partOfCampion_backTop_body.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
<div class="container">
	<div class="row">
		<div class= "left col-3">
		<%@ include file="/part-of/partOfCampion_backLeft_body.txt"%></div>
		<div class="right col-9">
			<h2>${employeeSvc.getOneEmployee(param.emp_no).getName()}資料&nbsp;<a class="content" href="<%=request.getContextPath()%>/back-end/employee/listAllEmployee.jsp">回網站管理員列表</a></h2>
			<hr>
<%-- 錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th style="width:50px">編號</th>
		<th style="width:150px">姓名</th>
		<th style="width:100px">帳號</th>
		<th style="width:100px">密碼</th>
		<th style="width:100px">EMAIL</th>
		<th style="width:100px">在職狀況</th>	
	</tr>
		<tr>
			<td>${employeeSvc.getOneEmployee(param.emp_no).getEmp_no()}</td>
			<td>${employeeSvc.getOneEmployee(param.emp_no).getName()}</td>
			<td>${employeeSvc.getOneEmployee(param.emp_no).getAcc()}</td>
			<td>${employeeSvc.getOneEmployee(param.emp_no).getPwd()}</td>
			<td>${employeeSvc.getOneEmployee(param.emp_no).getEmail()}</td>
			<td>
			<c:if test="${employeeSvc.getOneEmployee(param.emp_no).getEmp_stat() == 0}">在職</c:if>
			<c:if test="${employeeSvc.getOneEmployee(param.emp_no).getEmp_stat() == 1}">離職</c:if>
			<c:if test="${employeeSvc.getOneEmployee(param.emp_no).getEmp_stat() == 2}">留職</c:if>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改" class="change"> -->
<%-- 			     <input type="hidden" name="emp_no"  value="${employeeVO.emp_no}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="emp_no"  value="${employeeVO.emp_no}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
</table>
		<table>
				<tr>
					<th style="width:400px">網站管理權限</th>
				</tr>
				<c:forEach var="employeeVO" items="${employeeSvc.all}">
					<c:if test="${employeeVO.emp_no == param.emp_no}">
					<tr>
						<td class="function">
						<c:forEach var="functionVO" items="${functionSvc.all}" varStatus="nextLine">
							<input type="checkbox" name="fx_no${functionVO.fx_no}" ${authoritySvc.getOneAuthority(employeeVO.emp_no,functionVO.fx_no).fx_no == functionVO.fx_no ? 'checked':''} value="${functionVO.fx_no}" disabled/>
							<label for="function${nextLine.count}" ${authoritySvc.getOneAuthority(employeeVO.emp_no,functionVO.fx_no).fx_no == functionVO.fx_no ? 'class=spotlight':''}>${functionVO.fx_name}</label>
							${nextLine.count%3 == 0 ? '<br>':''}
						</c:forEach>
						</td>
					</tr>
					</c:if>
				</c:forEach>
			</table>
</div>
</div>
</div>
<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
</body>
</html>