<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>
<%@ page import="com.campsite_owner.model.*"%>
<%@ page import="com.employee.model.*"%>
<jsp:useBean id="campsite_ownerSvc" class="com.campsite_owner.model.Campsite_ownerService"/>
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
<title>營主帳號管理列表 </title>
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
	margin-bottom: 0.5em;
}
div.number{
	text-align: center;
}
div.address{
	width:11em;
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
		 <h3>營主帳號管理列表 &nbsp;
			<a class="content" href="<%=request.getContextPath()%>/back-end/member/listAllMember.jsp">會員帳號管理列表</a>
			<a href="#focus" class="content">看更新</a>
		</h3>

<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>



<table>

	<c:forEach var="campsite_ownerVO" items="${campsite_ownerSvc.all}" >
		<tr ${campsite_ownerVO.cso_no==param.cso_no ? 'bgcolor=#eee' : '' }>
			<td style="width:5%;">
				<a class="content" href="<%=request.getContextPath()%>/campsite_owner/campsite_owner.do?action=getOne_For_Update_Back&cso_no=${campsite_ownerVO.cso_no}">${campsite_ownerVO.cso_no}</a>
				${campsite_ownerVO.cso_no==param.cso_no ? '<a id="focus"></a>' : '' }
			</td>
			<td style="width:32%;">
				<div class="inTr">
					<div class="L">營主姓名:</div>
					<div class="R">${campsite_ownerVO.name}</div>
				</div>
				<div class="inTr">
					<div class="L">帳號:</div>
					<div class="R">${campsite_ownerVO.acc}</div>
				</div>
				<div class="inTr">
					<div class="L">手機:</div>
					<div class="R number">${campsite_ownerVO.mobile}</div>
				</div>
			<td style="width:45%;">
				<div class="inTr">
					<div class="L">信箱</div>
					<div class="R number">${campsite_ownerVO.mail}</div>
				</div>
				<br>
				<div class="L">地址:</div>
				<div class="R address">
					${campsite_ownerVO.city}${campsite_ownerVO.dist}${campsite_ownerVO.add}
				</div>
			</td>
			<td style="width:18%;">
				<div class="inTr">
					<div>
						<input type="radio" id="stat0" value="${campsite_ownerVO.stat}" style="margin:0.5em;" ${campsite_ownerVO.stat==0?'checked':''} disabled>
						<label for="stat0" ${campsite_ownerVO.stat==0?'class="spotlight"':''}>未審核</label>
						<input type="radio" id="stat1" value="${campsite_ownerVO.stat}" style="margin:0.5em;"  ${campsite_ownerVO.stat==1?'checked':''} disabled>
						<label for="stat1" ${campsite_ownerVO.stat==1?'class="spotlight"':''}>已審核</label>
						<input type="radio" id="stat2" value="${campsite_ownerVO.stat}" style="margin:0.5em;"  ${campsite_ownerVO.stat==2?'checked':''} disabled>
						<label for="stat2" ${campsite_ownerVO.stat==2?'class="spotlight"':''}>已停權</label>
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
		                <h5 class="modal-title">${campsite_ownerVO.cso_no}號會員之詳細資料</h5>
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button> 
		            </div>
		            <div class="modal-body">
						<jsp:include page="listOneCampsite_owner.jsp" />
		            </div>
		            <div class="modal-footer">
		            	<input type="button" value="通過" style="margin:0.5em;" class="confirmStop">
						<input type="button" value="停權" style="margin:0.5em;" class="confirm">
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
// $("tr").click(function(e){
// 	console.log('in');
// 	let prod_ord_no = e.currentTarget.children[0].innerText;
<%-- 	window.location.href="<%=request.getContextPath()%>/product_order/product_order.do?prod_ord_no="+ prod_ord_no + "&action=read"; --%>
// });
</script>
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

</script>
</body>
</html>