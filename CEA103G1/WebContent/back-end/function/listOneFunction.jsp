<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.function.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  FunctionVO functionVO = (FunctionVO) request.getAttribute("functionVO"); //FunctionServlet.java(Concroller), 存入req的functionVO物件
%>

<html>
<head>
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<title>權限列表 - listOneFunction.jsp</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<!-- <h4>此頁暫練習採用 Script 的寫法取值:</h4> -->
<table id="table-1">
	<tr><td>
		 <h3>權限列表 - ListOneFunction.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/function/select_page.jsp"><img src="<%=request.getContextPath()%>/images/logo.png" width="50" height="50" border="0"><br>回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>權限編號</th>
		<th>權限名稱</th>
	</tr>
	<tr>
		<td><%=functionVO.getFx_no()%></td>
		<td><%=functionVO.getFx_name()%></td>
	</tr>
</table>

</body>
</html>