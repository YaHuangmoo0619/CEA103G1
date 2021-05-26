<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.member.model.*" %>

<%
 	Product_orderVO product_orderVO = (Product_orderVO) request.getAttribute("product_orderVO");
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
%>

<html>
<head>
<title>商品訂單 - listOneProduct_order.jsp</title>

<%@ include file="/part-of/partOfCampion_COwnerTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_COwnerLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>

<link rel="icon" href="/CEA103G1/images/campionLogoIcon.png"
	type="image/png">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
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
<body bgcolor='white'>
<section>
	編號：${product_orderVO.prod_ord_no}號訂單
</section>
<table>
	<tr>
		<th>下訂時間</th>
		<th>訂單狀態</th>
		<th>訂單總金額</th>
		<th>使用點數</th>
		<th>運送方式</th>
		<th>付款方式</th>
		<th>運送地址_縣市</th>
		<th>運送地址_區域</th>
		<th>運送地址</th>
		<th>發票形式</th>
		<th>訂單備註</th>
	</tr>
	<tr>
		<td>${product_orderVO.prod_ord_time}</td>
		<td>
		<c:if test="${product_orderVO.prod_ord_stat==0}">
			<c:out value="未付款" />
		</c:if>
		<c:if test="${product_orderVO.prod_ord_stat==1}">
			<c:out value="已付款" />
		</c:if>
		<c:if test="${product_orderVO.prod_ord_stat==2}">
			<c:out value="出貨中" />
		</c:if>
		<c:if test="${product_orderVO.prod_ord_stat==3}">
			<c:out value="已收貨" />
		</c:if>
		<c:if test="${product_orderVO.prod_ord_stat==4}">
			<c:out value="未取貨" />
		</c:if>
		</td>
		<td>${product_orderVO.prod_ord_sum}</td>
		<td>${product_orderVO.used_pt}</td>
		<td>
		<c:if test="${product_orderVO.ship_meth==1}">
			<c:out value="宅配" />
		</c:if>
		<c:if test="${product_orderVO.ship_meth==2}">
			<c:out value="超商取貨" />
		</c:if>
		</td>
		<td>
		<c:if test="${product_orderVO.pay_meth==0}">
			<c:out value="信用卡" />
			</c:if>
		<c:if test="${product_orderVO.pay_meth==1}">
			<c:out value="匯款" />
		</c:if>
		<c:if test="${product_orderVO.pay_meth==2}">
			<c:out value="超商取貨付款" />
		</c:if>
		</td>
		<td>${product_orderVO.ship_cty}</td>
		<td>${product_orderVO.ship_dist}</td>
		<td>${product_orderVO.ship_add}</td>
		<td>
		<c:if test="${product_orderVO.receipt==0}">
			<c:out value="紙本發票" />
		</c:if>
		<c:if test="${product_orderVO.receipt==1}">
			<c:out value="電子發票" />
		</c:if>
		<c:if test="${product_orderVO.receipt==2}">
			<c:out value="發票捐贈" />
		</c:if>
		</td>
		<td>${product_orderVO.rmk}</td>
		<td>
		<td>
		<c:if test="${product_orderVO.prod_ord_stat<=1}">
		<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order/product_order.do" style="margin-bottom: 0px;">
		    <input type="submit" value="退貨" class="btn btn-white-outline display-4">
	   </FORM>
	   </c:if>
		</td>
</table>

</body>
</html>