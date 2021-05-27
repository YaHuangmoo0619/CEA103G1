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
	//���o�{�ɭq��̭����ӫ~(��T:�ӫ~�s���B�ڭn�R���ƶq)
	Jedis jedis = new Jedis("localhost", 6379);
	jedis.auth("123456");
	jedis.select(4);
	Map<String,String> my_item_list = new HashMap<>();
	//���o�{�ɭq�檺Map  key��prod_no  value��num
	for(String element : jedis.smembers("order:"+memberVO.getMbr_no()+":items")){
		String[]  strs=element.split(":");
		my_item_list.put(strs[0].toString(), strs[1].toString());
	}
	
	List<ProductVO> detail_list = new ArrayList<>();
			//�M��map�A�N�C�˰ӫ~��VO���X��
			for (Map.Entry<String, String> entry : my_item_list.entrySet()) {
			//�æs�Jdetail list
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

<title>���b</title>
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
</style>

</head>
<body>
	<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
<div class=container>
<%-- 	  <h1>${memberVO.name}���ʪ���</h1> --%>

<h2>�q�ʰӫ~��T</h2>
<br>
<div class="shopping-cart">

  <div class="column-labels">
    <label class="product-image">�Ӥ�</label>
    <label class="product-details">���~</label>
    <label class="product-price">���</label>
    <label class="product-quantity">�ƶq</label>
    <label class="product-line-price">�p�p</label>
  </div>

	<c:forEach var="detail_list" items="${detail_list}">
  <div class="product">
    <div class="product-image">
      <img src="https://s.cdpn.io/3/dingo-dog-bones.jpg">
    </div>
    
    <div class="product-details">
      <div class="product-title">${detail_list.prod_name}</div>
    </div>
    <div class="product-price">${detail_list.prod_pc}</div>
    
    
    <div class="product-quantity">
<!--       ���ѺðݡA���󤣥�set���ܲĤG��i�Ӷ]���� -->
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
      <label>�`�B</label>
      <div class="totals-value" id="cart-total">0</div>
    </div>
  </div>
            

</div>
			<form>	
					<h2>�q�ʪ̸�T</h2>
					<br>
					<div>�q�ʤH</div>
					<div class=buyer_name>${memberVO.name}</div>
					
					<div>�I�ڤ覡(�ثe�ȴ��ѫH�Υd)</div>
                  <div class="form-check pay_select">
  						<input class="form-check-input" type="radio" name="ptRadios" value="0" checked disabled>
  						<label class="form-check-label" for="exampleRadios1">�H�Υd</label>
                  </div>
					
                        <div class="city-selector-set">
                    <div>�H�e�a�}</div>
                    <div class=address_select>
                        <!-- ������� -->
                        <select class="county" name="ship_cty"></select>
                    </div>
                    <div class=address_select>
                        <!-- �ϰ��� -->
                        <select class="district" name="ship_dist"></select>
                    </div>
<!--                     <div class=address_select> -->
<!--                         �l���ϸ���� (��ĳ�[�J readonly �ݩʡA����ק�) -->
<!--                         <input class="zipcode" type="text" size="3" readonly placeholder="�l���ϸ�"> -->
<!--                     </div> -->
                    <div class=address_select>
                    	<input type="text" placeholder="��}" name="ship_add">
                    </div>
                </div>
                
                <div class=point-set>
                <div class=point-text>�O�_�ϥ��I��</div>
                <div class=point-have>�z���I�ƥثe�|�l:&nbsp;&nbsp;
                <c:if test="${memberVO.pt==0}"><span style="color:red">${memberVO.pt}</span>�I</c:if>
                <c:if test="${memberVO.pt!=0}"><span>${memberVO.pt}</span>�I</c:if>
                </div>
              <div class="form-check">
              	<c:if test="${memberVO.pt==0}">
  				<input class="form-check-input" type="radio" name="ptRadios" value="0" disabled>
  					<label class="form-check-label" for="exampleRadios1" style="color:black">
    					�ϥ�
  					</label>
  					
  					
              	</c:if>
              	<c:if test="${memberVO.pt!=0}">
  				<input class="form-check-input" type="radio" name="ptRadios" value="0">
  					<label class="form-check-label" for="exampleRadios1" style="color:black">
    					�ϥ�
  					</label>
  				</c:if>	
	   		 </div>
	   		 
	   		 <div class="form-check">
	   		 <c:if test="${memberVO.pt==0}">
  				<input class="form-check-input" type="radio" name="ptRadios" value="1" checked>
  					<label class="form-check-label" for="exampleRadios1" style="color:black">
    					���ϥ�
  					</label>
	   		 
	   		 </c:if>
	   		 <c:if test="${memberVO.pt!=0}">
	   		   	<input class="form-check-input" type="radio" name="ptRadios" value="1">
  					<label class="form-check-label" for="exampleRadios1" style="color:black">
    					���ϥ�
  					</label>
	   		 </c:if>
	   		 </div>
                </div>
              	<div>�q��Ƶ�</div>
              	<div><input type="text" placeholder="�p�G�z���S�O�ݨD�е���" name="rmk"></div>
				
				<input type="hidden" name="pay_meth" value="0">
				<input type="hidden" name="receipt" value="0">
                <input type="hidden" name="mbr_no" value="insert">
                <input type="hidden" name="PROD_ORD_SUM" value="insert">
                <input type="hidden" name="USED_PT" value="insert">
                
				<input type="submit" class="checkout" value="�h�R��">
				
				
				
				</form>
</div>



                
        


                
	<script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-157cd5b220a5c80d4ff8e0e70ac069bffd87a61252088146915e8726e5d9f147.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	    <script src="./tw-city-selector-master/docs/js/tw-city-selector.js"></script>
    <script>
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