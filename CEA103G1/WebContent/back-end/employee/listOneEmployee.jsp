<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*" %>
<%@ page import="com.employee.model.*" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<title>網站管理員資料 - listOneEmployee.jsp</title>
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
</style>
</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>網站管理員資料 - listOneEmployee.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/employee/select_page.jsp"><img src="<%=request.getContextPath()%>/images/logo.png" width="50" height="50" border="0"><br>回首頁</a></h4>
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

<table>
	<tr>
		<th style="width:50px">編號</th>
		<th style="width:150px">姓名</th>
		<th style="width:100px">帳號</th>
		<th style="width:100px">密碼</th>
		<th style="width:100px">EMAIL</th>
		<th style="width:100px">在職狀況</th>	
	</tr>
		<tr>
			<td>${employeeVO.emp_no}</td>
			<td>${employeeVO.name}</td>
			<td>${employeeVO.acc}</td>
			<c:set var="pwd" value="${employeeVO.pwd}"/>
			<td>${fn:substring(pwd, 0, 1)}********</td>
			<c:set var="email" value="${employeeVO.email}"/>
			<td>${fn:substring(email, 0, 1)}********</td>
			<td>
			<c:if test="${employeeVO.emp_stat == 0}">在職</c:if>
			<c:if test="${employeeVO.emp_stat == 1}">離職</c:if>
			<c:if test="${employeeVO.emp_stat == 2}">留職</c:if>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="emp_no"  value="${employeeVO.emp_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="emp_no"  value="${employeeVO.emp_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
</table>

</body>
</html>