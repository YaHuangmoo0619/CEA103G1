<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*" %>
<%@ page import="com.campsite_owner_mail.model.*" %>
<%@ page import="com.campsite_owner.model.*" %>

<% Campsite_ownerVO campsite_ownerVO = (Campsite_ownerVO)session.getAttribute("campsite_ownerVO"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<title>營主站內信搜尋結果</title>
<%@ include file="/part-of/partOfCampion_COwnerTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_COwnerLeft_css.txt"%>
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

tr:hover {
	box-shadow: 0 1px 5px 0 #4e5452 inset;
	cursor: pointer;
}
</style>

</head>
<body>
<%@ include file="/part-of/partOfCampion_COwnerTop_body.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
<div class="container">
	<div class="row">
		<div class= "left col-3">
		<%@ include file="/part-of/partOfCampion_COwnerLeft_body.txt"%></div>
		<div class="right col-9">
			<h5 style="color: #80c344;">${errorMsgs.notFound[0]}${errorMsgs.exception[0]}</h5>
			<h3>營主站內信搜尋結果</h3>
			<a href="<%=request.getContextPath()%>/front-end/campsite_owner_mail/addCampsite_owner_mail.jsp">寄信</a>
			<a href="<%=request.getContextPath()%>/front-end/campsite_owner_mail/listAllCampsite_owner_mail.jsp">回到營主站內信列表</a>
			<hr>
				<div style="text-align:center;font-weight:555;">
					<div style="width: 150px;display:inline-block;">寄件人</div>
					<div style="width: 150px;display:inline-block;">收件人</div>
					<div style="width: 250px;display:inline-block;">信件內容</div>
					<div style="width: 150px;display:inline-block;">信件日期</div>
				</div>
			<table>
<!-- 				<tr> -->
<!-- 					<th style="width:50px">編號</th> -->
<!-- 					<th style="width:50px">寄件會員</th> -->
<!-- 					<th style="width:50px">收件會員</th> -->
<!-- 					<th style="width:200px">內容</th> -->
<!-- 					<th style="width:50px">信件狀態</th> -->
<!-- 					<th style="width:100px">信件閱讀狀態</th> -->
<!-- 					<th style="width:100px">發信時間</th> -->
<!-- 					<th style="width:100px"></th> -->
<!-- 				</tr> -->
				<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
				<c:forEach var="campsite_owner_mailVO" items="${campsite_owner_mailVOSet}">
					<c:if test="${campsite_ownerVO.cso_no == campsite_owner_mailVO.send_no}">
					<tr>
						<td>${campsite_owner_mailVO.mail_no}</td>
						<td>${campsite_owner_mailVO.send_no}${memberSvc.getOneMember(campsite_owner_mailVO.send_no).name}</td>
						<td>${campsite_owner_mailVO.rcpt_no}${memberSvc.getOneMember(campsite_owner_mailVO.rcpt_no).name}</td>
						<c:set var="mail_cont" value="${campsite_owner_mailVO.mail_cont}" />
							<c:if test="${mail_cont.length() > 10}">
								<td>${fn:substring(mail_cont, 0, 10)}...</td>
							</c:if>
							<c:if test="${mail_cont.length() <= 10}">
								<td>${mail_cont}</td>
							</c:if>
						<td>${campsite_owner_mailVO.mail_stat}</td>
						<td class="mail_read_stat">${campsite_owner_mailVO.mail_read_stat}</td>
						<c:set var="mail_time" value="${campsite_owner_mailVO.mail_time}" />
							<td>${fn:substring(mail_time, 0, 10)}</td>
						<td>
							<form method="post" action="<%=request.getContextPath()%>/authority/authority.do">
								<input class="change" type="submit" value="修改">
								<input type="hidden" name="mail_no" value="${service_mailVO.mail_no}">
								<input type="hidden" name="action" value="getOne_For_Update">
							</form>
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