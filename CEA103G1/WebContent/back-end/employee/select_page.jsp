<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*" %>
<%@ page import="com.function.model.*" %>
<%@ page import="java.util.*" %>

<% 
EmployeeService employeeSrv = new EmployeeService();
List<EmployeeVO> list = employeeSrv.getAll();
Integer lastEmp_no = list.get(list.size()-1).getEmp_no();
request.setAttribute("lastEmp_no", lastEmp_no);
%>
    
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<title>Employee: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #FFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>
<table id="table-1">
   <tr><td><h3>Employee: Home</h3><img src="<%=request.getContextPath()%>/images/logo.png" width="50" height="50" border="0"><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Employee: Home</p>

<h3>��Ƭd��:</h3>

<%-- ���~��C --%>
<c:if test="${not empty  errorMsgs}">
	<font style="color:red">�Эץ��H�U���~�G</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<ul>
	<li><a href='<%=request.getContextPath()%>/back-end/employee/listAllEmployee.jsp'>List</a> all Employees.  <br><br></li>
	<li>
	    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" >
	        <b>��J�����޲z���s�� (�p90002):</b>
	        <input type="text" name="emp_no" placeholder="90002��${lastEmp_no}�����Ʀr">
	        <input type="hidden" name="action" value="getOne_For_Display">
	        <input type="submit" value="�e�X">
	    </FORM>
	</li>

<jsp:useBean id="employeeSvc" scope="page" class="com.employee.model.EmployeeService" />
	
	<li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" >
       <b>��ܺ����޲z���s��:</b>
       <select size="1" name="emp_no">
       			<option value= 0>--�п��--
         <c:forEach var="employeeVO" items="${employeeSvc.all}" > 
         	<c:if test="${employeeVO.emp_no != 90001}">
          		<option value="${employeeVO.emp_no}">${employeeVO.emp_no}
          	</c:if>
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  	</li>
	
	<li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" >
       <b>��ܺ����޲z���m�W:</b>
       <select size="1" name="name">
       			<option value= 0>--�п��--
         <c:forEach var="employeeVO" items="${employeeSvc.all}" > 
         	<c:if test="${employeeVO.emp_no != 90001}">
          		<option value="${employeeVO.name}">${employeeVO.name}
          	</c:if>
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getName_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>

 <jsp:useBean id="functionSvc" scope="page" class="com.function.model.FunctionService" />

	<li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" >
       <b>����v���W��:</b>
       <select size="1" name="fx_no">
       		<option value= 0 >--�п��--
         <c:forEach var="functionVO" items="${functionSvc.all}" > 
          	<option value="${functionVO.fx_no}">${functionVO.fx_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getFunction_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�s�W�����޲z��</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/employee/addEmployee.jsp'>Add</a> a new Employee.</li>
</ul>

</body>
</html>