<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_order.model.*"%>

<%
  Product_orderVO product_orderVO = (Product_orderVO) request.getAttribute("product_orderVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品訂單修改 - update_Product_order_input.jsp</title>

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
		 <h3>商品訂單修改 - update_Product_order_input.jsp</h3>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product_order/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0">	</a></h4>
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

<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order/product_order.do" name="form1">
<table>
	<tr>
		<td>商品訂單編號:<font color=red><b>*</b></font></td>
		<td>${product_orderVO.prod_ord_no}</td>
	</tr>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td>${product_orderVO.mbr_no}</td>
	</tr>
<tr>
		<td>下訂時間:</td>	
		<td><input type="TEXT" name="prod_cat_no" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getProd_ord_time()%>" /></td>
	</tr>
	<tr>
		<td>訂單狀態:</td>
		<td>
			<select id="prod_stat" name="prod_stat">
				<option value="0">未付款</option>
				<option value="1">已付款</option>
				<option value="2">出貨中</option> 
				<option value="3">已收貨</option> 
				<option value="4">未取貨</option>
			</select>
		</td>
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
		<td>
			<select id="ship_meth" name="ship_meth">
			    <option disabled value="">請選擇運送方式</option>
			    <option value="0">不限運送方式</option>
			    <option value="1">限宅配</option>
			    <option value="2">限超商取貨</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>付款方式:</td>
		<td><input type="TEXT" name="pay_meth" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getPay_meth()%>" /></td>
	</tr>
	<tr>
		<td>運送地址_縣市:</td>
		<td><input type="TEXT" name="ship_cty" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getShip_cty()%>" /></td>
	</tr>
	<tr>
		<td>運送地址_區域:</td>
		<td><input type="TEXT" name="ship_dist" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getShip_dist()%>" /></td>
	</tr>
	<tr>
		<td>運送地址:</td>
		<td><input type="TEXT" name="ship_add" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getShip_add()%>" /></td>
	</tr>
	<tr>
		<td>發票形式:</td>
		<td><input type="TEXT" name="receipt" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getReceipt()%>" /></td>

	</tr>
	<tr>
		<td>訂單備註:</td>
		<td><input type="TEXT" name="rmk" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getRmk()%>" /></td>

	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="prod_ord_no" value="<%=product_orderVO.getProd_ord_no()%>">
<input type="submit" value="送出修改"></FORM>
</body>

</html>