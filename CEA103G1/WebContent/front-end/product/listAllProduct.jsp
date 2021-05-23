<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_picture.model.*"%>
<%
	ProductService productSvc = new ProductService();
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
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

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>

</head>
<body bgcolor='white'>

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

<section class="features8 cid-syeFkbGLP0" xmlns="http://www.w3.org/1999/html" id="features9-12">
    <div class="container">
        <div class="card">
            <div class="card-wrapper">
                <div class="row align-items-center">
                    <div class="col-12 col-md-4">
                        <div class="">
                            <img src="">
                        </div>
                    </div>
                    <div class="col-12 col-md">
                        <div class="card-box">
                            <div class="row">
                                <div class="col-md">
                                    <p>${product_categorySvc.getOneProduct_category(productVO.prod_cat_no).prod_cat_name}</p>
                                    <h6 class="card-title mbr-fonts-style display-5">
                                        <strong>商品名稱:</strong>
                                    </h6>
                                    <p class="mbr-text mbr-fonts-style display-7">
                                        ${productVO.prod_cat_name}
                                    </p>
                                    <p>品牌資訊:${productVO.prod_bnd}</p>
                                    <p>${productVO.prod_info}</p>                                    
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
                                    <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do"  style="margin-bottom: 0px;">
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
</section>

<%-- <%@ include file="page2.file" %> --%> 

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

</body>
</html>