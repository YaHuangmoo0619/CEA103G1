<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*" %>
<%@ page import="com.announcement.model.*" %>

<%
	AnnouncementService announcementSvc = new AnnouncementService();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<title>公告列表 - listOneAnnouncement.jsp</title>
<%@ include file="/part-of/partOfCampion_frontTop_css.txt"%>
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

/* table{ */
/* 	width: 700px; */
/* 	margin: 30px auto; */
/* 	border: 1px solid #4e5452; */
/* } */
/* th, td{ */
/* 	text-align: center; */
/* 	border: 1px solid #4e5452; */
/* 	padding: 10px 15px; */
/* } */
/* td.function{ */
/* 	text-align: justify;	 */
/* } */
label.spotlight{
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}
form{
	text-align: center;
}
/* textarea{ */
/* 	resize: none; */
/* } */

div.mail{
	text-align: left;
	margin: 50px auto;
	width: 60%;
	font-size:1.2em;
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
img.info{
	max-width:30%;
	margin: 3px;
}
</style>

</head>
<body>
<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
<div class="container">
	<div class="row">
		<div class="right col">
			<h2>公告內容&nbsp;<a class="content" href="<%=request.getContextPath()%>/front-end/announcement/listAllAnnouncement.jsp">回公告列表</a></h2>
		<hr>
		<div class="mail">
		<jsp:useBean id="employeeSvc" scope="page" class="com.employee.model.EmployeeService" />

<%-- 				<p>${announcementVO.an_no}</p> --%>
<%-- 				<p>${employeeSvc.getOneEmployee(announcementVO.emp_no).name}</p> --%>
				<c:set var="an_cont" value="${announcementVO.an_cont}"/>
				<% request.setAttribute("line", "\n"); %>
		        <p>${fn:replace(an_cont, line, '<br>')}</p><br>
				<img src="<%=request.getContextPath()%>/announcement/GetPhoto?an_no=${announcementVO.an_no}" style="width:200px">
				<p>${announcementVO.an_skd_date}</p>
			
</div>
</div>
</div>
</div>
<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
</body>
</html>