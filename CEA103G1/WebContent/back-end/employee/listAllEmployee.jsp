<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*" %>
<%@ page import="com.employee.model.*" %>

<%
	EmployeeService employeeSvc = new EmployeeService();
	List<EmployeeVO> list = employeeSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>網站管理員列表 - listAllAnnouncement.jsp</title>
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
 	cursor: pointer; 
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

<%-- 錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<h3>網站管理員列表&nbsp;
			<a class="content" href="<%=request.getContextPath()%>/back-end/employee/addEmployee.jsp">新增網站管理員</a>&nbsp;
			<a class="content" href="<%=request.getContextPath()%>/back-end/authority/listAllAuthority.jsp">查看管理員權限</a>&nbsp;
			</h3>
			<hr>
<div style="text-align:center;font-weight:555;">
					<div style="width: 50px;display:inline-block;">編號</div>
					<div style="width: 100px;display:inline-block;">姓名</div>
					<div style="width: 100px;display:inline-block;">帳號</div>
					<div style="width: 100px;display:inline-block;">密碼</div>
					<div style="width: 200px;display:inline-block;">EMAIL</div>
					<div style="width: 100px;display:inline-block;"><a href="#focus" class="content">看更新</a><a id="first"></a></div>
				</div>
				<hr>
<!-- <div><a href="#focus" style="text-decoration:none;">查看當筆新增或修改的資料</a><a id="first" style="text-decoration:none;"></a></div> -->
<table>
<!-- 	<tr> -->
<!-- 		<th style="width:50px">編號</th> -->
<!-- 		<th style="width:130px">姓名</th> -->
<!-- 		<th style="width:100px">帳號</th> -->
<!-- 		<th style="width:100px">密碼</th> -->
<!-- 		<th style="width:100px">EMAIL</th> -->
<!-- 		<th style="width:80px">在職狀況</th>	 -->
<!-- 	</tr> -->
	<c:forEach var="employeeVO" items="${list}" >
		<tr ${(employeeVO.emp_no==param.emp_no || employeeVO.emp_no==emp_no) ? 'bgcolor=#eee name=pos' : '' } >
			<c:if test="${employeeVO.emp_no != 90001}">
				<c:if test="${employeeVO.emp_no==param.emp_no || employeeVO.emp_no==emp_no}">
					<td>${employeeVO.emp_no}<a id="focus"></a></td>
				</c:if>
				<c:if test="${employeeVO.emp_no!=param.emp_no && employeeVO.emp_no!=emp_no}">
					<td>${employeeVO.emp_no}</td>
				</c:if>

			<td style="width:100px;font-size:0.9em;padding:10px;">${employeeVO.name}</td>
			<td>${employeeVO.acc}</td>
			<td>${employeeVO.pwd}</td>
			<td>${employeeVO.email}</td>
<%-- 			<c:set var="pwd" value="${employeeVO.pwd}"/> --%>
<%-- 			<td>${fn:substring(pwd, 0, 1)}********</td> --%>
<%-- 			<c:set var="email" value="${employeeVO.email}"/> --%>
<%-- 			<td>${fn:substring(email, 0, 1)}********</td> --%>
			<td>
			<c:if test="${employeeVO.emp_stat == 0}">在職</c:if>
			<c:if test="${employeeVO.emp_stat == 1}">離職</c:if>
			<c:if test="${employeeVO.emp_stat == 2}">留職</c:if>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" class="change">
			     <input type="hidden" name="emp_no"  value="${employeeVO.emp_no}">
			     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="emp_no"  value="${employeeVO.emp_no}"> --%>
<%-- 			     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
			
			</c:if>			
		</tr>
	</c:forEach>
</table>
</div>
</div>
</div>
<script>
// $("tr").click(function(e){
// 	let emp_no = e.currentTarget.children[0].innerText;
<%-- 	window.location.href="<%=request.getContextPath()%>/employee/employee.do?emp_no="+ emp_no + "&action=read"; --%>
// });

$("tr").click(function(e){
	let emp_no = e.currentTarget.children[0].innerText;
	window.location.href="<%=request.getContextPath()%>/back-end/employee/listOneEmployee.jsp?emp_no="+ emp_no;
});

	let backToTop = document.getElementsByClassName("backToTop");
	$(window).scroll(function(e) {
		if ($(window).scrollTop() <= 1) {
			backToTop[1].style.display = "none";
		} else {
			backToTop[1].style.display = "block";
		}
	});
</script>

</body>
</html>