<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*" %>
<%@ page import="com.member_mail.model.*" %>
<%@ page import="com.member.model.*" %>

<% MemberVO memberVO = (MemberVO)session.getAttribute("memberVO"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<title>所有系統通知列表</title>
<%@ include file="/part-of/partOfCampion_frontTop_css.txt"%>
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

div.forSearchs{
	margin: 0 auto;
	width: 70%;
	hieght: 50px;
	position: relative;
}
div.forSearchsMore{
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
<body onload="connection()">
<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
<div class="container">
	<div class="row">
		<div class="right col">
			<h5 style="color: #80c344;">${errorMsgs.notFound[0]}${errorMsgs.exception[0]}</h5>
			<h3>系統通知列表&nbsp;</h3>
			<hr>
			<jsp:useBean id="personal_system_notifySvc" class="com.personal_system_notify.model.Personal_System_NotifyService"/>
			<table>
				<tbody id="notifyTable">
				<c:forEach var="personal_system_notifyVO" items="${personal_system_notifySvc.all}">
					<c:if test="${memberVO.mbr_no == personal_system_notifyVO.mbr_no}">
					<tr>
						<td style="display:none;">${personal_system_notifyVO.ntfy_no}</td>
						<td>${personal_system_notifyVO.ntfy_cont}</td>
						<c:set var="ntfy_time" value="${personal_system_notifyVO.ntfy_time}" />
						<td>${fn:substring(ntfy_time, 0, 19)}</td>
						<td style="display:none;" class="ntfy_stat">${personal_system_notifyVO.ntfy_stat}</td>
					</tr>
					</c:if>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
<%@ include file="/part-of/partOfCampion_frontTop_js.txt"%>
<script>
	$("body").on("click","tr",function(e){
		let ntfy_no = e.currentTarget.children[0].innerText;
		window.location.href="<%=request.getContextPath()%>/personal_system_notify/personal_system_notify.do?ntfy_no="+ ntfy_no + "&action=read";
	});

	for (let i = 0; i < $(".ntfy_stat").length; i++) {
		if ($(".ntfy_stat")[i].innerText === '1') {
			$($(".ntfy_stat")[i]).parent()[0].style.backgroundColor = '#eee';
		}
		if ($(".ntfy_stat")[i].innerText === '0') {
			$($(".ntfy_stat")[i]).parent()[0].style.fontWeight = '555';
		}
	}
	
// 	let countSearch = 0;
// 	$("span").click(function(e){
// 		countSearch++;
// 		if (countSearch % 2 == 1) {
// 			$("#forSearchsMore")[0].style.display="inline";
// 			$("#confirmTop")[0].setAttribute("disabled","");
// 			$("#confirmTop")[0].style.backgroundColor="#4B7F52";
// 			$("#confirmTop")[0].style.color="#80c344";
// 			$("#confirmTop")[0].style.cursor="context-menu";
// 		} else {
// 			$("#forSearchsMore")[0].style.display="none";
// 			$("#confirmTop")[0].removeAttribute("disabled");
// 			$("#confirmTop")[0].style.backgroundColor="#80c344";
// 			$("#confirmTop")[0].style.color="#4e5452";
// 		}
// 	});
	
</script>
</body>
</html>