<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.member.model.*" %>
<jsp:useBean id="product_pictureSvc" class="com.product_picture.model.Product_pictureService"/>
<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />

<%
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	if(memberVO == null){
		response.sendRedirect(request.getContextPath()+"/campion_front_login.jsp");
		return;
	}
    Product_orderService product_orderSvc = new Product_orderService();
	List<Product_orderVO> list = product_orderSvc.getByMbr(memberVO.getMbr_no());
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>${memberVO.name}之商品訂單 </title>
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
</style>

</head>
<body onload="connection()">
<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
<h3>商品訂單列表&nbsp;
	<a class="content" href="<%=request.getContextPath()%>/front-end/member/viewMember.jsp">回到會員首頁</a>
</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>

	<c:forEach var="product_orderVO" items="${list}" >
		<tr ${product_orderVO.prod_ord_no==param.prod_ord_no ? 'bgcolor=#eee' : '' }>
			<td style="width:15%;">
				${product_orderVO.prod_ord_no}
				${product_orderVO.prod_ord_no==param.prod_ord_no ? '<a id="focus"></a>' : '' }
			</td>
			<td style="width:30%;">
				<div class="inTr">
					<div class="L"><strong>訂單總金額:</strong></div>
					<div class="R">${product_orderVO.prod_ord_sum}元</div>
				</div>
				<div class="inTr">
					<div class="L"><strong>運送方式:</strong></div>
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
					<div class="L"><strong>付款方式:</strong></div>
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
				
			</td>
			
			<td style="width:40%;">
				<div class="inTr">
					<div class="L"><strong>發票形式:</strong></div>
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
				<div class="inTr">
					<div class="L"><strong>運送地址:</strong></div>
					<div>
						<div class="innerDiv">${product_orderVO.ship_cty}${product_orderVO.ship_dist}${product_orderVO.ship_add}</div>
					</div>
				</div>
				<div class="inTr">
					<div class="L"><strong>下訂時間:</strong></div>
					<div><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${product_orderVO.prod_ord_time}"/></div>
				</div>
			</td>
			
			<td style="width:15%;">
				<div class="inTr">
					<label for="prod_ord_stat0" ${product_orderVO.prod_ord_stat==0?'class="spotlight"':'hide'}>未付款</label>
					<label for="prod_ord_stat1" ${product_orderVO.prod_ord_stat==1?'class="spotlight"':'hide'}>已付款</label>
					<label for="prod_ord_stat2" ${product_orderVO.prod_ord_stat==2?'class="spotlight"':'hide'}>出貨中</label>
					<label for="prod_ord_stat3" ${product_orderVO.prod_ord_stat==3?'class="spotlight"':'hide'}>已收貨</label>
					<label for="prod_ord_stat4" ${product_orderVO.prod_ord_stat==4?'class="warn"':'hide'}>未取貨</label>
					<label for="prod_ord_stat5" ${product_orderVO.prod_ord_stat==5?'class="warn"':'hide'}>訂單取消</label>
				</div>
			</td>
	   	</tr>
	   	<FORM id="form${product_orderVO.prod_ord_no}" METHOD="post" ACTION="${pageContext.request.contextPath}/product_order/product_order.do" style="margin-bottom: 0px;">
	   	<input type="hidden" name="prod_ord_no"  value="${product_orderVO.prod_ord_no}">
	   	<input type="hidden" name="action"	value="getOne_For_Display">
	   	</FORM>
	</c:forEach>
</table>

<script>
$("tr").click(function(e) {
	let no = e.currentTarget.children[0].innerText;
	var id = "#form"+no;
	$(id).submit();
});
</script>
</body>
</html>