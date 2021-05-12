<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.product_order.model.*"%>

<%
  Product_orderVO product_orderVO = (Product_orderVO) request.getAttribute("product_orderVO");
%>

<html>
<head>
<title>商品訂單 - listOneProduct_order.jsp</title>

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
		 <h3>商品訂單 - ListOneProduct_order.jsp</h3>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product_order/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品訂單編號</th>
		<th>會員編號</th>
		<th>下訂時間</th>
		<th>訂單狀態</th>
		<th>訂單總金額</th>
		<th>使用點數</th>
		<th>運送方式</th>
		<th>付款方式</th>
		<th>運送地址_縣市</th>
		<th>運送地址_區域</th>
		<th>運送地址</th>
		<th>發票形式</th>
		<th>訂單備註</th>
	</tr>
	<tr>
		<td>${product_orderVO.prod_ord_no}</td>
			<td>${product_orderVO.mbr_no}</td>
			<td>${product_orderVO.prod_ord_time}</td>
			<td>
			<c:if test="${product_orderVO.prod_ord_stat==0}">
				<c:out value="未付款" />
			</c:if>
			<c:if test="${product_orderVO.prod_ord_stat==1}">
				<c:out value="已付款" />
			</c:if>
			<c:if test="${product_orderVO.prod_ord_stat==2}">
				<c:out value="出貨中" />
			</c:if>
			<c:if test="${product_orderVO.prod_ord_stat==3}">
				<c:out value="已收貨" />
			</c:if>
			<c:if test="${product_orderVO.prod_ord_stat==4}">
				<c:out value="未取貨" />
			</c:if>
			</td>
			<td>${product_orderVO.prod_ord_sum}</td>
			<td>${product_orderVO.used_pt}</td>
			<td>
			<c:if test="${product_orderVO.ship_meth==1}">
				<c:out value="宅配" />
			</c:if>
			<c:if test="${product_orderVO.ship_meth==2}">
				<c:out value="超商取貨" />
			</c:if>
			</td>
			<td>
			<c:if test="${product_orderVO.pay_meth==0}">
				<c:out value="信用卡" />
			</c:if>
			<c:if test="${product_orderVO.pay_meth==1}">
				<c:out value="匯款" />
			</c:if>
			<c:if test="${product_orderVO.pay_meth==2}">
				<c:out value="超商取貨付款" />
			</c:if>
			</td>
			<td>${product_orderVO.ship_cty}</td>
			<td>${product_orderVO.ship_dist}</td>
			<td>${product_orderVO.ship_add}</td>
			<td>
			<c:if test="${product_orderVO.receipt==0}">
				<c:out value="紙本發票" />
			</c:if>
			<c:if test="${product_orderVO.receipt==1}">
				<c:out value="電子發票" />
			</c:if>
			<c:if test="${product_orderVO.receipt==2}">
				<c:out value="發票捐贈" />
			</c:if>
			</td>
			<td>${product_orderVO.rmk}</td>
			<td>
	</tr>
</table>

</body>
</html>