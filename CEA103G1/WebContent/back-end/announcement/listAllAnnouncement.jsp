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
<!-- 回復刪除的檔案 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<title>所有公告列表 - listAllAnnouncement.jsp</title>

<style>
  table#table-1 {
	background-color: #FFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	position: relative;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  } 
  td.cont{
  	text-align: left;
  }
  .seeMoreBox{
  	border-radius: 5%;
  	background-color: #333;
  	position: absolute;
  	width: 500px;
  	display: none;
  	opacity: 0.9;
  }
  .seeMoreWord{
  	border-radius: 5%;
  	background-color: #fff;
	width: 300px;
	color: black;
	padding: 30px;
	margin: 75px;
  }
  #focusButton{
  	width:230px;
  	margin-left: 250px;
  	padding: 10px;
  	border: 1px solid #ccc;
  	text-align: center;
  }
  #focusButton:hover{
  	background-color: #98FB98;
  	cursor: pointer;
  }
  a:hover{
  	text-decoration: none;
  }
  #back:hover{
 	background-color: #98FB98;
  	cursor: pointer;
  }
</style>

</head>
<body bgcolor='white'>
<table id="table-1">	
	<tr><td>
		 <h3>所有公告列表 - listAllAnnouncement.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/announcement/select_page.jsp"><img src="<%=request.getContextPath()%>/images/logo.png" width="50" height="50" border="0"><br>回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div id="focusButton"><a href="#focus">查看當筆新增或修改的資料</a><a id="first"></a></div>
<table>
	<tr>
		<th style="width:50px">編號</th>
		<th style="width:80px">發文者</th>
		<th style="width:170px">部分發文內容</th>
		<th style="width:100px">公告日期</th>
		<th style="width:200px">照片</th>
	
	</tr>
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
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/announcement/announcement.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="an_no"  value="${announcementVO.an_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			<td id="back" style="width: 80px;"><a href="#first" style="font-size: 0.5em;">回到第一筆</a></td>
		</tr>
		<div class="modal" tabindex="-1" role="dialog" id="Modal${announcementVO.an_no}">
		     <div class="modal-dialog" role="document"> 
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title">公告全文</h5>
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button> 
		            </div>
		            <div class="modal-body">
		            					<% request.setAttribute("line", "\n"); %>
		                                <p>${fn:replace(an_cont, line, '<br>')}</p> 
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


</body>
</html>