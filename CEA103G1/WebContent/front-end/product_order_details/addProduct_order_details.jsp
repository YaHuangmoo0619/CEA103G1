<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_order.model.*"%>

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");  
	Product_orderVO product_orderVO = (Product_orderVO) request.getAttribute("product_orderVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�ӫ~�q����ӷs�W</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�ӫ~�q����ӷs�W - addProduct_order_details.jsp</h3></td><td>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product_order_details/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order_details/product_order_details.do" name="form1">
<table>
	<tr>
		<td>�q��s��:</td>
		<td><input type="TEXT" name="prod_ord_no" size="45" 
			 value="${product_orderVO.prod_ord_no}" /></td>
	</tr>
	<tr>
		<td>�ӫ~�s��:</td>
		<td><input type="TEXT" name="prod_no" size="45" 
			 value="${productVO.prod_no}" /></td>
	</tr>
	<tr>
		<td>�ӫ~�ƶq:</td>
		<td><input type="TEXT" name="prod_amt" size="45" 
			 value="${product_order_detailsVO.prod_amt}" /></td>
	</tr>
	<tr>
		<td>�ӫ~���:</td>  
		<td><input type="TEXT" name="prod_unit_pc" size="45" 
			 value="${product.prod_unit_pc}" /></td>
	</tr>



	<jsp:useBean id="product_orderSvc" scope="page" class="com.product_order.model.Product_orderService" />
	<tr>
		<td>�q��:<font color=red><b>*</b></font></td>
		<td>
		<select size="1" name="prod_ordno">
			<c:forEach var="product_orderVO" items="${product_orderSvc.all}">
				<option value="$product_orderVO.prod_ord_no}" ${(product_order_detailsVO.product_orderVO.prod_ord_no==product_orderVO.prod_ord_no)? 'selected':'' } >${product_orderVO.prod_ord_no}
			</c:forEach>
		</select>
		</td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>

</html>