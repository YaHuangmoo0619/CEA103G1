<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.product.model.*"%>

<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<title>�ӫ~ - listOneProduct.jsp</title>

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
		 <h3>�ӫ~ - ListOneProduct.jsp</h3>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />

<table>
	<tr>
		<th>�ӫ~�s��</th>
		<th>�ӫ~�����W��</th>
		<th>�ӫ~���A</th>
		<th>�ӫ~�W��</th>
		<th>�ӫ~����</th>
		<th>�ӫ~�w�s</th>
		<th>�ӫ~��T</th>
		<th>�ӫ~�~�P</th>
		<th>�ӫ~�C��</th>
		<th>�ӫ~�j�p</th>
		<th>�B�e�覡</th>
	</tr>
	<tr>
		<td>${productVO.prod_no}</td>
			<td>
			${product_categorySvc.getOneProduct_category(productVO.prod_cat_no).prod_cat_name}
			</td>
			<td>
			<c:if test="${productVO.prod_stat==0}">
				<c:out value="�U�[" />
			</c:if>
			<c:if test="${productVO.prod_stat==1}">
				<c:out value="�W�[" />
			</c:if>
			</td>
			<td>${productVO.prod_name}</td>
			<td>${productVO.prod_pc}</td>
			<td>${productVO.prod_stg}</td>
			<td>${productVO.prod_info}</td>
			<td>${productVO.prod_bnd}</td>
			<td>${productVO.prod_clr}</td>
			<td>${productVO.prod_size}</td>
			<td>
			<c:if test="${productVO.ship_meth==0}">
				<c:out value="�����B�e�覡" />
			</c:if>
			<c:if test="${productVO.ship_meth==1}">
				<c:out value="���v�t" />
			</c:if>
			<c:if test="${productVO.ship_meth==2}">
				<c:out value="���W�Ө��f" />
			</c:if>
			</td>
	</tr>
</table>

</body>
</html>