<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*" %>
<%@ page import="com.service_mail.model.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<title>所有客服信列表</title>
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
#focus{
	margin-right: -5px;
}
tr{
	border-top: 1px solid #4e5452;
	border-bottom: 1px solid #4e5452;
}
tr:hover{
 	box-shadow: 0 1px 5px 0 #4e5452;
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
			<h2>所有客服信列表&nbsp;<a class="content" href="<%=request.getContextPath()%>/back-end/service_mail/select_page.jsp">回客服管理首頁</a></h2>
			<hr>
			${errorMsgs.Exception}
			<h3>資料列表:</h3>
			<table>
				<tr>
					<th style="width:50px">編號</th>
					<th style="width:50px">寄件人</th>
					<th style="width:50px">收件人</th>
					<th style="width:200px">內容</th>
					<th style="width:50px">信件狀態</th>
					<th style="width:100px">信件閱讀狀態</th>
					<th style="width:100px">發信時間</th>
<!-- 					<th style="width:100px"><a class="content" href="#focus" style="text-decoration: none;">看更新</a><a -->
<!-- 						id="first" style="text-decoration: none;"></a></th> -->
				</tr>
				<jsp:useBean id="service_mailSvc" class="com.service_mail.model.Service_mailService"/>
				<c:forEach var="service_mailVO" items="${service_mailSvc.all}">
<%-- 					<tr ${service_mailVO.mail_no == param.mail_no ? 'bgcolor=#eee':''}> --%>
					<tr>
						<c:if
							test="${service_mailVO.mail_no==param.mail_no}">
							<td>${service_mailVO.mail_no}<a id="focus"></a></td>
						</c:if>
						<c:if
							test="${service_mailVO.mail_no!=param.mail_no}">
							<td>${service_mailVO.mail_no}</td>
						</c:if>
						<td>${service_mailVO.emp_no}</td>
						<td>${service_mailVO.mbr_no}</td>
						<c:set var="mail_cont" value="${service_mailVO.mail_cont}"/>
						<td>${fn:substring(mail_cont, 0, 10)}...</td>
						<td>${service_mailVO.mail_stat}</td>
						<td>${service_mailVO.mail_read_stat}</td>
						<c:set var="mail_time" value="${service_mailVO.mail_time}"/>
						<td>${fn:substring(mail_time, 0, 10)}</td>
<!-- 						<td> -->
<%-- 							<form method="post" action="<%=request.getContextPath()%>/service_mail/service_mail.do"> --%>
<!-- 								<input class="change" type="submit" value="修改"> -->
<%-- 								<input type="hidden" name="mail_no" value="${service_mailVO.mail_no}"> --%>
<!-- 								<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 							</form> -->
<!-- 						</td> -->
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
<script>
	$("tr").click(function(e){
		let mail_no = e.currentTarget.children[0].innerText;
		console.log(mail_no);
		window.location.href="<%=request.getContextPath()%>/back-end/service_mail/listOneService_mail.jsp?mail_no="+mail_no+"&action=read";
	});
</script>
</body>
</html>