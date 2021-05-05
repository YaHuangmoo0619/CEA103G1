<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.service_mail.model.*" %>

<% Service_mailVO service_mailVO = (Service_mailVO)request.getAttribute("service_mailVO"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>修改客服信</title>
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
}
a.content:hover {
	color: #4B7F52;
}

table{
	width: 700px;
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
label.spotlight{
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}
form{
	text-align: center;
}
textarea{
	resize: none;
}
input.confirm{
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
	margin: 0px 10px;
}
input.confirm:hover{
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
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
			<h2>修改客服信&nbsp;<a class="content" href="<%=request.getContextPath()%>/back-end/service_mail/select_page.jsp">回首頁</a></h2>
			<hr>
			<h5 style="color:#80c344;">${errorMsgs.notFound[0]}${errorMsgs.exception[0]}</h5>
			<h3>資料列表:</h3>
			<form method="post" action="<%=request.getContextPath()%>/service_mail/service_mail.do">
			<jsp:useBean id="service_mailSvc" class="com.service_mail.model.Service_mailService"/>
			<jsp:useBean id="employeeSvc" class="com.employee.model.EmployeeService"/>
			<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
			<table>
				<tr>
					<td>
						<label for="emp_no">寄件人</label>
						<br><h5 style="color:#80c344;">${errorMsgs.emp_no[0]}</h5>
					</td>
					<td>
						<select size="1" name="emp_no" id="emp_no">
						<option value="99">--請選擇--</option>
						<c:forEach var="employeeVO" items="${employeeSvc.all}">
							<c:if test="${employeeVO.emp_no != 90001}">
							<option value="${employeeVO.emp_no}" ${employeeVO.emp_no == service_mailVO.emp_no || employeeVO.emp_no == param.emp_no? 'selected':''}>${employeeVO.emp_no}${employeeVO.name}</option>
							</c:if>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="mbr_no">收件人</label>
						<br><h5 style="color:#80c344;">${errorMsgs.mbr_no[0]}</h5>
					</td>
					<td>
						<select size="1" name="mbr_no" id="mbr_no">
						<option value="99">--請選擇--</option>
						<c:forEach var="memberVO" items="${memberSvc.all}">
							<option value="${memberVO.mbr_no}" ${memberVO.mbr_no == service_mailVO.mbr_no || memberVO.mbr_no == param.mbr_no? 'selected':''}>${memberVO.mbr_no}${memberVO.name}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="mail_cont">內容</label>
						<br><h5 style="color:#80c344;">${errorMsgs.mail_cont[0]}</h5>	
					</td>
					<td>
						<textarea name="mail_cont" rows="10" cols="45" class="mail_cont"><c:if test="${service_mailVO != null}">${service_mailVO.mail_cont.trim().isEmpty()? '':service_mailVO.mail_cont.trim()}</c:if><c:if test="${service_mailVO == null}">${param.mail_cont.trim().isEmpty()? '':param.mail_cont.trim()}</c:if></textarea>
					</td>
				</tr>
			</table>
					<input type="hidden" name="mail_stat" value="0">
					<input type="hidden" name="mail_read_stat" value="0">
					<input type="hidden" name="mail_no" value="${service_mailVO.mail_no}">
					<input type="hidden" name="action" value="update">
					<input type="submit" value="送出修改" class="confirm">
					<input type="submit" value="存入草稿" class="confirm">
			</form>
		</div>
	</div>
</div>
<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
</body>
</html>