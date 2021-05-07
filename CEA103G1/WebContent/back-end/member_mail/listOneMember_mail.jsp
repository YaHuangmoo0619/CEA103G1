<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.member_mail.model.*" %>

<% Member_mailVO member_mailVO = (Member_mailVO)request.getAttribute("member_mailVO"); %>

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
			<h2>信件內容&nbsp;<a class="content" href="<%=request.getContextPath()%>/back-end/member_mail/listAllMember_mail.jsp">回會員站內信列表</a></h2>
			<hr>
			<h5 style="color:#80c344;">${errorMsgs.notFound[0]}${errorMsgs.exception[0]}</h5>
			<div class="mail">
			
			<jsp:useBean id="member_mailSvc" class="com.member_mail.model.Member_mailService"/>
			<jsp:useBean id="member_mail_pictureSvc" class="com.member_mail_picture.model.Member_mail_pictureService"/>
			<jsp:useBean id="employeeSvc" class="com.employee.model.EmployeeService"/>
			<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
			
				<p>寄件人：&nbsp;${member_mailVO.send_no}${employeeSvc.getOneEmployee(member_mailVO.send_no).name}${memberSvc.getOneMember(member_mailVO.send_no).name}${campsite_ownerSvc.getOneCampsite_owner(member_mailVO.send_no).name}</p>
					
				<p>收件人：&nbsp;${member_mailVO.rcpt_no}${memberSvc.getOneMember(member_mailVO.rcpt_no).name}</p>
					
				<p>內容：</p>
				<p>${member_mailVO.mail_cont.trim()}</p>

				<p>${member_mail_pictureSvc.getByMail_no(member_mailVO.mail_no).size()!=0?'附件照片：':''}</p>
				<p>
				<c:forEach var="member_mail_pictureVO" items="${member_mail_pictureSvc.getByMail_no(member_mailVO.mail_no)}">
					<img class="info" src="${member_mail_pictureVO.mail_pic}">
				</c:forEach>
				</p>
				<hr>
				<p style="font-size:0.5em">發信時間：&nbsp;${member_mailVO.mail_time}</p>
						
			</div>
			<form method="post" action="<%=request.getContextPath()%>/back-end/member_mail/addMember_mail.jsp">		
					<input type="hidden" name="mail_no" value="${member_mailVO.mail_no}">
					<input type="hidden" name="send_no" value="${member_mailVO.send_no}">
					<input type="hidden" name="rcpt_no" value="${member_mailVO.rcpt_no}">
					<input type="submit" value="回覆" class="confirm">
			</form>
			
		</div>
	</div>
</div>
<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
</body>
</html>