<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
	Product_orderVO product_orderVO = (Product_orderVO) request.getAttribute("product_orderVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品訂單新增 - addProduct_order.jsp</title>

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
		 <h3>商品訂單新增 - addProduct_order.jsp</h3></td><td>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product_order/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order/product_order.do" name="form1">
<table>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="mbr_no" size="45" 
			 value="<%= (memberVO==null)? "" : memberVO.getName()%>" /></td>
	</tr>
	<tr>
		<td>下訂時間:</td>
		<td><input type="TEXT" name="prod_ord_time" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getProd_ord_time()%>" /></td>
	</tr>
	<tr>
		<td>下訂商品:</td>
		<td><%= productVO.getProd_name() %></td>
	</tr>
	<tr>
		<td>訂單總金額:</td>
		<td><input type="TEXT" name="prod_ord_sum" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getProd_ord_sum()%>" /></td>
	</tr>
	<tr>
		<td>使用點數:</td>
		<td><input type="TEXT" name="used_pt" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getUsed_pt()%>" /></td>
	</tr>
	<tr>
		<td>運送方式:</td>
		<td><input type="TEXT" name="ship_meth" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getMbr_no()%>" /></td>
	</tr>
	<tr>
		<td>付款方式:</td>
		<td><input type="TEXT" name="pay_meth" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getMbr_no()%>" /></td>
	</tr>
	<tr>
		<td>運送地址_縣市:</td>
		<td><input type="TEXT" name="ship_cty" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getMbr_no()%>" /></td>
	</tr>
	<tr>
		<td>運送地址_區域:</td>
		<td><input type="TEXT" name="ship_dist" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getMbr_no()%>" /></td>
	</tr>
	<tr>
		<td>運送地址:</td>
		<td><input type="TEXT" name="ship_add" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getMbr_no()%>" /></td>
	</tr>
	<tr>
		<td>發票形式:</td>
		<td><input type="TEXT" name="receipt" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getMbr_no()%>" /></td>
	</tr>
	<tr>
		<td>訂單備註:</td>
		<td><input type="TEXT" name="rmk" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getMbr_no()%>" /></td>
	</tr>

</table>
<br>

<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>

</html>