<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.product_category.model.*"%>

<%
  Product_categoryVO product_categoryVO = (Product_categoryVO) request.getAttribute("product_categoryVO");
%>

<html>
<head>
<title>商品分類 - listOneProduct_category.jsp</title>

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
		 <h3>商品分類 - ListOneProduct_category.jsp</h3>
		 <h4><a href="${pageContext.request.contextPath}/back-end/product_category/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品分類編號</th>
		<th>商品分類名稱</th>
	</tr>
	<tr>
		<td><%=product_categoryVO.getProd_cat_no()%></td>
		<td><%=product_categoryVO.getProd_cat_name()%></td>
	</tr>
</table>

</body>
</html>