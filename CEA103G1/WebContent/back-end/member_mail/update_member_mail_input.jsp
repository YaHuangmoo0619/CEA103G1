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
<title>修改會員站內信</title>
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
			<h2>修改會員站內信&nbsp;<a class="content" href="<%=request.getContextPath()%>/back-end/member_mail/select_page.jsp">回首頁</a></h2>
			<hr>
			<h5 style="color:#80c344;">${errorMsgs.notFound[0]}${errorMsgs.exception[0]}</h5>
			<h3>資料列表:</h3>
			<form method="post" action="<%=request.getContextPath()%>/member_mail/member_mail.do">
			<jsp:useBean id="member_mailSvc" class="com.member_mail.model.Member_mailService"/>
			<jsp:useBean id="campsite_ownerSvc" class="com.campsite_owner.model.Campsite_ownerService"/>
			<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
			<table>
				<tr>
					<td>
						<label for="send_no">寄件人</label>
						<br><h5 style="color:#80c344;">${errorMsgs.send_no[0]}</h5>
					</td>
					<td>
						<select size="1" name="send_no" id="send_no">
						<option value="99">--請選擇--</option>
						<c:forEach var="memberVO" items="${memberSvc.all}">
							<option value="${memberVO.mbr_no}" ${memberVO.mbr_no == member_mailVO.send_no || memberVO.mbr_no == param.send_no? 'selected':''}>${memberVO.mbr_no}${memberVO.name}</option>
						</c:forEach>
						<c:forEach var="campsite_ownerVO" items="${campsite_ownerSvc.all}">
							<option value="${campsite_ownerVO.cso_no}" ${campsite_ownerVO.cso_no == member_mailVO.send_no || campsite_ownerVO.cso_no == param.send_no? 'selected':''}>${campsite_ownerVO.cso_no}${campsite_ownerVO.name}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="rcpt_no">收件人</label>
						<br><h5 style="color:#80c344;">${errorMsgs.rcpt_no[0]}</h5>
					</td>
					<td>
						<select size="1" name="rcpt_no" id="rcpt_no">
						<option value="99">--請選擇--</option>
						<c:forEach var="memberVO" items="${memberSvc.all}">
							<option value="${memberVO.mbr_no}" ${memberVO.mbr_no == member_mailVO.rcpt_no || memberVO.mbr_no == param.rcpt_no? 'selected':''}>${memberVO.mbr_no}${memberVO.name}</option>
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
						<textarea name="mail_cont" rows="10" cols="45" class="mail_cont"><c:if test="${member_mailVO != null}">${member_mailVO.mail_cont.trim().isEmpty()? '':member_mailVO.mail_cont.trim()}</c:if><c:if test="${member_mailVO == null}">${param.mail_cont.trim().isEmpty()? '':param.mail_cont.trim()}</c:if></textarea>
					</td>
				</tr>
			</table>
					<input type="hidden" name="mail_stat" value="0">
					<input type="hidden" name="mail_read_stat" value="0">
					<c:if test="${member_mailVO != null}"><input type="hidden" name="mail_no" value="${member_mailVO.mail_no}"></c:if>
					<c:if test="${member_mailVO == null}"><input type="hidden" name="mail_no" value="${param.mail_no}"></c:if>
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