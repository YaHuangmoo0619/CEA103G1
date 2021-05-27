<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.member.model.*" %>

<%
    Product_orderService product_orderSvc = new Product_orderService();
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	List<Product_orderVO> list = product_orderSvc.getByMbr(memberVO.getMbr_no());
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<title>�Ҧ��ӫ~�q�� </title>
<%@ include file="/part-of/partOfCampion_frontTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_frontFooter_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_frontTop_js.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>

<style>
.confirm {
	margin: 2px;
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
	background-color: #80c344;
}

.confirm:hover {
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}
#cancel {
	margin: 2px;
	background-color: #FF0000;
	color: #880000;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
	background-color: #FF3333;
}

#cancel:hover {
	background-color: #AA0000;
	color: #FF0000;
	cursor: pointer;
}
table{
  width:100%;
  table-layout: fixed;
}
.tbl-header{
  background-color: rgba(255,255,255,0.3);
 }
.tbl-content{
  height:300px;
  overflow-x:auto;
  margin-top: 0px;
  border: 1px solid rgba(255,255,255,0.3);
}
th{
  padding: 20px 15px;
  text-align: left;
  font-weight: 500;
  font-size: 14px;
/*   color: #fff; */
  text-transform: uppercase;
}
td{
  padding: 15px;
  text-align: left;
  vertical-align:middle;
  font-weight: 300;
  font-size: 13px;
/*   color: #fff; */
/*   border-bottom: solid 1px rgba(255,255,255,0.1); */
  border-bottom: solid 1px #80c344;
}
section{
  margin: 50px;
}
.camp:hover {
	background-color: #F0FFF0;
}

.camp {
	margin: 0px;
}

div.left {
	margin-top: 20px;
}

div.right {
/* 	background-color: #fff; */
	margin-top: 20px;
	/* 	padding: 50px 50px; */
	border-radius: 5px;
}

html, body {
	margin: 0;
	padding: 0;
	/*background-color: #4e5452;*/
/* 	background-color: #fff; */
	color: #4B7F52;
}

section {
	text-align: center;
}

img.logo {
	width: 100px;
	margin: 10px;
	margin-left: 100px;
}

a {
	text-decoration: none;
}

a:hover {
	text-decoration: none;
}

form.form-inline {
	display: inline;
	border: none;
}

img.searchIcon {
	display: none;
}

img.cart {
	width: 30px;
	margin: 10px;
}

img.cart:hover {
	cursor: pointer;
}

img.menu {
	width: 40px;
	margin: 10px;
	display: none;
}

img.menu:hover {
	cursor: pointer;
}

img.person {
	width: 40px;
	margin: 10px;
}

img.person:hover {
	cursor: pointer;
}

@media screen and (max-width: 575px) {
	container {
		width: 100%;
	}
	form.form-inline {
		display: none;
	}
	form.secSearch {
		display: none;
	}
	img.searchIcon {
		display: inline;
		width: 30px;
		margin: 0px;
	}
	img.searchIcon:hover {
		cursor: pointer;
	}
	div.btn-group {
		display: none;
	}
	img.menu {
		width: 30px;
		margin: 10px;
		display: inline-block;
	}
	div.sec {
		display: none;
		background-color: #eee;
	}
	div.where {
		display: block;
		padding: 5px 100px;
		text-align: left;
	}
	div.slogan h1 {
		color: #fff;
		font-size: 2em;
		font-weight: 999;
		margin: 50px auto;
	}
	div.photo {
		width: 200px;
		height: 100px;
		margin: 20px auto;
		background-color: #eee;
		overflow: hidden;
	}
	div.row {
		margin-top: 0px;
	}
	div.article {
		width: 90%;
		margin: 20px auto;
		text-align: left;
		background-color: #eee;
		border-radius: 5px;
		padding: 10px 50px 20px 50px;
	}
	div.more {
		display: inline;
	}
	section.footer {
		background-color: #4B7F52;
		color: #80c344;
		height: 100px;
		padding-top: 30px;
	}
}

@media ( min-width : 576px) and (max-width: 767px) {
	container {
		width: 540px;
		margin: 0px auto;
	}
	form.form-inline {
		display: none;
	}
	form.secSearch {
		display: none;
	}
	img.searchIcon {
		display: inline;
		width: 30px;
		margin: 0px;
	}
	img.searchIcon:hover {
		cursor: pointer;
	}
	div.btn-group {
		display: none;
	}
	img.menu {
		width: 30px;
		margin: 10px;
		display: inline-block;
	}
	div.sec {
		display: none;
		background-color: #eee;
	}
	div.where {
		display: block;
		padding: 5px 130px;
		text-align: left;
	}
}

@media ( min-width : 768px) and (max-width: 991px) {
	container {
		width: 720px;
		margin: 0px auto;
	}
	form.form-inline {
		display: none;
	}
	form.secSearch {
		display: none;
	}
	img.searchIcon {
		display: inline;
		width: 30px;
		margin: 0px;
	}
	img.searchIcon:hover {
		cursor: pointer;
	}
	div.btn-group {
		display: none;
	}
	img.menu {
		width: 30px;
		margin: 10px;
		display: inline-block;
	}
	div.sec {
		display: none;
		background-color: #fff;
	}
	div.where {
		display: block;
		padding: 5px 220px;
		text-align: left;
	}
	div.photo {
		width: 300px;
		height: 150px;
		margin: 20px auto;
		background-color: #eee;
		overflow: hidden;
	}
}

@media ( min-width : 992px) and (max-width: 1199px) {
	container {
		width: 960px;
		margin: 0px auto;
	}
	div.menuForButton {
		display: none;
	}
	div.forSearch {
		display: none;
	}
	div.photo {
		width: 400px;
		height: 200px;
		margin: 20px auto;
		background-color: #fff;
		overflow: hidden;
	}
}

@media ( min-width : 1200px) {
	container {
		width: 1140px;
		margin: 0px auto;
	}
	div.menuForButton {
		display: none;
	}
	div.forSearch {
		display: none;
	}
	div.photo {
		width: 400px;
		height: 200px;
		margin: 20px auto;
		background-color: #fff;
		overflow: hidden;
	}
}
</style>

</head>
<body onload="connection()" bgcolor='white'>
<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>


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
		 <h1>�Ҧ��ӫ~�q�� </h1>
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

<table>

	<tr>
		<th>�ӫ~�q��s��</th>
		<th>�U�q�ɶ�</th>
		<th>�q�檬�A</th>
		<th>�q���`���B</th>
		<th>�B�e�a�}</th>
	</tr>
<%-- <%@ include file="page1.file" %> --%>
	<c:forEach var="product_orderVO" items="${list}" > <%-- begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" --%>
		
		
		<tr>
			<td>${product_orderVO.prod_ord_no}</td>
			<td>${product_orderVO.prod_ord_time}</td>
			<td>
			<c:if test="${product_orderVO.prod_ord_stat==0}">
				<c:out value="���I��" />
			</c:if>
			<c:if test="${product_orderVO.prod_ord_stat==1}">
				<c:out value="�w�I��" />
			</c:if>
			<c:if test="${product_orderVO.prod_ord_stat==2}">
				<c:out value="�X�f��" />
			</c:if>
			<c:if test="${product_orderVO.prod_ord_stat==3}">
				<c:out value="�w���f" />
			</c:if>
			<c:if test="${product_orderVO.prod_ord_stat==4}">
				<c:out value="�����f" />
			</c:if>
			</td>
			<td>${product_orderVO.prod_ord_sum}</td>
			<td>${product_orderVO.ship_add}</td>
			<td>
			  <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order/product_order.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�d�ݭq��Ա�" class="btn btn-white-outline display-4">
			     <input type="hidden" name="prod_ord_no"  value="${product_orderVO.prod_ord_no}">
			     <input type="hidden" name="action"	value="getOne_For_Display">
			  </FORM>
			</td>
		</tr>
	</c:forEach>
	
</table>
<%-- <%@ include file="page2.file" %> --%> 
<%@ include file="/part-of/partOfCampion_frontFooter_body.txt"%>
</body>
</html>