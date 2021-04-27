<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
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
<title>�ק�����޲z����� - updateEmployee.jsp</title>

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
		 <h3>�ק�����޲z����� - updateEmployee.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/employee/select_page.jsp"><img src="<%=request.getContextPath()%>/images/logo.png" width="50" height="50" border="0"><br>�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" name="form1">
<table>
	<tr>
		<td>�m�W:</td>
		<td><input type="TEXT" name="name" size="30" placeholder="�п�J�m�W"
			 value="<%= (employeeVO == null)? "" : employeeVO.getName()%>" /></td>
	</tr>
	<tr>
		<td>�b��:</td>
		<td>CEA<input type="TEXT" name="acc" size="25"
			 value="<%= (employeeVO == null)? employeeLast.getEmp_no()+1 : employeeVO.getAcc().substring(3)%>" /></td>
	</tr>
	<tr>
		<td>EMAIL:</td>
		<td><input type="TEXT" name="email" size="40" placeholder="�п�JEMAIL"
			 value="<%= (employeeVO == null)? "" : employeeVO.getEmail()%>" /></td>
	</tr>
	<tr>
		<td>�b¾���p:</td>
		<td onclick="noChecked(this)">
			<c:if test="${employeeVO.getEmp_stat() == null}">
				<input type="radio" id="state0" name="emp_stat" value="0">
				<label for="state0">�b¾</label><br>
				<input type="radio" id="state1" name="emp_stat" value="1">
				<label for="state1">��¾</label><br>
				<input type="radio" id="state2" name="emp_stat" value="2">
				<label for="state2">�d¾</label>
			</c:if>
			<c:if test="${employeeVO.getEmp_stat() == 0}">
				<input type="radio" id="state0" name="emp_stat" value="0" checked>
				<label for="state0">�b¾</label><br>
				<input type="radio" id="state1" name="emp_stat" value="1">
				<label for="state1">��¾</label><br>
				<input type="radio" id="state2" name="emp_stat" value="2">
				<label for="state2">�d¾</label>
			</c:if>
			<c:if test="${employeeVO.getEmp_stat() == 1}">
				<input type="radio" id="state0" name="emp_stat" value="0">
				<label for="state0">�b¾</label><br>
				<input type="radio" id="state1" name="emp_stat" value="1" checked>
				<label for="state1">��¾</label><br>
				<input type="radio" id="state2" name="emp_stat" value="2">
				<label for="state2">�d¾</label>
			</c:if>
			<c:if test="${employeeVO.getEmp_stat() == 2}">
				<input type="radio" id="state0" name="emp_stat" value="0">
				<label for="state0">�b¾</label><br>
				<input type="radio" id="state1" name="emp_stat" value="1">
				<label for="state1">��¾</label><br>
				<input type="radio" id="state2" name="emp_stat" value="2" checked>
				<label for="state2">�d¾</label>
			</c:if>			
		</td>			  
	</tr>
	<tr>
		<td>�K�X��s:</td>
		<td>
			<c:if test="${code == null}">
		 		<input type="TEXT" name="pwd" value="${employeeVO.pwd}">
		 	</c:if>
		 	<c:if test="${code != null}">
		 		<input type="TEXT" name="pwd" value="${code}">
		 	</c:if>
		</td>	 
	</tr>
</table>
<br>
<input type="hidden" name="emp_no" value="<%=employeeVO.getEmp_no()%>">
<input type="hidden" name="pwd" value="<%=employeeVO.getPwd()%>">
<input type="hidden" name="action" value="update">
<input type="submit" name="change" value="�K�X��s">
<input type="submit" value="�e�X�ק�"></FORM>

<script>
function noChecked(e){
	document.getElementById("state0").removeAttribute("checked");
	document.getElementById("state1").removeAttribute("checked");
	document.getElementById("state2").removeAttribute("checked");
}
</script>
</body>
</html>