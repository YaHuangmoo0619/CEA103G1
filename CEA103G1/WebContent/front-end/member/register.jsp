<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>


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
		 <h3>註冊測試</h3></td><td>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method="post" action="<%=request.getContextPath()%>/member/member.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>帳號</td>
		<td><input type="TEXT" name="acc" id="acc" size="45" value="<%= (memberVO == null)? "" : memberVO.getAcc()%>"/></td>
	</tr>
	<tr>
		<td>密碼</td>
		<td><input type="password" name="pwd" id="pwd" size="45" value="<%= (memberVO == null)? "" : memberVO.getPwd()%>"/></td>
	</tr>
	<!--
	<tr>
		<td>確認密碼</td>
		<td><input type="password" name="pwd" id="pwd" size="45" value="<%= (memberVO == null)? "" : memberVO.getPwd()%>"/></td>
	</tr>
	-->  
	<tr>
		<td>身分證字號</td>
		<td><input type="TEXT" name="id" id="pwd" size="45" value="<%= (memberVO == null)? "" : memberVO.getId()%>"/></td>
	</tr>
	<tr>
		<td>姓名</td>
		<td><input type="TEXT" name="name" id="name" size="45" value="<%= (memberVO == null)? "" : memberVO.getName()%>"/></td>
	</tr>	
	<tr>
		<td>生日</td>
		<td>
		<c:if test="${memberVO == null || memberVO.bday == null}">
			<input name="bday" id="birdthday" type="text" placeholder="1990-01-01">
		</c:if>
		<c:if test="${memberVO != null && memberVO.bday != null}">
			<input name="bday" id="birdthday" type="text" value="${memberVO.bday}">
		</c:if>
		</td>
	</tr>
	<tr>
		<td>性別</td>
		<td onclick="noChecked(this)">
			<c:if test="${memberVO.getSex() == null}">
				<input type="radio" id="state1" name="sex" value="1">
				<label for="state1">男</label>
				<input type="radio" id="state1" name="sex" value="2">
				<label for="state2">女</label>
			</c:if>
			<c:if test="${memberVO.getSex() == 1}">
				<input type="radio" id="state1" name="sex" value="1" checked>
				<label for="state1">男</label>
				<input type="radio" id="state1" name="sex" value="2">
				<label for="state2">女</label>
			</c:if>
			<c:if test="${memberVO.getSex() == 2}">
				<input type="radio" id="state1" name="sex" value="1">
				<label for="state1">男</label>
				<input type="radio" id="state1" name="sex" value="2" checked>
				<label for="state2">女</label>
			</c:if>
		</td>
	</tr>
	<tr>
		<td>手機</td>
		<td><input type="TEXT" name="mobile" id="mobile" size="45" value="<%= (memberVO == null)? "" : memberVO.getMobile()%>"/></td>
	</tr>
	<tr>
		<td>電子信箱</td>
		<td><input type="TEXT" name="mail" id="mail" size="45" value="<%= (memberVO == null)? "" : memberVO.getMail()%>"/></td>
	</tr>
	<tr>
		<td>地址_縣市</td>
		<td><input type="TEXT" name="city" id="city" size="45" value="<%= (memberVO == null)? "" : memberVO.getCity()%>"/></td>
	</tr>
	<tr>
		<td>地址_區域</td>
		<td><input type="TEXT" name="dist" id="dist" size="45" value="<%= (memberVO == null)? "" : memberVO.getDist()%>"/></td>
	</tr>
	<tr>
		<td>地址</td>
		<td><input type="TEXT" name="add" id="add" size="45" value="<%= (memberVO == null)? "" : memberVO.getAdd()%>"/></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="register_Member">
<input type="submit" value="註冊"></FORM>

<script>
$.datetimepicker.setLocale('zh');
$(function(){
	 $('#announce_date').datetimepicker({
	  format:'Y-m-d',
	  minDate:'-1970/01/01',
	  timepicker:false
	 });

});
</script>

</body>
</html>