<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.product_order.model.*"%>

<%
  Product_orderVO product_orderVO = (Product_orderVO) request.getAttribute("product_orderVO");
%>

<html>
<head>
<title>�ӫ~�q�� - listOneProduct_order.jsp</title>

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
		 <h3>�ӫ~�q�� - ListOneProduct_order.jsp</h3>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product_order/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�ӫ~�q��s��</th>
		<th>�|���s��</th>
		<th>�U�q�ɶ�</th>
		<th>�q�檬�A</th>
		<th>�q���`���B</th>
		<th>�ϥ��I��</th>
		<th>�B�e�覡</th>
		<th>�I�ڤ覡</th>
		<th>�B�e�a�}_����</th>
		<th>�B�e�a�}_�ϰ�</th>
		<th>�B�e�a�}</th>
		<th>�o���Φ�</th>
		<th>�q��Ƶ�</th>
	</tr>
	<tr>
		<td>${product_orderVO.prod_ord_no}</td>
		<td>${product_orderVO.mbr_no}</td>
		<td>${product_orderVO.prod_ord_time}</td>
		<td>${product_orderVO.prod_ord_sum}</td>
		<td>${product_orderVO.used_pt}</td>
		<td>${product_orderVO.ship_meth}</td>
		<td>${product_orderVO.pay_meth}</td>
		<td>${product_orderVO.ship_cty}</td>
		<td>${product_orderVO.ship_dist}</td>
		<td>${product_orderVO.ship_add}</td>
		<td>${product_orderVO.receipt}</td>
		<td>${product_orderVO.rmk}</td>
	</tr>
</table>

</body>
</html>