<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.product_report.model.*"%>

<%
  Product_reportVO product_reportVO = (Product_reportVO) request.getAttribute("product_reportVO");
%>

<html>
<head>
<title>商品回報 - listOneProduct_report.jsp</title>

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

<table id="table-1">
	<tr><td>
		 <h3>商品回報 - ListOneProduct_report.jsp</h3>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product_report/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品回報編號</th>
		<th>會員編號</th>
	</tr>
	<tr>
		<td><%=product_reportVO.getProd_rpt_no()%></td>
		<td><%=product_reportVO.getMbr_no()%></td>
	</tr>
</table>

</body>
</html>