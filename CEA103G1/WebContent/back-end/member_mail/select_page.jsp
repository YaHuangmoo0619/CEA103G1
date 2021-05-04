<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member_mail.model.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="zh-tw">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<title>Member_mail: Home</title>
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

form{
	margin: 0px auto;
}

span{
	color: #80c344;
}
input.confirm{
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
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
<div class="container">
	<div class="row">
		<div class= "left col-3">
		<%@ include file="/part-of/partOfCampion_backLeft_body.txt"%></div>
		<div class="right col-9">
			<h2>This is the Home page for Member_mail</h2>
			<hr>
			<h3>資料查詢:</h3>
			<%-- 錯誤列表 map ${errorMsgs}--%>
			<h5 style="color:#80c344;">${errorMsgs.notFound[0]}</h5>
			<ul>
				<li>
					<a class="content" href="<%=request.getContextPath() %>/back-end/member_mail/listAllMember_mail.jsp">List</a><br>
				</li>
				<li>
				<form method="post" action="<%=request.getContextPath() %>/member_mail/member_mail.do">
					<jsp:useBean id="member_mailSvc" class="com.member_mail.model.Member_mailService"/>
					<jsp:useBean id="employeeSvc" class="com.employee.model.EmployeeService"/>
					<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
					<jsp:useBean id="campsite_ownerSvc" class="com.campsite_owner.model.Campsite_ownerService"/>
					
					<label for="mail_no">信件編號:</label>
					<select size="1" name="mail_no" id="mail_no">
					<option value="no">--請選擇--</option>
					<c:forEach var="member_mailVO" items="${member_mailSvc.all}">
						<option value="${member_mailVO.mail_no}">${member_mailVO.mail_no}</option>
					</c:forEach>
					</select>
					<br>
					<label for="send_no">寄件會員編號:</label>
					<select size="1" name="send_no" id="send_no">
					<option value="no">--請選擇--</option>
					<c:forEach var="memberVO" items="${memberSvc.all}">
						<option value="${memberVO.mbr_no}">${memberVO.mbr_no}${memberVO.name}</option>
					</c:forEach>
					<c:forEach var="campsite_ownerVO" items="${campsite_ownerSvc.all}">
						<option value="${campsite_ownerVO.cso_no}" ${campsite_ownerVO.cso_no == param.rcpt_no? 'selected':''}>${campsite_ownerVO.cso_no}${campsite_ownerVO.name}</option>
					</c:forEach>
					</select>
					<br>
					<label for="rcpt_no">收件會員編號:</label>
					<select size="1" name="rcpt_no" id="rcpt_no">
					<option value="no">--請選擇--</option>
					<c:forEach var="memberVO" items="${memberSvc.all}">
						<option value="${memberVO.mbr_no}">${memberVO.mbr_no}${memberVO.name}</option>
					</c:forEach>
					</select>
					<br>
					<label for="mail_time">發信日期:</label>
					<input type="text" name="mail_time" id="mail_time"></input>
					<br>
					<label for="mail_cont">信件內容:</label>
					<input type="text" name="mail_cont" id="mail_cont">
					<br>
					<label for="mail_stat">信件狀態:</label>
					<select size="1" name="mail_stat" id="mail_stat">
						<option value="no">--請選擇--</option>
						<option value="0">收件</option>
						<option value="1">寄件</option>
					</select>
					<br>
					<label for="mail_read_stat">信件狀態:</label>
					<select size="1" name="mail_read_stat" id="mail_read_stat">
						<option value="no">--請選擇--</option>
						<option value="0">未讀</option>
						<option value="1">已讀</option>
					</select>
					<br>
					<input type="hidden" name="action" value="compositeSearch">
					<input type="submit" value="送出查詢" class="confirm">
				</form>
				</li>
			</ul>
	 		<h3>新增會員站內信:</h3>
			<ul>
				<li>
					<a class="content" href="<%=request.getContextPath()%>/back-end/member_mail/addMember_mail.jsp">add</a>
				</li>
			</ul>
		</div>
	</div>
</div>
<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>
<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->
$.datetimepicker.setLocale('zh');
$(function(){
	$('#mail_time').datetimepicker({
		format:'Y-m-d',
		maxDate:'+1970/01/01',
		timepicker:false
	});
});
</script>
</body>
</html>