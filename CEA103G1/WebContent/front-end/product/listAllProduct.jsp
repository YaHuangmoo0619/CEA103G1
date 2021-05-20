<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_picture.model.*"%>
<%
	ProductService productSvc = new ProductService();
    List<ProductVO> list = productSvc.getShop();
    pageContext.setAttribute("list",list);
%>



<html>

<head>
<title>所有商品 </title>

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
	width: 800px;
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
<body bgcolor='white'>

<!-- <div style="background-color: #eee;">
	<img src="/CEA103G1/front-images/campionLogoLong.png" class="logo">
	<form class="form-inline my-2 my-lg-0">
		<input class="form-control mr-sm-2" type="search"
			placeholder="營位/商品/文章" aria-label="Search">
		<button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜尋</button>
	</form>
	<img src="/CEA103G1/front-images/search-circle-outline.svg"
		class="searchIcon" onclick="showSearch()">
	<div class="btn-group" role="group" aria-label="Basic example">
		<button type="button" class="btn btn-secondary">營區</button>
		<button type="button" class="btn btn-secondary">商城</button>
		<button type="button" class="btn btn-secondary">論壇</button>
	</div>
	<img src="/CEA103G1/front-images/cart-outline.svg" class="cart">
	<div class="btn-group" role="group" aria-label="Basic example">
		<button type="button" class="btn btn-outline-secondary">註冊</button>
		<button type="button" class="btn btn-outline-secondary">登入</button>
		<button type="button" class="btn btn-outline-secondary">FAQ</button>
		<button type="button" class="btn btn-outline-secondary">聯絡我們</button>
	</div>
	<img src="/CEA103G1/front-images/menu-outline.svg" class="menu"
		onclick="showMenu()"> <img
		src="/CEA103G1/front-images/person-circle-outline.svg" class="person">
</div> -->

<table id="table-1">
	<tr><td>
		 <h3>所有商品 </h3>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />

<table>
	<tr>
		<th>商品編號</th>
		<th>商品分類名稱</th>
		<th>商品照片</th>
		<th>商品名稱</th>
		<th>商品價格</th>
		<th>商品資訊</th>
		<th>商品品牌</th>
		<th>商品顏色</th>
		<th>商品大小</th>
		<th>運送方式</th>
	</tr>
<%-- <%@ include file="page1.file" %> --%>
	<c:forEach var="productVO" items="${list}" > <%-- begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" --%>
		
		<tr>
			<td>${productVO.prod_no}</td>
			<td>
			${product_categorySvc.getOneProduct_category(productVO.prod_cat_no).prod_cat_name}
			</td>
			<td>
			<c:forEach var="product_pictureVO" items="${list}" >
			${product_pictureSvc.getOneProduct_picture(productVO.prod_cat_no).prod_pic}
			</c:forEach>
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
			     <input type="submit" value="查看詳情">
			     <input type="hidden" name="prod_no"  value="${productVO.prod_no}">
			     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     <input type="hidden" name="action"	value="getOne_For_Display"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" style="margin-bottom: 0px;">
			     <input type="submit" value="直接下訂">
			     <input type="hidden" name="prod_no"  value="${productVO.prod_no}">
			     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     <input type="hidden" name="action"	value="buyOne"></FORM>
			</td>
		</tr>
	</c:forEach>
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

<%-- <%@ include file="page2.file" %> --%> 

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
		}
		
		$(this).next().text(num);
		})	
		
		
		
</script>

</body>
</html>