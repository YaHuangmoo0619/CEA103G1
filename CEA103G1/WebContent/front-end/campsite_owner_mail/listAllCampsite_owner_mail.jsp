<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*" %>
<%@ page import="com.campsite_owner_mail.model.*" %>
<%@ page import="com.campsite_owner.model.*" %>

<!-- 測試登入狀態及畫面改變 -->
<%
Campsite_ownerService campsite_ownerSvcLogin = new Campsite_ownerService();
Campsite_ownerVO campsite_ownerVOLogin = campsite_ownerSvcLogin.getOneCampsite_owner(70003);
session.setAttribute("campsite_ownerVO",campsite_ownerVOLogin);
%>

<% Campsite_ownerVO campsite_ownerVO = (Campsite_ownerVO)session.getAttribute("campsite_ownerVO"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<title>所有營主站內信列表</title>
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
<body>
<%@ include file="/part-of/partOfCampion_COwnerTop_body.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
<div class="container">
	<div class="row">
		<div class= "left col-3">
		<%@ include file="/part-of/partOfCampion_COwnerLeft_body.txt"%></div>
		<div class="right col-9">
			<h5 style="color: #80c344;">${errorMsgs.notFound[0]}${errorMsgs.exception[0]}</h5>
			<h3>營主站內信列表</h3>
			<a href="<%=request.getContextPath()%>/back-end/campsite_owner_mail/addCampsite_owner_mail.jsp">寄信</a>
			<a href="<%=request.getContextPath()%>/back-end/campsite_owner_mail/listAllCampsite_owner_mail_send.jsp">寄件備份</a>
			<hr>
			<div class="forSearch" id="forSearch">
					<ul>
						<li>
							<form method="post"	action="<%=request.getContextPath()%>/campsite_owner_mail/campsite_owner_mail.do">
								<label	for="mail_cont"></label>
								<input type="text" name="mail_cont" id="mail_cont" placeholder="依信件內容查詢">
								<input type="hidden" name="action"	value="compositeSearchTop">
								<input type="submit" value="送出查詢" class="confirm" id="confirmTop">&nbsp;<span>進階查詢</span>
							</form>
					</li>
					</ul>
					<div class="forSearch forSearchMore" id="forSearchMore">
					<ul>
						<li>
							<form method="post"	action="<%=request.getContextPath()%>/campsite_owner_mail/campsite_owner_mail.do">
								<jsp:useBean id="campsite_owner_mailSvc" class="com.campsite_owner_mail.model.Campsite_owner_mailService" />
								<jsp:useBean id="campsite_ownerSvc" class="com.campsite_owner.model.Campsite_ownerService" />
								<jsp:useBean id="employeeSvc" class="com.employee.model.EmployeeService" />
								<jsp:useBean id="memberSvc"	class="com.member.model.MemberService" />
	
								<label for="mail_no">信件編號:</label>
								<select size="1" name="mail_no" id="mail_no">
									<option value="no">--請選擇--</option>
									<c:forEach var="campsite_owner_mailVO" items="${campsite_owner_mailSvc.all}">
										<option value="${campsite_owner_mailVO.mail_no}">${campsite_owner_mailVO.mail_no}</option>
									</c:forEach>
								</select>
								<br>
								<label for="send_no">寄件人:</label>
								<select size="1" name="send_no" id="send_no">
									<option value="no">--請選擇--</option>
									<c:forEach var="campsite_ownerVO" items="${campsite_ownerSvc.all}">
										<option value="${campsite_ownerVO.cso_no}">${campsite_ownerVO.cso_no}${campsite_ownerVO.name}</option>
									</c:forEach>
									<c:forEach var="memberVO" items="${memberSvc.all}">
										<option value="${memberVO.mbr_no}">${memberVO.mbr_no}${memberVO.name}</option>
									</c:forEach>
								</select>
								<br>
								<label for="mbr_no">發信會員:</label> 
								<select size="1" name="mbr_no" id="mbr_no">
									<option value="no">--請選擇--</option>
									<c:forEach var="memberVO" items="${memberSvc.all}">
										<option value="${memberVO.mbr_no}">${memberVO.mbr_no}${memberVO.name}</option>
									</c:forEach>
									<c:forEach var="campsite_ownerVO" items="${campsite_ownerSvc.all}">
										<option value="${campsite_ownerVO.cso_no}">${campsite_ownerVO.cso_no}${campsite_ownerVO.name}</option>
									</c:forEach>
								</select>
								<br>
								<label for="mail_time">發信日期:</label>
								<input	type="text" name="mail_time" id="mail_time"></input>
								<br>
								<label	for="mail_cont">信件內容:</label>
								<input type="text"	name="mail_cont" id="mail_cont">
								<br>
								<label	for="mail_stat">信件狀態:</label>
								<select size="1" name="mail_stat" id="mail_stat">
									<option value="no">--請選擇--</option>
									<option value="0">收件</option>
									<option value="1">寄件</option>
								</select>
								<br>
								<label for="mail_read_stat">信件閱讀狀態:</label>
								<select	size="1" name="mail_read_stat" id="mail_read_stat">
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
					</div>
				</div>
				<div style="text-align:center;font-weight:555;">
					<div style="width: 150px;display:inline-block;">寄件人</div>
					<div style="width: 150px;display:inline-block;">收件人</div>
					<div style="width: 250px;display:inline-block;">信件內容</div>
					<div style="width: 150px;display:inline-block;">信件日期</div>
				</div>
			<table>
<!-- 				<tr> -->
<!-- 					<th style="width:50px">編號</th> -->
<!-- 					<th style="width:50px">寄件者</th> -->
<!-- 					<th style="width:50px">收件者</th> -->
<!-- 					<th style="width:200px">內容</th> -->
<!-- 					<th style="width:50px">信件狀態</th> -->
<!-- 					<th style="width:100px">信件閱讀狀態</th> -->
<!-- 					<th style="width:100px">發信時間</th> -->
<!-- 					<th style="width:100px"><a class="content" href="#focus" style="text-decoration: none;">看更新</a><a -->
<!-- 						id="first" style="text-decoration: none;"></a></th> -->
<!-- 				</tr> -->
<%-- 				<jsp:useBean id="campsite_owner_mailSvc" class="com.campsite_owner_mail.model.Campsite_owner_mailService"/> --%>
				<c:forEach var="campsite_owner_mailVO" items="${campsite_owner_mailSvc.all}">
					<c:if test="${campsite_ownerVO.cso_no == campsite_owner_mailVO.rcpt_no}">
					<tr>
<%-- 						<c:if --%>
<%-- 							test="${campsite_owner_mailVO.mail_no==param.mail_no}"> --%>
<%-- 							<td>${campsite_owner_mailVO.mail_no}<a id="focus"></a></td> --%>
<%-- 						</c:if> --%>
<%-- 						<c:if --%>
<%-- 							test="${campsite_owner_mailVO.mail_no!=param.mail_no}"> --%>
						<td>${campsite_owner_mailVO.mail_no}</td>
<%-- 						</c:if> --%>
						<td>${campsite_owner_mailVO.send_no}${employeeSvc.getOneEmployee(campsite_owner_mailVO.send_no).name}${memberSvc.getOneMember(campsite_owner_mailVO.send_no).name}</td>
						<td>${campsite_owner_mailVO.rcpt_no}${campsite_ownerSvc.getOneCampsite_owner(campsite_owner_mailVO.rcpt_no).name}</td>
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
<!-- 						<td> -->
<%-- 							<form method="post" action="<%=request.getContextPath()%>/campsite_owner_mail/campsite_owner_mail.do"> --%>
<!-- 								<input class="change" type="submit" value="修改"> -->
<%-- 								<input type="hidden" name="mail_no" value="${campsite_owner_mailVO.mail_no}"> --%>
<!-- 								<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 							</form> -->
<!-- 						</td> -->
					</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
<script>
	$("tr").click(function(e){
		let mail_no = e.currentTarget.children[0].innerText;
		window.location.href="<%=request.getContextPath()%>/campsite_owner_mail/campsite_owner_mail.do?mail_no="+ mail_no + "&action=read";
	});

	for (let i = 0; i < $(".mail_read_stat").length; i++) {
		if ($(".mail_read_stat")[i].innerText === '1') {
			$($(".mail_read_stat")[i]).parent()[0].style.backgroundColor = '#eee';
		}
		if ($(".mail_read_stat")[i].innerText === '0') {
			$($(".mail_read_stat")[i]).parent()[0].style.fontWeight = '555';
		}
	}
	
	let countSearch = 0;
	$("span").click(function(e){
		countSearch++;
		if (countSearch % 2 == 1) {
			$("#forSearchMore")[0].style.display="inline";
			$("#confirmTop")[0].setAttribute("disabled","");
			$("#confirmTop")[0].style.backgroundColor="#4B7F52";
			$("#confirmTop")[0].style.color="#80c344";
			$("#confirmTop")[0].style.cursor="context-menu";
		} else {
			$("#forSearchMore")[0].style.display="none";
			$("#confirmTop")[0].removeAttribute("disabled");
			$("#confirmTop")[0].style.backgroundColor="#80c344";
			$("#confirmTop")[0].style.color="#4e5452";
		}
	});
	
</script>
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