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

</head>
<body bgcolor='white'>

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
			     <input type="hidden" name="action"	value="buyOne"></FORM>
			</td>
			<td><button class=addshopping_cart>加入購物車</button></td>
	</tr>
</table>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>


<script>
	$(".addshopping_cart").click(function(){
		$.ajax({  
			type : "POST",
			url : "http://localhost:8081/CEA103G1/shopping_cart/shopping_cart.do",
			data : {action: "plus_like",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,}, //參數傳遞 : action傳遞「加一」 mbr_no art_no 傳遞要加一的資訊
			success : function(data) {
				alert("新增某人對某文章的按讚成功");
			}
		});
	})
</script>
</body>
</html>