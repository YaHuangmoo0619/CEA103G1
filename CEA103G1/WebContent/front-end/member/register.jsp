<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>



<html>
<head>
<meta charset="BIG5">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>register</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
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
		 <h3>���U����</h3></td><td>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form class="form" method="post" action="<%=request.getContextPath()%>/member/member.do">
<table>
	<tr>
		<td>�b��</td>
		<td><input type="TEXT" name="acc" id="acc" size="45" /></td>
		<input type="hidden" name="action" value="register_Member">
	</tr>
	<tr>
		<td>�K�X</td>
		<td><input type="TEXT" name="pwd" id="pwd" size="45"/></td>
		<input type="hidden" name="action" value="register_Member">
	</tr>
	<tr>
		<td>�T�{�K�X</td>
		<td><input type="TEXT" name="pwd" id="pwd" size="45"/></td>
		<input type="hidden" name="action" value="register_Member">
	</tr>
	<tr>
		<td>�����Ҧr��</td>
		<td><input type="TEXT" name="id" id="pwd" size="45"/></td>
		<input type="hidden" name="action" value="register_Member">
	</tr>
	<tr>
		<td>�m�W</td>
		<td><input type="TEXT" name="name" id="name" size="45"/></td>
		<input type="hidden" name="action" value="register_Member">
	</tr>	
	<tr>
		<td>�ͤ�</td>
		<td><input  name="" id="f_date1" type="text"></td>
		<input type="hidden" name="action" value="register_Member">
	</tr>
	<tr>
		<td>�ʧO</td>
		<td><input  type="radio" name="sex" id="sex" value="1">�k
		<input  type="radio" name="sex" id="sex" value="2">�k</td>
		<input type="hidden" name="action" value="register_Member">
	</tr>
	<tr>
		<td>���</td>
		<td><input type="TEXT" name="mobile" id="mobile" size="45"/></td>
		<input type="hidden" name="action" value="register_Member">
	</tr>
	<tr>
		<td>�q�l�H�c</td>
		<td><input type="TEXT" name="mobile" id="mobile" size="45"/></td>
		<input type="hidden" name="action" value="register_Member">
	</tr>
	<tr>
		<td>�a�}_����<font color=red><b>*</b></font></td>
		<td><select size="1" name="deptno">
			<c:forEach var="deptVO" items="${deptSvc.all}">
				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname}
			</c:forEach>
		</select></td>
		<input type="hidden" name="action" value="register_Member">
	</tr>
	<tr>
		<td>�a�}_�ϰ�<font color=red><b>*</b></font></td>
		<td><select size="1" name="deptno">
			<c:forEach var="deptVO" items="${deptSvc.all}">
				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname}
			</c:forEach>
		</select></td>
		<input type="hidden" name="action" value="register_Member">
	</tr>
	<tr>
		<td>�a�}</td>
		<td><input type="TEXT" name="add" id="add" size="45"/></td>
		<input type="hidden" name="action" value="register_Member">
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="register">
<input type="submit" value="���U"></FORM>
</body>
</html>