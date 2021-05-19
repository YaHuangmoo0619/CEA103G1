<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*" %>
<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>



<% 
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	int ajax_mbr_no = 0;

	if(memberVO!=null){
		ajax_mbr_no = memberVO.getMbr_no();
	}
	if(memberVO==null){
		ajax_mbr_no=0;
	}
	pageContext.setAttribute("ajax_mbr_no", ajax_mbr_no);
%>

<html>
<head>
<title>商品 - listOneProduct.jsp</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<%@ include file="/part-of/partOfCampion_frontTop_css.txt"%>
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

<style>
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

</style>

</head>
<body bgcolor='white'>
<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
<table id="table-1">
	<tr><td>
		 <h3>商品 - ListOneProduct.jsp</h3>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />

<table>
	<tr>
		<th>商品編號</th>
		<th>商品分類名稱</th>
		<th>商品名稱</th>
		<th>商品價格</th>
		<th>商品資訊</th>
		<th>商品品牌</th>
		<th>商品顏色</th>
		<th>商品大小</th>
		<th>運送方式</th>
		<th></th>
		<th>數量</th>
		<th>庫存量</th>
		<th></th>
	</tr>
	<tr>
		<td>${productVO.prod_no}</td>
			<td>
			${product_categorySvc.getOneProduct_category(productVO.prod_cat_no).prod_cat_name}
			</td>
			<td>${productVO.prod_name}</td>
			<td>${productVO.prod_pc}</td>
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
			<td>
			  <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" style="margin-bottom: 0px;">
			     <input type="submit" value="直接下訂">
			     <input type="hidden" name="prod_no"  value="${productVO.prod_no}">
			     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     <input type="hidden" name="action"	value="buyOne"></FORM>
			</td>
			<td>
			  <button class="_btn _column product-subtract">&minus;</button>
              <div class="_column product-qty" id="product-qty">0</div>
              <button class="_btn _column product-plus">&plus;</button>
			</td>
			
			
			
			<td id="max_num">${productVO.prod_stg}</td>
			<!-- 	如果有登入的話 -->
			<c:if test="${not empty memberVO }"> 
			<td><button class=addshopping_cart>加入購物車</button></td>
			</c:if>
			<!-- 	如果沒有登入的話  要打開名為登入的燈箱-->	
			<c:if test="${empty memberVO }"> 
			<td><button class=to_login>加入購物車</button></td>
			</c:if>
			
	</tr>
</table>
	
			<div class="modal fade" id="login_confirm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5>您尚未登入</h5>
      </div>
      <div class="modal-body">
        <div>想要一起加入討論，要先登入 Campion 唷！</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary close_modal" data-bs-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/front-end/member/login.jsp'">登入</button>
      </div>
    </div>
  </div>
</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>


<script>

	$(".to_login").click(function(){
	  		$('#login_confirm').modal('show');
	})
	
	$(".close_modal").click(function(){
	  		$('#login_confirm').modal('hide');
	})
	
	$(".addshopping_cart").click(function(){
		var num_add = $("#product-qty").text();
		$.ajax({  
			type : "post",
			url : "http://localhost:8081/CEA103G1/shopping_cart/shopping_cart.do",
			data : {action: "add_collection",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,prod_no:<%=productVO.getProd_no()%>,prod_amt:num_add},
			success : function(data) {
				alert("成功加入購物車");
			}
		});
	})
	
	$(".product-plus").click(function(){
		var max_num = parseInt($("#max_num").text());
		console.log("max_num:"+max_num);
		var num = parseInt($(this).prev().text());
		if(num<max_num){
			num = num + 1;
		}
		$(this).prev().text(num);
		
		})

	$(".product-subtract").click(function(){
		var num = parseInt($(this).next().text());
		if(num>0){
			num = num - 1;
			$(this).next().text(num);
		}

		$(this).next().text(num);
		})	
		
		
		
</script>
</body>
</html>