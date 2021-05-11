<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*" %>
<%@ page import="com.announcement.model.*" %>

<%
	AnnouncementService announcementSvc = new AnnouncementService();
	List<AnnouncementVO> list = announcementSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<title>公告列表 - listAllAnnouncement.jsp</title>
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
	text-align: left;
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
/* 	border-top: 1px solid #eee; */
	border-bottom: 2px solid #eee;
}

tr:hover {
	box-shadow: 0 1px 5px 0 #4e5452 inset;
/* 	cursor: pointer; */
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

<%-- 錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<h3>公告列表&nbsp;
			<a class="content" href="<%=request.getContextPath()%>/back-end/announcement/addAnnouncement.jsp">新增公告</a>&nbsp;
			</h3>
			<hr>
<div style="text-align:center;font-weight:555;">
					<div style="width: 50px;display:inline-block;">編號</div>
					<div style="width: 50px;display:inline-block;">發文者</div>
					<div style="width: 200px;display:inline-block;">部分發文內容</div>
					<div style="width: 100px;display:inline-block;">公告日期</div>
					<div style="width: 200px;display:inline-block;">照片</div>
					<div style="width: 100px;display:inline-block;"><a href="#focus" class="content">看更新</a><a id="first"></a></div>
				</div>
				<hr>
<table>
<!-- 	<tr> -->
<!-- 		<th style="width:50px">編號</th> -->
<!-- 		<th style="width:80px">發文者</th> -->
<!-- 		<th style="width:170px">部分發文內容</th> -->
<!-- 		<th style="width:100px">公告日期</th> -->
<!-- 		<th style="width:200px">照片</th> -->
<!-- 		<th><a href="#focus">看更新</a><a id="first"></a></th> -->
	
<!-- 	</tr> -->
	<jsp:useBean id="employeeSvc" scope="page" class="com.employee.model.EmployeeService" />
	<c:forEach var="announcementVO" items="${list}" >	
		<tr ${(announcementVO.an_no==param.an_no || announcementVO.an_no==an_no) ? 'bgcolor=#98FB98' : '' }>
				<c:if test="${announcementVO.an_no==param.an_no || announcementVO.an_no==an_no}">
					<td id="focus">${announcementVO.an_no}<a  style="display: none;"></a></td>
				</c:if>
				<c:if test="${announcementVO.an_no!=param.an_no && announcementVO.an_no!=an_no}">
					<td>${announcementVO.an_no}</td>
				</c:if>
			<td>${employeeSvc.getOneEmployee(announcementVO.emp_no).name}</td>
			<c:set var="an_cont" value="${announcementVO.an_cont}"/>
			<td class="cont" >
			${fn:substring(an_cont, 0, 30)}<br>
			<button onclick="showModal${announcementVO.an_no}()">看全文</button>
			</td>
			<td>${announcementVO.an_skd_date}</td>
			<td><img src="<%=request.getContextPath()%>/announcement/GetPhoto?an_no=${announcementVO.an_no}" style="width:200px"></td>
		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/announcement/announcement.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="an_no"  value="${announcementVO.an_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
		</tr>
		<div class="modal" tabindex="-1" role="dialog" id="Modal${announcementVO.an_no}">
		     <div class="modal-dialog" role="document"> 
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title">公告全文</h5>
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button> 
		            </div>
		            <div class="modal-body">
						<c:set var="an_cont" value="${announcementVO.an_cont}"/>
						<% request.setAttribute("line", "\n"); %>
				        <p>${fn:replace(an_cont, line, '<br>')}</p><br>
						<img src="<%=request.getContextPath()%>/announcement/GetPhoto?an_no=${announcementVO.an_no}" style="width:200px">
						<p>${announcementVO.an_skd_date}</p>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		            </div>
		       </div>
		   </div>
		</div>
		<script>
			function showModal${announcementVO.an_no}() {
			    $('#Modal${announcementVO.an_no}').modal('show'); 
			}
		</script>
	</c:forEach>
</table>
</div>
</div>
</div>

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

</body>
</html>