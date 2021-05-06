<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.service_mail.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<title>�Ҧ��ȪA�H�C��</title>
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
	text-align: center;
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
	border-top: 1px solid #eee;
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
				<h5 style="color: #80c344;">${errorMsgs.notFound[0]}${errorMsgs.exception[0]}</h5>
				<h3>�ȪA�H�C��</h3>
				<hr>
				<div class="forSearch" id="forSearch">
					<ul>
						<li>
							<form method="post"	action="<%=request.getContextPath()%>/service_mail/service_mail.do">
								<label	for="mail_cont"></label>
								<input type="text" name="mail_cont" id="mail_cont" placeholder="�̫H�󤺮e�d��">
								<input type="hidden" name="action"	value="compositeSearch">
								<input type="submit" value="�e�X�d��" class="confirm" id="confirmTop">&nbsp;<span>�i���d��</span>
							</form>
					</li>
					</ul>
					<div class="forSearch forSearchMore" id="forSearchMore">
				<ul>
					<li>
						<form method="post"	action="<%=request.getContextPath()%>/service_mail/service_mail.do">
							<jsp:useBean id="service_mailSvc" class="com.service_mail.model.Service_mailService" />
							<jsp:useBean id="employeeSvc" class="com.employee.model.EmployeeService" />
							<jsp:useBean id="memberSvc"	class="com.member.model.MemberService" />

							<label for="mail_no">�H��s��:</label>
							<select size="1" name="mail_no" id="mail_no">
								<option value="no">--�п��--</option>
								<c:forEach var="service_mailVO" items="${service_mailSvc.all}">
									<option value="${service_mailVO.mail_no}">${service_mailVO.mail_no}</option>
								</c:forEach>
							</select>
							<br>
							<label for="emp_no">���u�s��:</label>
							<select size="1" name="emp_no" id="emp_no">
								<option value="no">--�п��--</option>
								<c:forEach var="employeeVO" items="${employeeSvc.all}">
									<c:if test="${employeeVO.emp_no != 90001}">
										<option value="${employeeVO.emp_no}">${employeeVO.emp_no}${employeeVO.name}</option>
									</c:if>
								</c:forEach>
							</select>
							<br>
							<label for="mbr_no">�|���s��:</label> 
							<select size="1" name="mbr_no" id="mbr_no">
								<option value="no">--�п��--</option>
								<c:forEach var="memberVO" items="${memberSvc.all}">
									<option value="${memberVO.mbr_no}">${memberVO.mbr_no}${memberVO.name}</option>
								</c:forEach>
							</select>
							<br>
							<label for="mail_time">�o�H���:</label>
							<input	type="text" name="mail_time" id="mail_time"></input>
							<br>
							<label	for="mail_cont">�H�󤺮e:</label>
							<input type="text"	name="mail_cont" id="mail_cont">
							<br>
							<label	for="mail_stat">�H�󪬺A:</label>
							<select size="1" name="mail_stat" id="mail_stat">
								<option value="no">--�п��--</option>
								<option value="0">����</option>
								<option value="1">�H��</option>
							</select>
							<br>
							<label for="mail_read_stat">�H��\Ū���A:</label>
							<select	size="1" name="mail_read_stat" id="mail_read_stat">
								<option value="no">--�п��--</option>
								<option value="0">��Ū</option>
								<option value="1">�wŪ</option>
							</select>
							<br>
							<input type="hidden" name="action" value="compositeSearch">
							<input type="submit" value="�e�X�d��" class="confirm">
						</form>
					</li>
				</ul>
				</div>
				</div>
<!-- 				<h2> -->
<%-- 					�Ҧ��ȪA�H�C��&nbsp;<a class="content" href="<%=request.getContextPath()%>/back-end/service_mail/select_page.jsp">�^�ȪA�޲z����</a> --%>
<!-- 				</h2> -->
<!-- 				<hr> -->

				<table>
<!-- 					<tr> -->
<!-- 						<th style="width: 50px">�s��</th> -->
<!-- 						<th style="width: 50px">�H��H</th> -->
<!-- 						<th style="width: 50px">����H</th> -->
<!-- 						<th style="width: 200px">���e</th> -->
<!-- 						<th style="width: 50px">�H�󪬺A</th> -->
<!-- 						<th style="width: 100px">�H��\Ū���A</th> -->
<!-- 						<th style="width: 100px">�o�H�ɶ�</th> -->
<!-- 					<th style="width:100px"><a class="content" href="#focus" style="text-decoration: none;">�ݧ�s</a><a -->
<!-- 						id="first" style="text-decoration: none;"></a></th> -->
<!-- 					</tr> -->
<%-- 				<jsp:useBean id="service_mailSvc" class="com.service_mail.model.Service_mailService"/> --%>
					<c:forEach var="service_mailVO" items="${service_mailSvc.all}">
						<%-- 					<tr ${service_mailVO.mail_no == param.mail_no ? 'bgcolor=#eee':''}> --%>
						<tr>
<%-- 							<c:if test="${service_mailVO.mail_no==param.mail_no}"> --%>
<%-- 								<td>${service_mailVO.mail_no}<a id="focus"></a></td> --%>
<%-- 							</c:if> --%>
<%-- 							<c:if test="${service_mailVO.mail_no!=param.mail_no}"> --%>
							<td>${service_mailVO.mail_no}</td>
<%-- 							</c:if> --%>
							<td>${service_mailVO.emp_no}</td>
							<td>${service_mailVO.mbr_no}</td>
							<c:set var="mail_cont" value="${service_mailVO.mail_cont}" />
							<c:if test="${mail_cont.length() > 10}">
								<td>${fn:substring(mail_cont, 0, 10)}...</td>
							</c:if>
							<c:if test="${mail_cont.length() <= 10}">
								<td>${mail_cont}</td>
							</c:if>
							<td>${service_mailVO.mail_stat}</td>
							<td class="mail_read_stat">${service_mailVO.mail_read_stat}</td>
							<c:set var="mail_time" value="${service_mailVO.mail_time}" />
							<td>${fn:substring(mail_time, 0, 10)}</td>
<!-- 						<td> -->
<%-- 							<form method="post" action="<%=request.getContextPath()%>/service_mail/service_mail.do"> --%>
<!-- 								<input class="change" type="submit" value="�ק�"> -->
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
		window.location.href="<%=request.getContextPath()%>/service_mail/service_mail.do?mail_no="+ mail_no + "&action=read";
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
<!-- �ѦҺ���: https://xdsoft.net/jqplugins/datetimepicker/ -->
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