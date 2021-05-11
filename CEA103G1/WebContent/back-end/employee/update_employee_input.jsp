<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*" %>
<%@ page import="java.util.*" %>

<%
	EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");
	EmployeeService employeeSvc = new EmployeeService();
	LinkedList<EmployeeVO> employeeVOLink = (LinkedList<EmployeeVO>)employeeSvc.getAll();
	EmployeeVO employeeLast = employeeVOLink.getLast();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>修改網站管理員資料 - updateEmployee.jsp</title>
<%@ include file="/part-of/partOfCampion_backTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_backLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>
<style>
body {
	background-color: #4e5452;
	color: #4e5452;
}

div.left {
	margin-top: 20px;
}

div.right {
	background-color: #fff;
	margin-top: 40px;
	padding: 50px 50px;
	border-radius: 5px;
}

a.content {
	color: #80c344;
	font-size: 0.6em;
}

a.content:hover {
	color: #4B7F52;
}

input.confirm {
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}

input.confirm:hover {
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}

#confirmTop:hover {
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}

div.forSearch{
	margin: 0 auto;
	width: 70%;
	hieght: 50px;
	position: relative;
}
div.forSearchMore{
	top: 110%;
	left: 15%;
	width: 70%;
	position: absolute;
	background-color: #fff;
	box-shadow: 0 1px 5px 0 #4e5452;
	display: none
}

#mail_cont{
	border-radius:5px;
	background-color:#eee;
	border:none;
	padding:5px 15px;
	width:50%;
}

span{
	 font-size:0.8em;
	 font-weight:444;
	 padding: 7px;
	 background-color: #eee;
	 border-radius:5px;
}
span:hover{
	cursor: pointer;
	background-color: #4e5452;
	color: #eee;
}

label, select, input {
	font-size: 0.8em;
}

table {
	width: 700px;
	margin: 30px auto;
	/* 	border: 1px solid #4e5452; */
}

th, td {
	text-align: left;
	/* 	border: 1px solid #4e5452; */
	padding: 10px 15px;
}

td.function {
	text-align: justify;
}

label.spotlight {
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}

input.change {
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}

input.change:hover {
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}

#focus {
	margin-right: -5px;
}

tr {
/* 	border-top: 1px solid #eee; */
	border-bottom: 2px solid #eee;
}

tr:hover {
 	box-shadow: 0 1px 5px 0 #4e5452 inset; 
/* 	cursor: pointer; */
}
</style>

</head>
<body>
<%@ include file="/part-of/partOfCampion_backTop_body.txt"%>
	<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
	<div class="container">
		<div class="row">
			<div class="left col-3">
				<%@ include file="/part-of/partOfCampion_backLeft_body.txt"%></div>
			<div class="right col-9">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<h3>修改網站管理員&nbsp;
			<a class="content" href="<%=request.getContextPath()%>/back-end/employee/listAllEmployee.jsp">回到網站管理員列表</a>&nbsp;
			</h3>
			<hr>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" name="form1">
<table>
	<tr>
		<td>姓名:</td>
		<td><input type="TEXT" name="name" size="30" placeholder="請輸入姓名"
			 value="<%= (employeeVO == null)? "" : employeeVO.getName()%>" /></td>
	</tr>
	<tr>
		<td>帳號:</td>
		<td>CEA<input type="TEXT" name="acc" size="25"
			 value="<%= (employeeVO == null)? employeeLast.getEmp_no()+1 : employeeVO.getAcc().substring(3)%>" /></td>
	</tr>
	<tr>
		<td>EMAIL:</td>
		<td><input type="TEXT" name="email" size="40" placeholder="請輸入EMAIL"
			 value="<%= (employeeVO == null)? "" : employeeVO.getEmail()%>" /></td>
	</tr>
	<tr>
		<td>在職狀況:</td>
		<td onclick="noChecked(this)">
			<c:if test="${employeeVO.getEmp_stat() == null}">
				<input type="radio" id="state0" name="emp_stat" value="0">
				<label for="state0">在職</label><br>
				<input type="radio" id="state1" name="emp_stat" value="1">
				<label for="state1">離職</label><br>
				<input type="radio" id="state2" name="emp_stat" value="2">
				<label for="state2">留職</label>
			</c:if>
			<c:if test="${employeeVO.getEmp_stat() == 0}">
				<input type="radio" id="state0" name="emp_stat" value="0" checked>
				<label for="state0">在職</label><br>
				<input type="radio" id="state1" name="emp_stat" value="1">
				<label for="state1">離職</label><br>
				<input type="radio" id="state2" name="emp_stat" value="2">
				<label for="state2">留職</label>
			</c:if>
			<c:if test="${employeeVO.getEmp_stat() == 1}">
				<input type="radio" id="state0" name="emp_stat" value="0">
				<label for="state0">在職</label><br>
				<input type="radio" id="state1" name="emp_stat" value="1" checked>
				<label for="state1">離職</label><br>
				<input type="radio" id="state2" name="emp_stat" value="2">
				<label for="state2">留職</label>
			</c:if>
			<c:if test="${employeeVO.getEmp_stat() == 2}">
				<input type="radio" id="state0" name="emp_stat" value="0">
				<label for="state0">在職</label><br>
				<input type="radio" id="state1" name="emp_stat" value="1">
				<label for="state1">離職</label><br>
				<input type="radio" id="state2" name="emp_stat" value="2" checked>
				<label for="state2">留職</label>
			</c:if>			
		</td>			  
	</tr>
	<tr>
		<td>密碼更新:</td>
		<td>
			<c:if test="${code == null}">
		 		<input type="TEXT" name="pwd" value="${employeeVO.pwd}" readOnly>
		 	</c:if>
		 	<c:if test="${code != null}">
		 		<input type="TEXT" name="pwd" value="${code}" readOnly>
		 	</c:if>
		</td>	 
	</tr>
</table>
<br>
<input type="hidden" name="emp_no" value="<%=employeeVO.getEmp_no()%>">
<input type="hidden" name="pwd" value="<%=employeeVO.getPwd()%>">
<input type="hidden" name="action" value="update">
<input type="hidden" name="sendingEmail" value="${sendingEmail==null ? '' : 'sendingEmail'}">
<input type="submit" name="change" value="密碼更新" class="change">
<input type="submit" value="送出修改" class="change"></FORM>
</div>
</div>
</div>
<script>
function noChecked(e){
	document.getElementById("state0").removeAttribute("checked");
	document.getElementById("state1").removeAttribute("checked");
	document.getElementById("state2").removeAttribute("checked");
}
</script>
<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
</body>
</html>