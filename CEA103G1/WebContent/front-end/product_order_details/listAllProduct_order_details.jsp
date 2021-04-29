<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_order_details.model.*"%>

<%
    Product_order_detailsService product_order_detailsSvc = new Product_order_detailsService();
    List<Product_order_detailsVO> list = product_order_detailsSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有商品訂單明細 </title>

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

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>所有商品訂單明細 </h3>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product_order_details/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0"></a></h4>
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

<table>
	<tr>
		<th>商品訂單明細編號</th>
		<th>商品編號</th>
	</tr>
<%-- <%@ include file="page1.file" %> --%>
	<c:forEach var="product_order_detailsVO" items="${list}" > <%-- begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" --%>
		
		
		<tr>
			<td>${product_order_detailsVO.prod_ord_no}</td>
			<td>${product_order_detailsVO.prod_no}</td>
			<td>
			  <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order_details/product_order_details.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="prod_ord_no"  value="${product_order_detailsVO.prod_ord_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order_details/product_order_details.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="prod_ord_no"  value="${product_order_detailsVO.prod_ord_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%> 

</body>
</html>