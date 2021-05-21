<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*" %>
<%@ page import="com.shopping_cart.model.*" %>
<%@ page import="com.product.model.*" %>

<%
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
//ProductService productSvc = new ProductService();
	
	
%>

<html>
<head>
<title>商品下訂</title>

<%@ include file="/part-of/partOfCampion_COwnerTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_COwnerLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

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
		 <h3>商品下訂</h3>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />

<table>
	<tr>
		<th>商品順位</th>
		<th>商品分類名稱</th>
		<th>商品名稱</th>
		<th>價格小計</th>
		<th>購買數量</th>
		<th>運送方式</th>
	</tr>
	<tr>
		<td>1</td>
		<td>
		${product_categorySvc.getOneProduct_category(productVO.prod_cat_no).prod_cat_name}
		</td>
		<td>${productVO.prod_name}</td>
		<td>
			<input type="hidden" value="prod_unit_pc"/>${productVO.prod_pc}
		</td>
		<td>
		<input type="button" value="+" onclick="this.nextElementSibling.value++"/>
    	<input type="text" name="prod_amt" id="amount" value="0" readonly="readonly" size="4"/>
    	<input type="button" value="-" onclick="(this.previousElementSibling.value<1 ? 0 : this.previousElementSibling.value--)"/>
    	</td>
		<td>
		<c:if test="${productVO.ship_meth==0}">
			<c:out value="請選擇運送方式" />
			<input  type="radio" name="ship_meth" value="1">宅配
			<input  type="radio" name="ship_meth" value="2">超商取貨
		</c:if>
		<c:if test="${productVO.ship_meth==1}">
			<c:out value="限宅配" />
		</c:if>
		<c:if test="${productVO.ship_meth==2}">
			<c:out value="限超商取貨" />
		</c:if>
		</td>
		<td>
		<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order_details/product_order_details.do" style="margin-bottom: 0px;">
			 <input type="submit" value="送出">
			 <input type="hidden" name="prod_no" value="${productVO.prod_no}">
			 <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			 <input type="hidden" name="action"	value="insert">
		</FORM>
		
		</td>    
   
	</tr>
</table>

</body>
</html>