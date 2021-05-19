<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*" %>
<%@ page import="com.shopping_cart.model.*" %>
<%@ page import="com.product.model.*" %>
<% 
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	Shopping_cartService shopping_cartSvc = new Shopping_cartService();
	ProductService productSvc = new ProductService();
	List<Shopping_cartVO> list = shopping_cartSvc.getByMbr_no(memberVO.getMbr_no()); //取得我購物車裡面的商品(資訊:商品編號、我要買的數量)
	//我要這些商品的其他詳細資訊
	
	List<ProductVO> detail_list = new ArrayList<>();
	for(Shopping_cartVO element : list){
		detail_list.add(productSvc.getOneProduct(element.getProd_no()));	//查詢購物車裡面每個商品的詳細資訊並放入詳細資訊的list     
	}

	pageContext.setAttribute("list", list);
	pageContext.setAttribute("detail_list", detail_list);
%>
<%

request.setAttribute("vEnter", "\n");

%>


<html>
<head>
<meta charset="Big5">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="apple-touch-icon" type="image/png" href="https://cpwebassets.codepen.io/assets/favicon/apple-touch-icon-5ae1a0698dcc2402e9712f7d01ed509a57814f994c660df9f7a952f3060705ee.png" />
<meta name="apple-mobile-web-app-title" content="CodePen">

<link rel="shortcut icon" type="image/x-icon" href="https://cpwebassets.codepen.io/assets/favicon/favicon-aec34940fbc1a6e787974dcd360f2c6b63348d4b1f4e06c77743096d55480f33.ico" />

<link rel="mask-icon" type="" href="https://cpwebassets.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg" color="#111" />

<title>某會員的購物車</title>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

<%@ include file="/article_css/article_css.txt"%>
<%@ include file="/part-of/partOfCampion_frontTop_css.txt"%>
<link rel="icon" href="campionLogoIcon.png" type="image/png">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">

<style>

.product-image {
  float: left;
  width: 20%;
}

.product-details {
  float: left;
  width: 37%;
}

.product-price {
  float: left;
  width: 12%;
}

.product-quantity {
  float: left;
  width: 10%;
}

.product-removal {
  float: left;
  width: 9%;
}

.product-line-price {
  float: left;
  width: 12%;
  text-align: right;
}

/* This is used as the traditional .clearfix class */
.group:before, .shopping-cart:before, .column-labels:before, .product:before, .totals-item:before,
.group:after,
.shopping-cart:after,
.column-labels:after,
.product:after,
.totals-item:after {
  content: '';
  display: table;
}

.group:after, .shopping-cart:after, .column-labels:after, .product:after, .totals-item:after {
  clear: both;
}

.group, .shopping-cart, .column-labels, .product, .totals-item {
  zoom: 1;
}

/* Apply clearfix in a few places */
/* Apply dollar signs */
.product .product-price:before, .product .product-line-price:before, .totals-value:before {
  content: '$';
}

/* Body/Header stuff */
body {
/*   padding: 0px 30px 30px 20px; */
  font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-weight: 100;
}

.container{
padding: 0px 10px 30px 10px;
}
h1 {
  font-weight: 100;
}

label {
  color: #aaa;
}

.shopping-cart {
  margin-top: -45px;
}

/* Column headers */
.column-labels label {
  padding-bottom: 15px;
  margin-bottom: 15px;
  border-bottom: 1px solid #eee;
}
.column-labels .product-image, .column-labels .product-details, .column-labels .product-removal {
  text-indent: -9999px;
}

/* Product entries */
.product {
  margin-bottom: 20px;
  padding-bottom: 10px;
  border-bottom: 1px solid #eee;
}
.product .product-image {
  text-align: center;
}
.product .product-image img {
  width: 100px;
}
.product .product-details .product-title {
  margin-right: 20px;
  font-family: "HelveticaNeue-Medium", "Helvetica Neue Medium";
}
.product .product-details .product-description {
  margin: 5px 20px 5px 0;
  line-height: 1.4em;
}
.product .product-quantity input {
  width: 40px;
}
.product .remove-product {
  border: 0;
  padding: 4px 8px;
  background-color: #c66;
  color: #fff;
  font-family: "HelveticaNeue-Medium", "Helvetica Neue Medium";
  font-size: 12px;
  border-radius: 3px;
}
.product .remove-product:hover {
  background-color: #a44;
}

/* Totals section */
.totals .totals-item {
  float: right;
  clear: both;
  width: 100%;
  margin-bottom: 10px;
}
.totals .totals-item label {
  float: left;
  clear: both;
  width: 79%;
  text-align: right;
}
.totals .totals-item .totals-value {
  float: right;
  width: 21%;
  text-align: right;
}
.totals .totals-item-total {
  font-family: "HelveticaNeue-Medium", "Helvetica Neue Medium";
}

.checkout {
  float: right;
  border: 0;
  margin-top: 20px;
  padding: 6px 25px;
  background-color: #6b6;
  color: #fff;
  font-size: 25px;
  border-radius: 3px;
}

.checkout:hover {
  background-color: #494;
}


</style>

</head>
<body>
	<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
<div class=container>
	  <h1>${memberVO.name}的購物車</h1>

<div class="shopping-cart">

  <div class="column-labels">
    <label class="product-image">照片</label>
    <label class="product-details">產品</label>
    <label class="product-price">單價</label>
    <label class="product-quantity">數量</label>
    <label class="product-removal">移除</label>
    <label class="product-line-price">小計</label>
  </div>

	<c:forEach var="detail_list" items="${detail_list}">
  <div class="product">
    <div class="product-image">
      <img src="https://s.cdpn.io/3/dingo-dog-bones.jpg">
    </div>
    <div class="product-details">
      <div class="product-title">${detail_list.prod_name}</div>
      <p class="product-description">${fn:replace(detail_list.prod_info,vEnter,"<br>")}</p>
    </div>
    <div class="product-price">${detail_list.prod_pc}</div>
    
    
    <div class="product-quantity">
<!--       未解疑問，為何不用set的話第二圈進來跑不動 -->
		<c:set value="${list}" var="list2"/>
         <c:forEach var="list2" items="${list2}">
			<c:if test="${detail_list.prod_no==list2.prod_no}"><input type="number" value="${list2.prod_amt}" min="1"></c:if>
			<c:if test="${detail_list.prod_no==list2.prod_no}"><c:set value="${list2.prod_amt}" var="amt_this"/></c:if>		 
		 </c:forEach>
    </div>
    
      
    <div class="product-removal">
      <div class="product-no" style="display:none">${detail_list.prod_no}</div>
      <button class="remove-product">刪除</button>
    </div>
    <div class="product-line-price">${amt_this * detail_list.prod_pc}</div>
  </div>
	</c:forEach>
	

  <div class="totals">
    <div class="totals-item totals-item-total">
      <label>總額</label>
      <div class="totals-value" id="cart-total"></div>
    </div>
  </div>
      
      <button class="checkout">去買單</button>

</div>
</div>
	<script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-157cd5b220a5c80d4ff8e0e70ac069bffd87a61252088146915e8726e5d9f147.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>



<script>

var total_init = 0;
$('.product-line-price').each(function(){
	var te = parseInt($(this).text());
	if(isNaN(te)===false){
		total_init += te;
	}
});
$('#cart-total').text(total_init);

/* 動畫毫秒 */
var fadeTime = 300;


/* 數量有更動要更新 */
$('.product-quantity input').change(function () {
  updateQuantity(this);
});

/* 按下移除按鈕要移除 */
$('.product-removal button').click(function () {
  	var remove_prod_no = $(this).prev().text();
	$.ajax({  
		type : "post",
		url : "http://localhost:8081/CEA103G1/shopping_cart/shopping_cart.do",
		data : {action: "cancel_collection",mbr_no:<%=memberVO.getMbr_no()%>,prod_no:remove_prod_no},
		success : function(data) {
			alert("成功將商品編號:"+remove_prod_no+"從購物車中移除");
		}
	});
	removeItem(this);
});


/* 重新計算購物車 */
function recalculateCart()
{
  var total = 0;

  /* 要購買的單一商品總價   */
  $('.product').each(function () {
    total += parseFloat($(this).children('.product-line-price').text());
  });


  /*更新顯示的總價 */
  $('.totals-value').fadeOut(fadeTime, function () {
    $('#cart-total').html(total.toFixed(2));
    if (total == 0) {
      $('.checkout').fadeOut(fadeTime);
    } else {
      $('.checkout').fadeIn(fadeTime);
    }
    $('.totals-value').fadeIn(fadeTime);
  });
}


/* 更新數量 */
function updateQuantity(quantityInput)
{
  /* Calculate line price */
  var productRow = $(quantityInput).parent().parent();
  var price = parseFloat(productRow.children('.product-price').text());
  var quantity = $(quantityInput).val();
  var linePrice = price * quantity;

  /* Update line price display and recalc cart totals */
  productRow.children('.product-line-price').each(function () {
    $(this).fadeOut(fadeTime, function () {
      $(this).text(linePrice.toFixed(2));
      recalculateCart();
      $(this).fadeIn(fadeTime);
    });
  });
}


/* 移除整個商品 */
function removeItem(removeButton)
{
  /* 移除並重新計算總額 */
  var productRow = $(removeButton).parent().parent();
  productRow.slideUp(fadeTime, function () {
    productRow.remove();
    recalculateCart();
  });
  
}
    </script>


</script>
</body>
</html>