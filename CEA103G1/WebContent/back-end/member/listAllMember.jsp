<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.member_rank.model.*"%>
<%@ page import="com.employee.model.*"%>
<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
<jsp:useBean id="member_rankSvc" scope="page" class="com.member_rank.model.Member_rankService" />
<% 
	EmployeeVO employeeVO = (EmployeeVO)session.getAttribute("employeeVO");
	if(employeeVO == null){
		response.sendRedirect(request.getContextPath()+"/campion_back_login.jsp");
		return;
	}
%>

<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>會員帳號管理列表 </title>
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
	overflow:scroll;
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

input.confirmStop{
	background-color: red;
	color: white;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}
input.confirmStop:hover{
	background-color: pink;
	color: red;
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
	text-align: left;
	/* 	border: 1px solid #4e5452; */
	padding: 10px 10px;
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
/* 	border-top: 1px solid #eee; */
	border-bottom: 2px solid #eee;
}

tr:hover {
	box-shadow: 0 1px 5px 0 #4e5452 inset;
/*  	cursor: pointer; */
}

img.inDiv{
	width:50px;
	margin:1px;
}
div.innerDiv{
 	display:inline;
}
hr{
	margin: 3px;
	width: 30%;
	border-color:#80c344;
}
div.L{
	display:inline-block;
	width:6em;
	font-weight: 555;
	margin-bottom: 0.5em;
}
div.R{
	display:inline-block;
	width:4em;
}
div.number{
	text-align: center;
}
div.inTr{
	display:inline-block;
}
label.spotlight{
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}
textarea{
	resize: none;
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
		 <h3>會員帳號管理列表 &nbsp;
			<a class="content" href="<%=request.getContextPath()%>/back-end/campsite_owner/listAllCampsite_owner.jsp">營主帳號管理列表</a>
			<a href="#focus" class="content">看更新</a>
		</h3>

<table>

	<c:forEach var="memberVO" items="${memberSvc.all}" >
		<tr ${memberVO.mbr_no==param.mbr_no ? 'bgcolor=#eee' : '' }>
			<td style="width:5%;">
				<a class="content" href="<%=request.getContextPath()%>/member/member.do?action=getOne_For_Update_Back&mbr_no=${memberVO.mbr_no}">${memberVO.mbr_no}</a>
				${memberVO.mbr_no==param.mbr_no ? '<a id="focus"></a>' : '' }
			</td>
			<td style="width:35%;">
				<div class="inTr">
					<div class="L">會員姓名:</div>
					<div class="R">${memberVO.name}</div>
				</div>
				<div class="inTr">
					<div class="L">會員等級:</div>
					<div class="R">${member_rankSvc.getOneMember_rank(memberVO.rank_no).rank_name}</div>
				</div>
			<td style="width:35%;">
				<div class="inTr">
					<div class="L">會員經驗值:</div>
					<div class="R number">${memberVO.exp}</div>
				</div>
				<div class="inTr">
					<div class="L">會員點數:</div>
					<div class="R number">${memberVO.pt}</div>
				</div>
			</td>
			<td style="width:35%;">
				<div class="inTr">
					<div class="L">會員備註:</div>
					<div class="R">
						<textarea rows="2" cols="15" readonly>${memberVO.rmk}</textarea>
					</div>
				</div>
			</td>
			<td style="width:45%;">
				<div class="inTr">
					<div>
						<input type="button" value="帳號停權" style="margin:0.5em;" class="confirmStop">
						<input type="button" value="恢復帳號" style="margin:0.5em;" class="confirm">
					</div>
				</div>	
		</tr>
	</c:forEach>
</table>
</div>
</div>
</div>
<c:if test="${openModal!=null}">
		<div class="modal" tabindex="-1" role="dialog" id="Modal">
		     <div class="modal-dialog modal-lg" role="document"> 
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title">${memberVO.mbr_no}號會員之詳細資料</h5>
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button> 
		            </div>
		            <div class="modal-body">
						<jsp:include page="listOneMember.jsp" />
		            </div>
		            <div class="modal-footer">
		            </div>
		       </div>
		   </div>
		</div>

<script>
	$('#Modal').modal({
	  	show :true
	}); 
</script>
</c:if>
<script>
	let backToTop = document.getElementsByClassName("backToTop");
	$(window).scroll(function(e) {
		if ($(window).scrollTop() <= 1) {
			backToTop[1].style.display = "none";
		} else {
			backToTop[1].style.display = "block";
		}
	});
</script>
<script>
	$("textarea").focus(function(){
		$("textarea").css("outline","none");
	});
</script>
</body>
</html>