<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%
	ProductService productSvc = new ProductService();
    List<ProductVO> list = productSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>

<head>
<title>�Ҧ��ӫ~ </title>

<%@ include file="/part-of/partOfCampion_COwnerTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_COwnerLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>

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
	width: 800px;
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

<!-- <div style="background-color: #eee;">
	<img src="/CEA103G1/front-images/campionLogoLong.png" class="logo">
	<form class="form-inline my-2 my-lg-0">
		<input class="form-control mr-sm-2" type="search"
			placeholder="���/�ӫ~/�峹" aria-label="Search">
		<button class="btn btn-outline-success my-2 my-sm-0" type="submit">�j�M</button>
	</form>
	<img src="/CEA103G1/front-images/search-circle-outline.svg"
		class="searchIcon" onclick="showSearch()">
	<div class="btn-group" role="group" aria-label="Basic example">
		<button type="button" class="btn btn-secondary">���</button>
		<button type="button" class="btn btn-secondary">�ӫ�</button>
		<button type="button" class="btn btn-secondary">�׾�</button>
	</div>
	<img src="/CEA103G1/front-images/cart-outline.svg" class="cart">
	<div class="btn-group" role="group" aria-label="Basic example">
		<button type="button" class="btn btn-outline-secondary">���U</button>
		<button type="button" class="btn btn-outline-secondary">�n�J</button>
		<button type="button" class="btn btn-outline-secondary">FAQ</button>
		<button type="button" class="btn btn-outline-secondary">�p���ڭ�</button>
	</div>
	<img src="/CEA103G1/front-images/menu-outline.svg" class="menu"
		onclick="showMenu()"> <img
		src="/CEA103G1/front-images/person-circle-outline.svg" class="person">
</div> -->

<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��ӫ~ </h3>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

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
<%-- <%@ include file="page1.file" %> --%>
	<c:forEach var="productVO" items="${list}" > <%-- begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" --%>
		
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
			
			<td>
			  <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="prod_no"  value="${productVO.prod_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="prod_no"  value="${productVO.prod_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

<%-- <%@ include file="page2.file" %> --%> 

</body>
</html>