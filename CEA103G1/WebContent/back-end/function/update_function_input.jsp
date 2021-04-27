<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.function.model.*"%>

<%
  FunctionVO functionVO = (FunctionVO) request.getAttribute("functionVO"); //FunctionServlet.java (Concroller) 存入req的functionVO物件 (包括幫忙取出的functionVO, 也包括輸入資料錯誤時的functionVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<title>權限列表修改 - update_function_input.jsp</title>

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
		 <h3>權限列表修改 - update_function_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/function/select_page.jsp"><img src="<%=request.getContextPath()%>/images/logo.png" width="50" height="50" border="0"><br>回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/function/function.do" name="form1">
<table>
	<tr>
		<td>權限編號:<font color=red><b>*</b></font></td>
		<td><%=functionVO.getFx_no()%></td>
	</tr>
	<tr>
		<td>權限名稱:</td>
		<td><input type="TEXT" name="fx_name" size="45" value="<%=functionVO.getFx_name()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="fx_no" value="<%=functionVO.getFx_no()%>">
<input type="submit" value="送出修改"></FORM>
</body>
</html>