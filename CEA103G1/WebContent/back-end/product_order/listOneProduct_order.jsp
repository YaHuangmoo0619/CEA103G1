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
<title>�ӫ~�q�� - listOneProduct_order.jsp</title>

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
					<div class="L">�|���s��:</div>
					<div class="R">${product_orderVO.mbr_no}</div>
				</div>
				<div class="inTr">
					<div class="L">�q���`���B:</div>
					<div class="R">${product_orderVO.prod_ord_sum}��</div>
				</div>
				<div class="inTr">
					<div class="L">�ϥ��I��:</div>
					<div class="R">${product_orderVO.used_pt}</div>
				</div>
				<div class="inTr">
					<div class="L">�B�e�覡:</div>
					<div class="R">
						<c:if test="${product_orderVO.ship_meth==1}">
							<c:out value="�v�t" />
						</c:if>
						<c:if test="${product_orderVO.ship_meth==2}">
							<c:out value="�W�Ө��f" />
						</c:if>
					</div>
				</div>
					
			</div>
			
			<div style="width:45%;" class="inTr">
				<div class="inTr">
					<div class="L">�I�ڤ覡:</div>
					<div>
						<c:if test="${product_orderVO.pay_meth==0}">
							<c:out value="�H�Υd" />
						</c:if>
						<c:if test="${product_orderVO.pay_meth==1}">
							<c:out value="�״�" />
						</c:if>
						<c:if test="${product_orderVO.pay_meth==2}">
							<c:out value="�W�Ө��f�I��" />
						</c:if>
					</div>
				</div>
				<div class="inTr">
					<div class="L">�o���Φ�:</div>
					<div>
						<c:if test="${product_orderVO.receipt==0}">
							<c:out value="�ȥ��o��" />
						</c:if>
						<c:if test="${product_orderVO.receipt==1}">
							<c:out value="�q�l�o��" />
						</c:if>
						<c:if test="${product_orderVO.receipt==2}">
							<c:out value="�o������" />
						</c:if>
					</div>
				</div>
				<div class="inTr">
					<div class="L">�B�e�a�}:</div>
					<div>
						<div class="innerDiv">${product_orderVO.ship_cty}</div>
						<div class="innerDiv">${product_orderVO.ship_dist}</div>
						<div class="innerDiv">${product_orderVO.ship_add}</div>
					</div>
				</div>
				<div class="inTr">
					<div class="L">�U�q�ɶ�:</div>
					<c:set var="prod_ord_time" value="${product_orderVO.prod_ord_time}" />
					<div>${fn:substring(prod_ord_time, 0, 19)}</div>
				</div>
				<div class="inTr">
					<div class="L">�q��Ƶ�:</div>
					<div>${product_orderVO.rmk}</div>
				</div>
			</div>
			
			<div style="width:15%;" class="inTr">
				<div>
					<label for="prod_ord_stat0" ${product_orderVO.prod_ord_stat==0?'class="spotlight"':''}>���I��</label>
					<input type="radio" id="prod_ord_stat0" value="0" ${product_orderVO.prod_ord_stat==0?'checked':''} disabled><br>
					<label for="prod_ord_stat1" ${product_orderVO.prod_ord_stat==1?'class="spotlight"':''}>�w�I��</label>
					<input type="radio" id="prod_ord_stat1" value="1" ${product_orderVO.prod_ord_stat==1?'checked':''} disabled><br>
					<label for="prod_ord_stat2" ${product_orderVO.prod_ord_stat==2?'class="spotlight"':''}>�X�f��</label>
					<input type="radio" id="prod_ord_stat2" value="2" ${product_orderVO.prod_ord_stat==2?'checked':''} disabled><br>
					<label for="prod_ord_stat3" ${product_orderVO.prod_ord_stat==3?'class="spotlight"':''}>�w���f</label>
					<input type="radio" id="prod_ord_stat3" value="3" ${product_orderVO.prod_ord_stat==3?'checked':''} disabled><br>
					<label for="prod_ord_stat4" ${product_orderVO.prod_ord_stat==4?'class="spotlight"':''}>�����f</label>
					<input type="radio" id="prod_ord_stat4" value="4" ${product_orderVO.prod_ord_stat==4?'checked':''} disabled><br>
				</div>
			</div>
		</div>
</body>
</html>