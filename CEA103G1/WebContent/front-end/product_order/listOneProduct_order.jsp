<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>
<%@ page import="com.product_order_details.model.*"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*" %>

<jsp:useBean id="product_pictureSvc" class="com.product_picture.model.Product_pictureService"/>
<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />
<jsp:useBean id="product_order_detailsSvc" class="com.product_order_details.model.Product_order_detailsService"/>
<jsp:useBean id="product_orderSvc" class="com.product_order.model.Product_orderService"/>
<jsp:useBean id="productSvc" class="com.product.model.ProductService"/>

<%
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	if(memberVO == null){
		response.sendRedirect(request.getContextPath()+"/campion_front_login.jsp");
		return;
	}
	Product_orderVO product_orderVO = (Product_orderVO) request.getAttribute("product_orderVO");
%>

<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>${memberVO.name}之編號${product_orderVO.prod_ord_no}商品訂單</title>
<%@ include file="/part-of/partOfCampion_frontTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_frontFooter_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_frontTop_js.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>

<style>
html, body {
	margin: 0;
	padding: 0;
/* 	background: #fff; */
	background-image: linear-gradient(rgba(255,255,255,.9), rgba(255,255,255,.9)), url("/CEA103G1/front-images/ShopFront.jpg") ;
	background-size: 100%;
	background-attachment: fixed;
	color: #4B7F52;
}
.content {
    color: #80c344;
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

label{
	display:none;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}

table {
	width: 700px;
	margin: 30px auto;
	/* 	border: 1px solid #4e5452; */
}

th, td {
	text-align: left;
	/* 	border: 1px solid #4e5452; */
	padding: 10px 10px;
}

td.function {
	text-align: justify;
}

label.spotlight {
	background-color: #80c344;
	display:block;
}

label.warn {
	background-color: red;
	display:block;
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
 	cursor: pointer;
}

img.inDiv{
	width:50px;
	margin:1px;
}
div.innerDiv{
 	display:inline;
}
hr{
	margin: 3px;
	width: 30%;
	border-color:#80c344;
}
div.L{
	display:inline-block;
	width:6em;
	font-weight: 555;
}
div.R{
	display:inline-block;
	width:6em;
}
div.inTr{
	display:inline-block;
}
label.spotlight{
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}
div.out{
	width:90%;
	margin: 0 auto;
}
label.stat{
	margin-left:3em;
	font-size:0.9em;
}
div.prod {
	border-bottom: 2px solid #eee;
}
div.prod:hover {
	box-shadow: 0 1px 5px 0 #4e5452 inset;
 	cursor: pointer;
}}
._btn {
  display: inline-block;
  background-color: #bdc3c7;
  border: none;
  padding: .5em .75em;
  text-align: center;
  font-weight: 300;
}

._btn:hover,
.cart-totals:hover ._btn {
  background-color: #3498db;
  color: #ecf0f1;

</style>
</head>
<body onload="connection()" bgcolor='white'>
<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
	<div class="out">
		<div style="display:flex;">
			<div style="width:10%;" class="inTr">
				${product_orderVO.prod_ord_no}號訂單
			</div>
			<div style="width:30%;" class="inTr">
			<div class="inTr">
			<div class="L">訂單總金額:</div>
			<div class="R">${product_orderVO.prod_ord_sum}元</div>
			</div>
			<div class="inTr">
				<div class="L">使用點數:</div>
				<div class="R">${product_orderVO.used_pt}</div>
			</div>
				<div class="inTr">
					<div class="L">運送方式:</div>
					<div class="R">
						<c:if test="${product_orderVO.ship_meth==1}">
							<c:out value="宅配" />
						</c:if>
						<c:if test="${product_orderVO.ship_meth==2}">
							<c:out value="超商取貨" />
						</c:if>
					</div>
				</div>
				<div class="inTr">
					<div class="L">運送地址:</div>
					<div>
						<div class="innerDiv">${product_orderVO.ship_cty}${product_orderVO.ship_dist}${product_orderVO.ship_add}</div>
					</div>
				</div>
				<div class="inTr">
					<div class="L">付款方式:</div>
					<div class="R">
						<c:if test="${product_orderVO.pay_meth==0}">
							<c:out value="信用卡" />
						</c:if>
						<c:if test="${product_orderVO.pay_meth==1}">
							<c:out value="匯款" />
						</c:if>
						<c:if test="${product_orderVO.pay_meth==2}">
							<c:out value="超商取貨付款" />
						</c:if>
					</div>
				</div>
				<div class="inTr">
					<div class="L">發票形式:</div>
					<div class="R">
						<c:if test="${product_orderVO.receipt==0}">
							<c:out value="紙本發票" />
						</c:if>
						<c:if test="${product_orderVO.receipt==1}">
							<c:out value="電子發票" />
						</c:if>
						<c:if test="${product_orderVO.receipt==2}">
							<c:out value="發票捐贈" />
						</c:if>
					</div>
				</div>	
			</div>
			
			<div style="width:55%;" class="inTr">
					<div class="inTr">
					<div class="L">訂單備註:</div>
					<div style="display:inline-block;width:8em;">${product_orderVO.rmk}</div>
				</div>
			</div>
			<div class="inTr">
				<div class="L">下訂時間:</div>
<%-- 				<c:set var="prod_ord_time" value="${product_orderVO.prod_ord_time}" /> --%>
<%-- 				<div>${fn:substring(prod_ord_time, 0, 19)}</div> --%>
				<div><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${product_orderVO.prod_ord_time}"/></div>
				<div class="inTr">
					<label for="prod_ord_stat0" ${product_orderVO.prod_ord_stat==0?'class="spotlight"':'hide'}>未付款</label>
					<label for="prod_ord_stat1" ${product_orderVO.prod_ord_stat==1?'class="spotlight"':'hide'}>已付款</label>
					<label for="prod_ord_stat2" ${product_orderVO.prod_ord_stat==2?'class="spotlight"':'hide'}>出貨中</label>
					<label for="prod_ord_stat3" ${product_orderVO.prod_ord_stat==3?'class="spotlight"':'hide'}>已收貨</label>
					<label for="prod_ord_stat4" ${product_orderVO.prod_ord_stat==4?'class="warn"':'hide'}>未取貨</label>
					<label for="prod_ord_stat5" ${product_orderVO.prod_ord_stat==5?'class="warn"':'hide'}>訂單取消</label>
				<c:if test="${product_orderVO.prod_ord_stat<=1}">
				<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order/product_order.do" style="margin-bottom: 0px;">
				    <input type="submit" value="取消訂單" class="btn btn-white-outline display-4" style="color: red;">
				    <input type="hidden" name="prod_ord_no" value="${product_orderVO.prod_ord_no}">
				    <input type="hidden" name="action" value="cancel">
			   	</FORM>
			   </c:if>
				</div>
			</div>	
		</div>
		<div class="inTr">
		<c:forEach var="product_order_detailsVO" items="${product_order_detailsSvc.getByProd_ord_no(product_orderVO.getProd_ord_no())}">
		<hr style="margin: 20 auto;">
		<div id="prod" onclick="location='${pageContext.request.contextPath}/product/product.do?action=getOne_For_Display&prod_no=${product_order_detailsVO.getProd_no()}'">
			<div class="innerDiv">
			<img style="width:150px; height:100px;" src="${product_pictureSvc.getOneProduct_picture(product_order_detailsVO.getProd_no()).getProd_pic()}">
			</div>
			<div class="innerDiv L">商品名稱:</div>
			<div class="innerDiv" style="margin-right:1em;">${productSvc.getOneProduct(product_order_detailsVO.getProd_no()).getProd_name()}</div>
			<div class="innerDiv L">商品數量:</div>
			<div class="innerDiv" style="margin-right:1em;">${product_order_detailsVO.getProd_amt()}件</div>
			<div class="innerDiv L">商品單價:</div>
			<div class="innerDiv" style="margin-right:1em;">${product_order_detailsVO.getProd_unit_pc()}元</div>
		</div>
		</c:forEach>
		<hr style="margin: 20 auto;">
		</div>
	</div>
</body>

</html>