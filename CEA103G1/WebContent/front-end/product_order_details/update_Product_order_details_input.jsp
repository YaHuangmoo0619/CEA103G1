<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_order_details.model.*"%>

<%
  Product_order_detailsVO product_order_detailsVO = (Product_order_detailsVO) request.getAttribute("product_order_detailsVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品訂單明細修改 - update_Product_order_details_input.jsp</title>

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
		 <h3>商品訂單明細修改 - update_Product_order_details_input.jsp</h3>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product_order_details/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0">	</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order_details/product_order_details.do" name="form1">
<table>
	<tr>
		<td>商品訂單明細編號:<font color=red><b>*</b></font></td>
		<td><%=product_order_detailsVO.getProd_ord_no()%></td>
	</tr>
	<tr>
		<td>商品編號:</td>
		<td><input type="TEXT" name="ename" size="45" value="<%=product_order_detailsVO.getProd_no()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="prod_ord_no" value="<%=product_order_detailsVO.getProd_ord_no()%>">
<input type="submit" value="送出修改"></FORM>
</body>

</html>