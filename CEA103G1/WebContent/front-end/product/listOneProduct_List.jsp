<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*" %>
<%@ page import="com.product.model.*" %>
<jsp:useBean id="product_pictureSvc" scope="page" class="com.product_picture.model.Product_pictureService"/>
<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />

<%
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>

<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>單一商品下訂</title>
<%@ include file="/part-of/partOfCampion_frontTop_css.txt"%>
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
  .container{
  	opacity:0.7;
  }
.product-subtract,
.product-plus,
.product-qty {
  width: 33.330557%;
  background-color: transparent;
  color: #686868;
  text-align: center;
}

._column {
  display: inline-block;
  vertical-align: top;
  font-size: medium;
  text-align: left;
  text-rendering: optimizeLegibility;
}

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
} 
  
.finish {
  float: right;
  border: 0;
  padding: 6px 25px;
  background-color: #80c344;
  color: #fff;
  font-size: 25px;
  border-radius: 5px;
}

</style>


</head>
<body onload="connection()">
<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
<h3>單一商品下訂&nbsp;</h3>

<div class="col-12 col-md-3">
<div class="showPic">
<c:forEach var="product_pictureVO" items="${product_pictureSvc.findByProd_no(productVO.prod_no)}">
<img class="inDiv" src="${product_pictureVO.prod_pic}" style="max-width:100%; height:auto;">
</c:forEach>
</div>
</div>


<div class="col-12 col-md-9">
<div>【商品分類名稱】  ${product_categorySvc.getOneProduct_category(productVO.prod_cat_no).prod_cat_name}</div>
<div>【商品名稱】 ${productVO.prod_name}</div>
<div>【商品單價】${productVO.prod_pc}</div>
<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order/product_order.do" style="margin-bottom: 0px;">
<hr />

	<div>
		<div>【會員姓名】${memberVO.name}</div>
		<div>【購買數量】
			<input type="button" value="+" onclick="this.nextElementSibling.value++"/>
    		<input type="text" name="prod_amt" id="prod_amt" value="3" readonly="readonly" size="4"/>
    		<input type="button" value="-" onclick="(this.previousElementSibling.value<1 ? 0 : this.previousElementSibling.value--)"/>
		</div>
		<div>【使用點數】
    		<input type="text" name="used_pt" id="used_pt" value="0" size="4"/>
		</div>
		<div>【運送方式】
		<c:if test="${productVO.ship_meth==0}">
		<c:out value="請選擇" />
			<input type="radio" name="ship_meth" value="1">宅配
			<input type="radio" name="ship_meth" value="2">超商取貨
		</c:if>
		<c:if test="${productVO.ship_meth==1}">
			<c:out value="限宅配" />
			<input type="hidden" name="ship_meth" value="1">
		</c:if>
		<c:if test="${productVO.ship_meth==2}">
			<c:out value="限超商取貨" />
			<input type="hidden" name="ship_meth" value="2">
		</c:if>
		</div>
		<input type="hidden" name="pay_meth" value="1">
		<input type="hidden" name="receipt" value="1">
		
		<div>【連絡電話】<input type="text" name="tel" value=""></div>
		<div>【運送縣市】<select id="city" name="ship_city"></select></div>
		<div>【運送區域】<select id="dist" name="ship_dist"></select></div>
		<div>【運送地址】<input type="text" name="ship_add" value=""></div>

		<div>【付款方式】
		<c:out value="請選擇" />
			<input type="radio" name="pay_meth" value="0">信用卡
			<input type="radio" name="pay_meth" value="1">匯款
			<input type="radio" name="pay_meth" value="2">貨到付款
		</div>

		<input type="hidden" name="receipt" value="1">
		
		<div>
		【訂單備註】
		<textarea id="rmk" cols="50" rows="5">
請輸入:
		</textarea>
		</div>
		
		<script>
			var amt = $('#prod_amt').val();
			var rmk = $('#rmk').val()
		</script>
		
		<input type="submit" class="finish" value="結帳">
		<input type="hidden" name="mbr_no" value="${memberVO.mbr_no}">
		<input type="hidden" name="prod_ord_stat" value="1">
		<input type="hidden" name="prod_ord_sum" value="${productVO.prod_pc}">
		
		<input type="hidden" name="pay_meth" value="1">
		<input type="hidden" name="receipt" value="1">
		<input type="hidden" name="rmk"	value="">
		
		<input type="hidden" name="ship_cty" value="">
		<input type="hidden" name="ship_dist" value="">
		
		<input type="hidden" name="prod_no" value="${productVO.prod_no}">
		<input type="hidden" name="prod_unit_pc" value="${productVO.prod_pc}">
		<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
		<input type="hidden" name="action"	value="insert">
	</div>
	</FORM>
</div>

<script src="<%=request.getContextPath()%>/front-end/member/city_dist.js"></script>
<script type="text/javascript">
 window.onload = function () {
		 AddressSeleclList.Initialize('city', 'dist');
		
   };
</script>

</body>
</html>