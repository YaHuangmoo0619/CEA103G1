<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
div.L{
	display:inline-block;
	width:6em;
	font-weight: 555;
}
div.R{
	display:inline-block;
	width:4em;
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
</style>
</head>
<body>
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
					<div class="L">訂單總金額:</div>
					<div class="R">${product_orderVO.prod_ord_sum}元</div>
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
					
			</div>
			
			<div style="width:45%;" class="inTr">
				<div class="inTr">
					<div class="L">付款方式:</div>
					<div>
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
					<div>
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
					<c:set var="prod_ord_time" value="${product_orderVO.prod_ord_time}" />
					<div>${fn:substring(prod_ord_time, 0, 19)}</div>
				</div>
				<div class="inTr">
					<div class="L">訂單備註:</div>
					<div>${product_orderVO.rmk}</div>
				</div>
			</div>
			
			<div style="width:15%;" class="inTr">
				<div>
					<label for="prod_ord_stat0" ${product_orderVO.prod_ord_stat==0?'class="spotlight"':''}>未付款</label>
					<input type="radio" id="prod_ord_stat0" value="0" ${product_orderVO.prod_ord_stat==0?'checked':''} disabled><br>
					<label for="prod_ord_stat1" ${product_orderVO.prod_ord_stat==1?'class="spotlight"':''}>已付款</label>
					<input type="radio" id="prod_ord_stat1" value="1" ${product_orderVO.prod_ord_stat==1?'checked':''} disabled><br>
					<label for="prod_ord_stat2" ${product_orderVO.prod_ord_stat==2?'class="spotlight"':''}>出貨中</label>
					<input type="radio" id="prod_ord_stat2" value="2" ${product_orderVO.prod_ord_stat==2?'checked':''} disabled><br>
					<label for="prod_ord_stat3" ${product_orderVO.prod_ord_stat==3?'class="spotlight"':''}>已收貨</label>
					<input type="radio" id="prod_ord_stat3" value="3" ${product_orderVO.prod_ord_stat==3?'checked':''} disabled><br>
					<label for="prod_ord_stat4" ${product_orderVO.prod_ord_stat==4?'class="spotlight"':''}>未取貨</label>
					<input type="radio" id="prod_ord_stat4" value="4" ${product_orderVO.prod_ord_stat==4?'checked':''} disabled><br>
				</div>
			</div>
		</div>
</body>
</html>