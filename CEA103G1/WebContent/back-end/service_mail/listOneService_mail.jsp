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
<title>信件內容</title>
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

/* table{ */
/* 	width: 700px; */
/* 	margin: 30px auto; */
/* 	border: 1px solid #4e5452; */
/* } */
/* th, td{ */
/* 	text-align: center; */
/* 	border: 1px solid #4e5452; */
/* 	padding: 10px 15px; */
/* } */
/* td.function{ */
/* 	text-align: justify;	 */
/* } */
label.spotlight{
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}
form{
	text-align: center;
}
/* textarea{ */
/* 	resize: none; */
/* } */

div.mail{
	text-align: left;
	margin: 50px auto;
	width: 70%;
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
img.info{
	max-width:30%;
	margin: 3px;
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
			<h2>信件內容&nbsp;<a class="content" href="<%=request.getContextPath()%>/back-end/service_mail/listAllService_mail.jsp">回客服信列表</a></h2>
			<hr>
			<h5 style="color:#80c344;">${errorMsgs.notFound[0]}${errorMsgs.exception[0]}</h5>
			<div class="mail">
			
			<jsp:useBean id="service_mailSvc" class="com.service_mail.model.Service_mailService"/>
			<jsp:useBean id="service_mail_pictureSvc" class="com.service_mail_picture.model.Service_mail_pictureService"/>
			<jsp:useBean id="employeeSvc" class="com.employee.model.EmployeeService"/>
			<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
			
				<p>回覆人員：&nbsp;${service_mailVO.emp_no}${employeeSvc.getOneEmployee(service_mailVO.emp_no).name}</p>
					
				<p>發信會員：&nbsp;${service_mailVO.mbr_no}${memberSvc.getOneMember(service_mailVO.mbr_no).name}</p>
					
				<p>內容：</p>
				<p>${service_mailVO.mail_cont.trim()}</p>

				<p>${service_mail_pictureSvc.getByMail_no(service_mailVO.mail_no).size()!=0?'附件照片：':''}</p>
				<p>
				<c:forEach var="service_mail_pictureVO" items="${service_mail_pictureSvc.getByMail_no(service_mailVO.mail_no)}">
					<img class="info" src="${service_mail_pictureVO.mail_pic}">
				</c:forEach>
				</p>
				<hr>
				<p style="font-size:0.5em">發信時間：&nbsp;${service_mailVO.mail_time}</p>
						
			</div>
			<form method="post" action="<%=request.getContextPath()%>/back-end/service_mail/addService_mail.jsp">		
					<input type="hidden" name="mail_no" value="${service_mailVO.mail_no}">
					<input type="hidden" name="emp_no" value="${service_mailVO.emp_no}">
					<input type="hidden" name="mbr_no" value="${service_mailVO.mbr_no}">
					<input type="submit" value="回覆" class="confirm">
			</form>
			
		</div>
	</div>
</div>
<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
</body>
</html>