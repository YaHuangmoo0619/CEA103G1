<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Product: Home</title>

<%@ include file="/part-of/partOfCampion_COwnerTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_COwnerLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Product: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Product: Home</p>

<h3>資料查詢:</h3>
	
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='${pageContext.request.contextPath}/front-end/product/listAllProduct.jsp'>List</a> all Product.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" >
        <b>輸入商品編號 :</b>
        <input type="text" name="prod_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
   
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" >
       <b>選擇商品編號:</b>
       <select size="1" name="prod_no">
         <c:forEach var="productVO" items="${productSvc.all}" > 
          <option value="${productVO.prod_no}">${productVO.prod_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" >
       <b>選擇商品名稱:</b>
       <select size="1" name="prod_no">
         <c:forEach var="productVO" items="${productSvc.all}" > 
          <option value="${productVO.prod_no}">${productVO.prod_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>
 
 <jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />
 
<ul>  
  <li>   
    <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" name="form1">
        <b><font color=blue>萬用複合查詢:</font></b> <br>
        <b>輸入商品編號:</b>
        <input type="text" name="prod_no" value=""><br>
           
       <b>輸入商品編號:</b>
       <input type="text" name="prod_name" value=""><br>
     
       <b>選擇商品類別編號:</b>
       <select size="1" name="prod_cat_no" >
          <option value="">
         <c:forEach var="product_categoryVO" items="${product_categorySvc.all}" > 
          <option value="${product_categoryVO.prod_cat_no}">${product_categoryVO.prod_cat_name}
         </c:forEach>   
       </select><br>
		        
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listProduct_ByCQ">
     </FORM>
  </li>
</ul> 

<h3>商品管理</h3>

<ul>
  <li><a href='${pageContext.request.contextPath}/back-end/product/addProduct.jsp'>Add</a> a new Product.</li>
</ul>

</body>
</html>