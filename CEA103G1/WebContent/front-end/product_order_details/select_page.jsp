<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Product_order_details: Home</title>

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
   <tr><td><h3>Product_order_details: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Product_order_details: Home</p>

<h3>��Ƭd��:</h3>
	
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='${pageContext.request.contextPath}/front-end/product_order_details/listAllProduct_order_details.jsp'>List</a> all Product_order_details.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order_details/product_order_details.do" >
        <b>��J�ӫ~�q��s�� :</b>
        <input type="text" name="prod_ord_no">
        <b>��J�ӫ~�s�� :</b>
        <input type="text" name="prod_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="product_order_detailsSvc" scope="page" class="com.product_order_details.model.Product_order_detailsService" />
   
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order_details/product_order_details.do" >
       <b>��ܰӫ~�q��s��:</b>
       <select size="1" name="prod_ord_no">
         <c:forEach var="product_order_detailsVO" items="${product_order_detailsSvc.all}" > 
          <option value="${product_order_detailsVO.prod_ord_no}">${product_order_detailsVO.prod_ord_no}
         </c:forEach>   
       </select>
       <b>��ܰӫ~�s��:</b>
       <select size="1" name="prod_no">
         <c:forEach var="product_order_detailsVO" items="${product_order_detailsSvc.all}" > 
          <option value="${product_order_detailsVO.prod_no}">${product_order_detailsVO.prod_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
</ul>


<h3>�ӫ~�q����Ӻ޲z</h3>

<ul>
  <li><a href='${pageContext.request.contextPath}/front-end/product_order_details/addProduct_order_details.jsp'>Add</a> a new Product_order_details.</li>
</ul>

</body>
</html>