<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*" %>
<%@ page import="com.shopping_cart.model.*" %>
<%@ page import="com.product.model.*" %>
<%@ page import="redis.clients.jedis.Jedis"%>
<% 
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	Shopping_cartService shopping_cartSvc = new Shopping_cartService();
	ProductService productSvc = new ProductService();
	//取得臨時訂單裡面的商品(資訊:商品編號、我要買的數量)
	Jedis jedis = new Jedis("localhost", 6379);
	jedis.auth("123456");
	jedis.select(4);
	Map<String,String> my_item_list = new HashMap<>();
	//取得臨時訂單的Map  key為prod_no  value為num
	for(String element : jedis.smembers("order:"+memberVO.getMbr_no()+":items")){
		String[]  strs=element.split(":");
		my_item_list.put(strs[0].toString(), strs[1].toString());
	}
	
	List<ProductVO> detail_list = new ArrayList<>();
			//遍歷map，將每樣商品的VO取出來
			for (Map.Entry<String, String> entry : my_item_list.entrySet()) {
			//並存入detail list
				detail_list.add(productSvc.getOneProduct(Integer.valueOf(entry.getKey())));	
		}
			pageContext.setAttribute("memberVO", memberVO);
			pageContext.setAttribute("my_item_list", my_item_list);
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

<title>結帳</title>
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
  margin: 0px 0px 20px 0px;
  padding: 6px 25px;
  background-color: #6b6;
  color: #fff;
  font-size: 25px;
  border-radius: 3px;
}

.checkout:hover {
  background-color: #494;
}

.address_select{
display:inline-block;
}
.form-check-input{
display:inline-block;
}



.point-text,.point-have{
display:inline-block;
}


.buyer_name{
padding:0px 0px 20px 0px;
}
.city-selector-set{
padding:20px 0px 20px 0px;
}

.point-set{
padding:0px 0px 20px 0px;
}

.receipt-set div{
display:inline-block;
padding:0px 0px 20px 0px;
}
</style>

</head>
<body>
	<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
<div class=container>
<%-- 	  <h1>${memberVO.name}的購物車</h1> --%>

<h2>訂購商品資訊</h2>
<br>
<div class="shopping-cart">

  <div class="column-labels">
    <label class="product-image">照片</label>
    <label class="product-details">產品</label>
    <label class="product-price">單價</label>
    <label class="product-quantity">數量</label>
    <label class="product-line-price">小計</label>
  </div>

	<c:forEach var="detail_list" items="${detail_list}">
  <div class="product">
    <div class="product-image">
      <img src="/CEA103G1/images/product_picture/${detail_list.prod_no}.jpg" width="70px" height="70px">
    </div>
    
    <div class="product-details">
      <div class="product-title">${detail_list.prod_name}</div>
    </div>
    <div class="product-price">${detail_list.prod_pc}</div>
    
    
    <div class="product-quantity">
<!--       未解疑問，為何不用set的話第二圈進來跑不動 -->
		<c:set value="${my_item_list}" var="list2"/>
         <c:forEach var="list2" items="${list2}">
			<c:if test="${detail_list.prod_no==list2.key}"><c:set var="num_this" value="${list2.value}"></c:set>${list2.value}</c:if>
		 </c:forEach>
    </div>
    <div class=line-price>
    ${detail_list.prod_pc*num_this}
    </div>
      </div>

	</c:forEach>
	

  <div class="totals">
    <div class="totals-item totals-item-total">
      <label>總額</label>
      <div class="totals-value" id="prod_ord_sum"></div>
    </div>
  </div>
            

</div>
			<form id="card" METHOD="post" ACTION="/CEA103G1/product_order/product_order.do" name="form1" autocomplete>	
					<h2>訂購者資訊</h2>
					<br>
					<div>訂購人</div>
					<div class=buyer_name>${memberVO.name}</div>
					
					<div>付款方式(目前僅提供信用卡)</div>
                  <div class="form-check pay_select">
  						<input class="form-check-input" type="radio" name="p_mRadios" value="0" checked disabled>
  						<label class="form-check-label" for="exampleRadios1">信用卡</label>
                  </div>
					
                        <div class="city-selector-set">
                    <div>寄送地址</div>
                    <div class=address_select>
                        <!-- 縣市選單 -->
                        <select class="county" name="ship_cty"></select>
                    </div>
                    <div class=address_select>
                        <!-- 區域選單 -->
                        <select class="district" name="ship_dist"></select>
                    </div>
<!--                     <div class=address_select> -->
<!--                         郵遞區號欄位 (建議加入 readonly 屬性，防止修改) -->
<!--                         <input class="zipcode" type="text" size="3" readonly placeholder="郵遞區號"> -->
<!--                     </div> -->
                    <div class=address_select>
                    	<input type="text" placeholder="住址" name="ship_add">
                    </div>
                </div>
                
                <div class=point-set>
                <div class=point-text>是否使用點數</div>
                <div class=point-have>您的點數目前尚餘:&nbsp;&nbsp;
                <c:if test="${memberVO.pt==0}"><span style="color:red">${memberVO.pt}</span>點</c:if>
                <c:if test="${memberVO.pt!=0}"><span>${memberVO.pt}</span>點</c:if>
                </div>
              <div class="form-check">
              	<c:if test="${memberVO.pt==0}">
  				<input class="form-check-input" type="radio" name="ptRadios" value="0" disabled>
  					<label class="form-check-label" for="exampleRadios1" style="color:black">
    					使用
  					</label>
  					
  					
              	</c:if>
              	<c:if test="${memberVO.pt!=0}">
  				<input class="form-check-input" type="radio" name="ptRadios" value="0">
  					<label class="form-check-label" for="exampleRadios1" style="color:black">
    					使用
  					</label>
  				</c:if>	
	   		 </div>
	   		 
	   		 <div class="form-check">
	   		 <c:if test="${memberVO.pt==0}">
  				<input class="form-check-input" type="radio" name="ptRadios" value="1" checked>
  					<label class="form-check-label" for="exampleRadios1" style="color:black">
    					不使用
  					</label>
	   		 
	   		 </c:if>
	   		 <c:if test="${memberVO.pt!=0}">
	   		   	<input class="form-check-input" type="radio" name="ptRadios" value="1">
  					<label class="form-check-label" for="exampleRadios1" style="color:black">
    					不使用
  					</label>
	   		 </c:if>
	   		 </div>
                </div>
                
<!--                 <div class=receipt-set> -->
                
<!--                 <div class="form-check"> -->
<!--   				<input class="form-check-input" type="radio" name="receiptRadios" value="0"> -->
<!--   					<label class="form-check-label" for="exampleRadios1" style="color:black"> -->
<!--     					紙本發票 -->
<!--   					</label> -->
<!-- 	   		    </div> -->
<!-- 	   		    <div class="form-check"> -->
<!--   				<input class="form-check-input" type="radio" name="receiptRadios" value="1"> -->
<!--   					<label class="form-check-label" for="exampleRadios1" style="color:black"> -->
<!--     					電子發票 -->
<!--   					</label> -->
<!-- 	   		    </div> -->
<!-- 	   		    <div class="form-check"> -->
<!--   				<input class="form-check-input" type="radio" name="receiptRadios" value="2"> -->
<!--   					<label class="form-check-label" for="exampleRadios1" style="color:black"> -->
<!--     					發票捐贈 -->
<!--   					</label> -->
<!-- 	   		    </div> -->
<!--                 </div> -->
              	
              	<div>訂單備註</div>
              	<div><input type="text" placeholder="如果您有特別需求請註明" name="rmk"></div>
				<div id="for_position"></div>
				<input type="hidden" name="pay_meth" value="0">
				<input type="hidden" name="receipt" value="0">
                <input type="hidden" name="mbr_no" value="${memberVO.mbr_no}">
                <input type="hidden" name="used_pt" value="0">        
				<input type="button" class="checkout" value="去買單">
				

    <div class="modal" tabindex="-1" role="dialog" id="test2">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title" style="text-align: center;">信用卡資訊</h3>
                </div>
                <div class="modal-body">
                    <div id="credit" class="demo-container">
                        <div class="card-wrapper"></div>
                        <br>
                        <div class="form-container active">
                                <input placeholder="Card number" type="tel" name="number" required>
                                <input placeholder="Full name" type="text" name="name" required>
                                <input placeholder="MM/YY" type="tel" name="expiry" required>
                                <input placeholder="CVC" type="number" name="cvc" required>
                                <input type="hidden" value="insert_products" name="action">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit"  class="btn btn-secondary">結帳</button>
                </div>
            </div>
        </div>
    </div>                    
                    
                    
				</form>
</div>



                
        


       
	<script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-157cd5b220a5c80d4ff8e0e70ac069bffd87a61252088146915e8726e5d9f147.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	    <script src="./tw-city-selector-master/docs/js/tw-city-selector.js"></script>
	    
	        <script src="/../CEA103G1/creditcard/card.js"></script>  
    <script>        new Card({
        form: document.querySelector('#card'),
        container: '.card-wrapper'
    });</script>  
    
    <script>
   		 var total = 0;
		$(".line-price").each(function(){
			total = total + parseInt($(this).text());
			console.log(total);
		}) 
		
		$("#prod_ord_sum").text(total);
		$("#for_position").after(`<input type="hidden" value="`+total+`" name="prod_ord_sum">`);

		
		
    	$(".checkout").click(function(){
    		$('#test2').modal('show');
    	})
    
        new TwCitySelector();

        var a = new TwCitySelector({
            el: '.test-object-normal'
        });


        var a = new TwCitySelector({
            el: '.test-object-has-zipcode',
            hasZipcode: true
        });


        var a = new TwCitySelector({
            el: '.test-object-standard-words',
            standardWords: true
        });



        new TwCitySelector({
            el: '.city-selector-set',
            elCounty: '.county',
            elDistrict: '.district',
            elZipcode: '.zipcode'
        });
    </script>






</body>
</html>