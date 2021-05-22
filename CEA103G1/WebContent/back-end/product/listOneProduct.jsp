<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.product.model.*"%>

<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />
<jsp:useBean id="product_pictureSvc" scope="page" class="com.product_picture.model.Product_pictureService" />

<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<title>商品 - listOneProduct.jsp</title>

<%@ include file="/part-of/partOfCampion_COwnerTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_COwnerLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>

<style>
div.out{
	display:flex;
	margin: 1em 3em;
}
img.inModal{
	width: 100%;
}
div.mid{
	width:50%;
	padding: 10px 20px;
}
div.in{
	margin-bottom: 5px;
}
div.divR{
	display:inline;
	width:30%;	
}
div.divL{
	display:inline;
	width:70%;
}
div.kinds{
	display:inline;
	padding-right:20px;
}
div.colName{
	font-weight:555;
}

</style>
</head>
<body>
<div class="out">
	<div class="mid">
		<img src="${product_pictureSvc.getOneProduct_picture(productVO.prod_no).getProd_pic()}" class="inModal">
	</div>
	<div class="mid">
		<div class="in">
			<div class="divR colName">類別：</div>
			<div class="divL">${product_categorySvc.getOneProduct_category(productVO.prod_cat_no).prod_cat_name}</div>
		</div>
		<div class="in">
			<div class="divR colName">廠牌：</div>
			<div class="divL">${productVO.prod_bnd}</div>
		</div>
		<div class="in">
			<div class="divR colName">名稱：</div>
			<div class="divL">${productVO.prod_name}</div>
		</div>
		<div class="in">
			<div class="divR colName">價格：</div>
			<div class="divL">${productVO.prod_pc}元</div>
		</div>
		<div class="in">
			<div class="divR colName" ${productVO.prod_stg > 5? '':'style="color:red;"'}>庫存：</div>
			<div class="divL" ${productVO.prod_stg > 5? '':'style="color:red;"'}>${productVO.prod_stg}件</div>
		</div>
	</div>
</div>
<hr style="margin: 0 auto;">
<div class="out">
	<div class="in">
		<div class="colName">資訊：</div>
		<c:set var="prod_info" value="${productVO.prod_info}"/>
		<% request.setAttribute("line", "\n"); %>
		<div>${fn:replace(prod_info, line, '<br>')}</div>
	</div>
</div>
<hr style="margin: 0 auto;">
<div class="out">
	<div class="in">
		<div class="colName">規格：</div>
		<div class="kinds">顏色：${productVO.prod_clr}</div>
		<div class="kinds">大小：${productVO.prod_size}</div>
	</div>
</div>
<hr style="margin: 0 auto;">
<div class="out">
	<div class="in">
		<div class="colName">運送方式：</div>
		<div>
			<c:if test="${productVO.ship_meth==0}">
				<c:out value="不限運送方式" />
			</c:if>
			<c:if test="${productVO.ship_meth==1}">
				<c:out value="限宅配" />
			</c:if>
			<c:if test="${productVO.ship_meth==2}">
				<c:out value="限超商取貨" />
			</c:if>
		</div>
	</div>
</div>
		
			
			
</body>
</html>