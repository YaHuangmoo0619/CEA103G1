<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*" %>
<%@ page import="java.util.*" %>

<%
	EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");
	EmployeeService employeeSvc = new EmployeeService();
	LinkedList<EmployeeVO> employeeVOLink = (LinkedList<EmployeeVO>)employeeSvc.getAll();
	EmployeeVO employeeLast = employeeVOLink.getLast();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<title>新增網站管理員 - addEmployee.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }

</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>新增網站管理員 - addEmployee.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/employee/select_page.jsp"><img src="<%=request.getContextPath()%>/images/logo.png" width="50" height="50" border="0"><br>回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" name="form1">
<table>
	<tr>
		<td>姓名:</td>
		<td><input type="TEXT" name="name" size="30" placeholder="請輸入姓名"
			 value="<%= (employeeVO == null)? "" : employeeVO.getName()%>" /></td>
	</tr>
	<tr>
		<td>帳號:</td>
		<td>CEA<input type="TEXT" name="acc" size="25"
			 value="<%= (employeeVO == null)? employeeLast.getEmp_no()+1 : employeeVO.getAcc().substring(3)%>" /></td>
	</tr>
	<tr>
		<td>EMAIL:</td>
		<td><input type="TEXT" name="email" size="40" placeholder="請輸入EMAIL"
			 value="<%= (employeeVO == null)? "" : employeeVO.getEmail()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>



</body>
</html>