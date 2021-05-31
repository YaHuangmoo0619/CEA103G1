<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_picture.model.*"%>
<jsp:useBean id="product_pictureSvc" scope="page" class="com.product_picture.model.Product_pictureService"/>
<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />


<%
	ProductService productSvc = new ProductService();
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
    List<ProductVO> list = productSvc.getShop();
    pageContext.setAttribute("list",list);
%>



<html>

<head>

<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>所有商品 </title>
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
	width: 800px;
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
</style>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>

</head>
<body onload="connection()">
<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<section class="features8 cid-syeFkbGLP0">
    <c:forEach var="productVO" items="${list}" >
    <div class="container">
        <div class="card">
            <div class="card-wrapper">
                <div class="row align-items-center">
                    <div class="col-12 col-md-4">
                        <div class="showPic">
							<c:forEach var="product_pictureVO" items="${product_pictureSvc.findByProd_no(productVO.prod_no)}">
								<img class="inDiv" src="${product_pictureVO.prod_pic}" style="max-width:100%; height:auto;">
							</c:forEach>
                        </div>
                    </div>
                    <div class="col-12 col-md">
                        <div class="card-box">
                            <div class="row">
                                <div class="col-md">
                                    <p>【${product_categorySvc.getOneProduct_category(productVO.prod_cat_no).prod_cat_name}】</p>
                                    <h6 class="card-title mbr-fonts-style display-5">
                                        <strong>商品名稱:</strong>
                                    </h6>
                                    <p class="mbr-text mbr-fonts-style display-7">
                                        ${productVO.prod_name}
                                    </p>
                                    <p>品牌資訊:${productVO.prod_bnd}</p>                                   
                                </div>
                                <div class="col-md-auto">
                              		<p>商品價格 </p>
                                    <p class="price mbr-fonts-style display-2">${productVO.prod_pc}</p>
                                     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" style="margin-bottom: 0px;">
									     <input type="submit" value="查看詳情" class="btn btn-white-outline display-4">
									     <input type="hidden" name="prod_no"  value="${productVO.prod_no}">
									     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
									     <input type="hidden" name="action"	value="getOne_For_Display">
								     </FORM>
								     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" style="margin-bottom: 0px;">
									     <input type="submit" value="直接下訂" class="btn btn-white-outline display-4">
									     <input type="hidden" name="prod_no"  value="${productVO.prod_no}">
									     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
									     <input type="hidden" name="action"	value="buyOne">
									 </FORM>        
                                </div>
                                <div></div>
                            </div> 
                        </div>
                    </div>
                </div>
            </div>
        </div>      
    </div>
    </c:forEach>
</section>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

</body>
</html>