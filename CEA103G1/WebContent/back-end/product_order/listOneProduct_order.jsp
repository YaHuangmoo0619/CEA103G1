<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.product_order_details.model.*"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="product_order_detailsSvc" class="com.product_order_details.model.Product_order_detailsService"/>
<jsp:useBean id="product_orderSvc" class="com.product_order.model.Product_orderService"/>
<jsp:useBean id="productSvc" class="com.product.model.ProductService"/>

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
div.L{
	display:inline-block;
	width:6em;
	font-weight: 555;
}
div.R{
	display:inline-block;
	width:6em;
}
div.inTr{
	display:inline-block;
}
label.spotlight{
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}
div.out{
	width:90%;
	margin: 0 auto;
}
label.stat{
	margin-left:3em;
	font-size:0.9em;
}
</style>
</head>
<body>
	<div class="out">
		<div style="display:flex;">
			<div style="width:5%;" class="inTr">
				${product_orderVO.prod_ord_no}
			</div>
			<div style="width:35%;" class="inTr">
				<div class="inTr">
					<div class="L">會員編號:</div>
					<div class="R">${product_orderVO.mbr_no}</div>
				</div>
				<div class="inTr">
					<div class="L">使用點數:</div>
					<div class="R">${product_orderVO.used_pt}</div>
				</div>
				<div class="inTr">
					<div class="L">運送方式:</div>
					<div class="R">
						<c:if test="${product_orderVO.ship_meth==1}">
							<c:out value="宅配" />
						</c:if>
						<c:if test="${product_orderVO.ship_meth==2}">
							<c:out value="超商取貨" />
						</c:if>
					</div>
				</div>
				<div class="inTr">
					<div class="L">付款方式:</div>
					<div class="R">
						<c:if test="${product_orderVO.pay_meth==0}">
							<c:out value="信用卡" />
						</c:if>
						<c:if test="${product_orderVO.pay_meth==1}">
							<c:out value="匯款" />
						</c:if>
						<c:if test="${product_orderVO.pay_meth==2}">
							<c:out value="超商取貨付款" />
						</c:if>
					</div>
				</div>
				<div class="inTr">
					<div class="L">發票形式:</div>
					<div class="R">
						<c:if test="${product_orderVO.receipt==0}">
							<c:out value="紙本發票" />
						</c:if>
						<c:if test="${product_orderVO.receipt==1}">
							<c:out value="電子發票" />
						</c:if>
						<c:if test="${product_orderVO.receipt==2}">
							<c:out value="發票捐贈" />
						</c:if>
					</div>
				</div>	
			</div>
			
			<div style="width:55%;" class="inTr">
				<div class="inTr">
					<div class="L">運送地址:</div>
					<div>
						<div class="innerDiv">${product_orderVO.ship_cty}</div>
						<div class="innerDiv">${product_orderVO.ship_dist}</div>
						<div class="innerDiv">${product_orderVO.ship_add}</div>
					</div>
				</div>
				<div class="inTr">
					<div class="L">下訂時間:</div>
<%-- 					<c:set var="prod_ord_time" value="${product_orderVO.prod_ord_time}" /> --%>
<%-- 					<div>${fn:substring(prod_ord_time, 0, 19)}</div> --%>
					<div><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${product_orderVO.prod_ord_time}"/></div>
				</div><br>
				<div class="inTr">
					<div class="L">訂單備註:</div>
					<div style="display:inline-block;width:8em;">${product_orderVO.rmk}</div>
				</div>
			</div>
		</div>
		<hr style="margin: 20 auto;">
		<div class="inTr">
		<c:forEach var="product_order_detailsVO" items="${product_order_detailsSvc.getByProd_ord_no(product_orderVO.getProd_ord_no())}">
		<div class="innerDiv L">商品名稱:</div>
		<div class="innerDiv" style="margin-right:1em;">${productSvc.getOneProduct(product_order_detailsVO.getProd_no()).getProd_name()}</div>
		<div class="innerDiv L">商品數量:</div>
		<div class="innerDiv" style="margin-right:1em;">${product_order_detailsVO.getProd_amt()}件</div>
		<div class="innerDiv L">商品單價:</div>
		<div class="innerDiv" style="margin-right:1em;">${product_order_detailsVO.getProd_unit_pc()}元</div>
		</c:forEach>
		<div class="inTr">
			<div class="L">訂單總金額:</div>
			<div class="R">${product_orderVO.prod_ord_sum}元</div>
		</div>
		</div>
		<hr style="margin: 20 auto;">
		<div class="inTr">
			<form id="prod_ord_stat" method="post" action="<%=request.getContextPath()%>/product_order/product_order.do" style="text-align:center;">
				<label for="prod_ord_stat0" class="stat">未付款</label>
				<input type="radio" id="prod_ord_stat0" name="prod_ord_stat" value="0" ${product_orderVO.prod_ord_stat==0?'checked':''} ${product_orderVO.prod_ord_stat > 0?'disabled':''}>
				<label for="prod_ord_stat1" class="stat">已付款</label>
				<input type="radio" id="prod_ord_stat1" name="prod_ord_stat" value="1" ${product_orderVO.prod_ord_stat==1?'checked':''} ${product_orderVO.prod_ord_stat > 1?'disabled':''}>
				<label for="prod_ord_stat2" class="stat">出貨中</label>
				<input type="radio" id="prod_ord_stat2" name="prod_ord_stat" value="2" ${product_orderVO.prod_ord_stat==2?'checked':''} ${product_orderVO.prod_ord_stat > 2?'disabled':''}>
				<label for="prod_ord_stat3" class="stat">已收貨</label>
				<input type="radio" id="prod_ord_stat3" name="prod_ord_stat" value="3" ${product_orderVO.prod_ord_stat==3?'checked':''} ${product_orderVO.prod_ord_stat > 3?'disabled':''}>
				<label for="prod_ord_stat4" class="stat">未取貨</label>
				<input type="radio" id="prod_ord_stat4" name="prod_ord_stat" value="4" ${product_orderVO.prod_ord_stat==4?'checked':''} ${product_orderVO.prod_ord_stat > 4?'disabled':''}>
				<label for="prod_ord_stat5" class="stat">取消訂單</label>
				<input type="radio" id="prod_ord_stat5" name="prod_ord_stat" value="5" ${product_orderVO.prod_ord_stat==5?'checked':''}>
				<input type="hidden" name="prod_ord_no" value="${product_orderVO.prod_ord_no}">
				<input type="hidden" name="action" value="update_order_stat">
				<input type="submit" class="btn btn-secondary" value="送出訂單狀態修改">
			</form>
		</div>
	</div>
</body>
</html>