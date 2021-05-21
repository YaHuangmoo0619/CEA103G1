<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.employee.model.*"%>
<jsp:useBean id="product_pictureSvc" class="com.product_picture.model.Product_pictureService"/>
<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />

<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>單一商品 </title>
<%@ include file="/part-of/partOfCampion_backTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_backLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>

<style>
body {
	background-color: #4e5452;
	color: #4e5452;
}

div.left {
	margin-top: 20px;
}

div.right {
	background-color: #fff;
	margin-top: 40px;
	padding: 50px 50px;
	border-radius: 5px;
	overflow:scroll;
}

a.content {
	color: #80c344;
	font-size: 0.6em;
}

a.content:hover {
	color: #4B7F52;
}

input.confirm {
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}

input.confirm:hover {
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}

#confirmTop:hover {
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}

div.forSearch{
	margin: 0 auto;
	width: 70%;
	hieght: 50px;
	position: relative;
}
div.forSearchMore{
	top: 110%;
	left: 15%;
	width: 70%;
	position: absolute;
	background-color: #fff;
	box-shadow: 0 1px 5px 0 #4e5452;
	display: none
}

#mail_cont{
	border-radius:5px;
	background-color:#eee;
	border:none;
	padding:5px 15px;
	width:50%;
}

span{
	 font-size:0.8em;
	 font-weight:444;
	 padding: 7px;
	 background-color: #eee;
	 border-radius:5px;
}
span:hover{
	cursor: pointer;
	background-color: #4e5452;
	color: #eee;
}

label, select, input {
	font-size: 0.8em;
}

table {
	width: 700px;
	margin: 30px auto;
	/* 	border: 1px solid #4e5452; */
}

th, td {
	text-align: left;
	/* 	border: 1px solid #4e5452; */
	padding: 10px 15px;
}

td.function {
	text-align: justify;
}

label.spotlight {
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}

input.change {
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}

input.change:hover {
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}

#focus {
	margin-right: -5px;
}

tr {
/* 	border-top: 1px solid #eee; */
	border-bottom: 2px solid #eee;
}

tr:hover {
	box-shadow: 0 1px 5px 0 #4e5452 inset;
/* 	cursor: pointer; */
}

img.inDiv{
	width:50px;
}
div.innerDiv{
 	display:inline;
}
hr{
	margin: 3px;
	width: 30%;
	border-color:#80c344;
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
<body>
<%@ include file="/part-of/partOfCampion_backTop_body.txt"%>
	<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
	<div class="container">
		<div class="row">
			<div class="left col-3">
				<%@ include file="/part-of/partOfCampion_backLeft_body.txt"%></div>
			<div class="right col-9">
		 <h3>所有商品列表&nbsp;
			<a class="content" href="<%=request.getContextPath()%>/back-end/product/listAllProduct.jsp">回到商品列表</a>
		</h3>
<table>
	<tr>
		<th>商品編號</th>
		<th>商品分類名稱</th>
		<th>商品狀態</th>
		<th>商品名稱</th>
		<th>商品價格</th>
		<th>商品庫存</th>
		<th>商品資訊</th>
		<th>商品品牌</th>
		<th>商品顏色</th>
		<th>商品大小</th>
		<th>運送方式</th>
	</tr>
	<tr>
		<td>${productVO.prod_no}</td>
			<td>
			${product_categorySvc.getOneProduct_category(productVO.prod_cat_no).prod_cat_name}
			</td>
			<td>
			<c:if test="${productVO.prod_stat==0}">
				<c:out value="下架" />
			</c:if>
			<c:if test="${productVO.prod_stat==1}">
				<c:out value="上架" />
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
				<c:out value="不限運送方式" />
			</c:if>
			<c:if test="${productVO.ship_meth==1}">
				<c:out value="限宅配" />
			</c:if>
			<c:if test="${productVO.ship_meth==2}">
				<c:out value="限超商取貨" />
			</c:if>
			</td>
	</tr>
</table>
</div>
</div>
</div>
</html>