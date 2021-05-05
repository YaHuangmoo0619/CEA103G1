<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.place_order.model.*"%>

<%
	Place_OrderService place_orderSvc = new Place_OrderService();
	List<Place_OrderVO> order_list = place_orderSvc.getAll();
	List<Place_OrderVO> list = new ArrayList();
	for (Place_OrderVO place_orderVO : order_list) {
		if (place_orderVO.getCkin_stat() == 0) {
			list.add(place_orderVO);
		}
	}
	pageContext.setAttribute("list", list);
	Place_OrderVO place_orderVO = (Place_OrderVO) request.getAttribute("place_orderVO");
%>
<jsp:useBean id="campSvc" scope="page"
	class="com.campsite.model.CampService" />
<!DOCTYPE html>
<html>
<head>
<title>營位訂單管理</title>
<style>
table, th, td {
	border: 0.1px solid black;
	background-color: white;
	text-align: center;
}

#plc_ord_bo {
	background-color: white;
	border: 0px;
}
</style>
</head>
<body>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<table>
		<tr>
			<th>訂單編號</th>
			<th>會員姓名</th>
			<th>營區名稱</th>
			<th>入住日期</th>
			<th>退房日期</th>
			<th>入住金額</th>
			<th>付款方式</th>
			<th>付款狀態</th>
			<th>使用點數</th>
			<th>入住狀態</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="place_orderVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${place_orderVO.plc_ord_no}</td>
				<td>${place_orderVO.mbr_no}</td>
				<td><c:forEach var="campVO" items="${campSvc.all}">
						<c:if test="${place_orderVO.camp_no==campVO.camp_no}">
	                    ${campVO.camp_name}
                    </c:if>
					</c:forEach></td>
				<td>${place_orderVO.ckin_date}</td>
				<td>${place_orderVO.ckout_date}</td>
				<td>${place_orderVO.plc_ord_sum}</td>
				<c:if test="${place_orderVO.pay_meth==0}">
					<td><c:out value="信用卡" /></td>
				</c:if>
				<c:if test="${place_orderVO.pay_meth==1}">
					<td><c:out value="匯款" /></td>
				</c:if>
				<c:if test="${place_orderVO.pay_stat==0}">
					<td><c:out value="未付款" /></td>
				</c:if>
				<c:if test="${place_orderVO.pay_stat==1}">
					<td><c:out value="已付訂金" /></td>
				</c:if>
				<c:if test="${place_orderVO.pay_stat==2}">
					<td><c:out value="已付全額" /></td>
				</c:if>
				<td>${place_orderVO.used_pt}</td>
				<c:if test="${place_orderVO.ckin_stat==0}">
					<td><c:out value="未入住" /></td>
				</c:if>
				<c:if test="${place_orderVO.ckin_stat==1}">
					<td><c:out value="未出席" /></td>
				</c:if>
				<c:if test="${place_orderVO.ckin_stat==2}">
					<td><c:out value="已入住" /></td>
				</c:if>
				<c:if test="${place_orderVO.ckin_stat==3}">
					<td><c:out value="已退房" /></td>
				</c:if>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/place_order/place_order.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="查看"> <input type="hidden"
							name="plc_ord_no" value="${place_orderVO.plc_ord_no}"> <input
							type="hidden" name="action" value="getOnePlaceOrder">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
</body>
</html>