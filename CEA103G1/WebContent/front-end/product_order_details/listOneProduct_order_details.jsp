<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.product_order_details.model.*"%>

<%
  Product_order_detailsVO product_order_detailsVO = (Product_order_detailsVO) request.getAttribute("product_order_detailsVO");
%>

<html>
<head>
<title>商品訂單明細 - listOneProduct_order_details.jsp</title>

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
		 <h3>商品訂單明細 - ListOneProduct_order_details.jsp</h3>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product_order_details/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品訂單明細編號</th>
		<th>商品編號</th>
	</tr>
	<tr>
		<td><%=product_order_detailsVO.getProd_ord_no()%></td>
		<td><%=product_order_detailsVO.getProd_no()%></td>
	</tr>
</table>

</body>
</html>