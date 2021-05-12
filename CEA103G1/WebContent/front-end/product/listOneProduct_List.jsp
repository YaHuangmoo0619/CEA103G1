<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.product.model.*"%>

<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<title>�ӫ~�U�q</title>

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
		 <h3>�ӫ~�U�q</h3>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />


<!-- 
<form action="" method="post">
  <table >
  <tr>
    <td>�ӫ~1</td><td>�ʶR�ƶq�G</td>
    <td><input type="button" value="+" onclick="this.form.c1.value++"/>
    <input type="text" name="c1"value=0 size="4"/>
    <input type="button"  value="-" onclick="this.form.c1.value--"/></td>
  </tr>
 <tr>
    <td>�ӫ~2</td><td>�ʶR�ƶq�G</td>
    <td><input type="button" value="+" onclick="this.form.c2.value++"/>
    <input type="text" name="c2"value=0 size="4"/>
    <input type="button"  value="-" onclick="this.form.c2.value--"/></td>
  </tr>
  <tr>
    <td>�ӫ~3</td><td>�ʶR�ƶq�G</td>
    <td><input type="button" value="+" onclick="this.form.c3.value++"/>
    <input type="text" name="c3"value=0 size="4"/>
    <input type="button"  value="-" onclick="this.form.c3.value--"/></td>
  </tr>
     </table>
     <input type="submit" value="�x�s"/>    
</form> 

 -->

<table>
	<tr>
		<th>�ʶR�ӫ~�s��</th>
		<th>�ӫ~�W��</th>
		<th>�ӫ~�����W��</th>
		<th>�ʶR�ƶq</th>
		<th>�B�e�覡</th>
	</tr>
	<tr>
		<td>${productVO.prod_no}</td>
		<td>${productVO.prod_name}</td>
		<td>
		${product_categorySvc.getOneProduct_category(productVO.prod_cat_no).prod_cat_name}
		</td>
		<td>
		<input type="button" value="+" onclick="debugger;this.nextElementSibling.value++" />
    	<input type="text" name="amount" value=0 size="4"/>
    	<input type="button" value="-" onclick="this.previousElementSibling.value--"/>
    	</td>
		<td>
		<c:if test="${productVO.ship_meth==0}">
			<c:out value="�п�ܹB�e�覡" />
			<input  type="radio" name="ship_meth" value="1">�v�t
			<input  type="radio" name="ship_meth" value="2">�W�Ө��f
		</c:if>
		<c:if test="${productVO.ship_meth==1}">
			<c:out value="���v�t" />
		</c:if>
		<c:if test="${productVO.ship_meth==2}">
			<c:out value="���W�Ө��f" />
		</c:if>
		</td>
	</tr>
</table>



<!-- 
<script>
function del(){
var num=parseInt($('#quantity').text())-1;
if(num<1){
$('#quantity').text(1);
}else{
$('#quantity').text(num);
}
}
function add(){
var num=parseInt($('#quantity').text())+1;
$('#quantity').text(num);
}
</script>

<input type="button" value="-" id="del" onclick="del()"/>
<span id="quantity">1</span>
<input type="button" value="+" id="add" onclick="add()"/>

 -->

</body>
</html>