<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Product: Home</title>

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
  <li><a href='${pageContext.request.contextPath}/front-end/product/listAllProduct.jsp'>List</a> all Product.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" >
        <b>��J�ӫ~�s�� :</b>
        <input type="text" name="prod_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
   
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" >
       <b>��ܰӫ~�s��:</b>
       <select size="1" name="prod_no">
         <c:forEach var="productVO" items="${productSvc.all}" > 
          <option value="${productVO.prod_no}">${productVO.prod_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" >
       <b>��ܰӫ~�W��:</b>
       <select size="1" name="prod_no">
         <c:forEach var="productVO" items="${productSvc.all}" > 
          <option value="${productVO.prod_no}">${productVO.prod_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>
<!--  
<ul>  
  <li>   
    <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" name="form1">
        <b><font color=blue>�U�νƦX�d��:</font></b> <br>
        <b>��J�ӫ~�s��:</b>
        <input type="text" name="prod_no" value=""><br>
           
       <b>��J�ӫ~���O�s��:</b>
       <input type="text" name="prod_cat_no" value=""><br>
     
       <b>��ܳ���:</b>
       <select size="1" name="deptno" >
          <option value="">
         <c:forEach var="deptVO" items="${deptSvc.all}" > 
          <option value="${deptVO.deptno}">${deptVO.dname}
         </c:forEach>   
       </select><br>
           
       <b>���Τ��:</b>
	   <input name="hiredate" id="f_date1" type="text">
		        
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="listEmps_ByCompositeQuery">
     </FORM>
  </li>
</ul> -->

<h3>�ӫ~�޲z</h3>

<ul>
  <li><a href='${pageContext.request.contextPath}/front-end/product/addProduct.jsp'>Add</a> a new Product.</li>
</ul>

</body>
</html>