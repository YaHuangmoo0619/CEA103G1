<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_order.model.*"%>

<%
    Product_orderService product_orderSvc = new Product_orderService();
    List<Product_orderVO> list = product_orderSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有商品訂單 </title>

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

<!-- <div style="background-color: #eee;">
	<img src="/CEA103G1/front-images/campionLogoLong.png" class="logo">
	<form class="form-inline my-2 my-lg-0">
		<input class="form-control mr-sm-2" type="search"
			placeholder="營位/商品/文章" aria-label="Search">
		<button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜尋</button>
	</form>
	<img src="/CEA103G1/front-images/search-circle-outline.svg"
		class="searchIcon" onclick="showSearch()">
	<div class="btn-group" role="group" aria-label="Basic example">
		<button type="button" class="btn btn-secondary">營區</button>
		<button type="button" class="btn btn-secondary">商城</button>
		<button type="button" class="btn btn-secondary">論壇</button>
	</div>
	<img src="/CEA103G1/front-images/cart-outline.svg" class="cart">
	<div class="btn-group" role="group" aria-label="Basic example">
		<button type="button" class="btn btn-outline-secondary">註冊</button>
		<button type="button" class="btn btn-outline-secondary">登入</button>
		<button type="button" class="btn btn-outline-secondary">FAQ</button>
		<button type="button" class="btn btn-outline-secondary">聯絡我們</button>
	</div>
	<img src="/CEA103G1/front-images/menu-outline.svg" class="menu"
		onclick="showMenu()"> <img
		src="/CEA103G1/front-images/person-circle-outline.svg" class="person">
</div> -->

<table id="table-1">
	<tr><td>
		 <h3>所有商品訂單 </h3>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product_order/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0"></a></h4>
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
<%-- <%@ include file="page1.file" %> --%>
	<c:forEach var="product_orderVO" items="${list}" > <%-- begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" --%>
		
		
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
			  <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order/product_order.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="prod_ord_no"  value="${product_orderVO.prod_ord_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order/product_order.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="prod_ord_no"  value="${product_orderVO.prod_ord_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
	
</table>
<%-- <%@ include file="page2.file" %> --%> 

</body>
</html>