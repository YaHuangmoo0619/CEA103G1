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
<title>�ӫ~ - listOneProduct.jsp</title>

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
			<div class="divR colName">���O�G</div>
			<div class="divL">${product_categorySvc.getOneProduct_category(productVO.prod_cat_no).prod_cat_name}</div>
		</div>
		<div class="in">
			<div class="divR colName">�t�P�G</div>
			<div class="divL">${productVO.prod_bnd}</div>
		</div>
		<div class="in">
			<div class="divR colName">�W�١G</div>
			<div class="divL">${productVO.prod_name}</div>
		</div>
		<div class="in">
			<div class="divR colName">����G</div>
			<div class="divL">${productVO.prod_pc}��</div>
		</div>
		<div class="in">
			<div class="divR colName" ${productVO.prod_stg > 5? '':'style="color:red;"'}>�w�s�G</div>
			<div class="divL" ${productVO.prod_stg > 5? '':'style="color:red;"'}>${productVO.prod_stg}��</div>
		</div>
	</div>
</div>
<hr style="margin: 0 auto;">
<div class="out">
	<div class="in">
		<div class="colName">��T�G</div>
		<c:set var="prod_info" value="${productVO.prod_info}"/>
		<% request.setAttribute("line", "\n"); %>
		<div>${fn:replace(prod_info, line, '<br>')}</div>
	</div>
</div>
<hr style="margin: 0 auto;">
<div class="out">
	<div class="in">
		<div class="colName">�W��G</div>
		<div class="kinds">�C��G${productVO.prod_clr}</div>
		<div class="kinds">�j�p�G${productVO.prod_size}</div>
	</div>
</div>
<hr style="margin: 0 auto;">
<div class="out">
	<div class="in">
		<div class="colName">�B�e�覡�G</div>
		<div>
			<c:if test="${productVO.ship_meth==0}">
				<c:out value="�����B�e�覡" />
			</c:if>
			<c:if test="${productVO.ship_meth==1}">
				<c:out value="���v�t" />
			</c:if>
			<c:if test="${productVO.ship_meth==2}">
				<c:out value="���W�Ө��f" />
			</c:if>
		</div>
	</div>
</div>
		
			
			
</body>
</html>