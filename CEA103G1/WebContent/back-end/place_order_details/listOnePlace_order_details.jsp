<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.place_order_details.model.*"%>
<%@ page import="com.place_order.model.*"%>

<%
	List<Place_Order_DetailsVO> list = (List) request.getAttribute("place_order_detailsVO");
	pageContext.setAttribute("list", list);
	Place_OrderService place_orderSvc = new Place_OrderService();
	pageContext.setAttribute("place_orderSvc", place_orderSvc);
	Calendar cal = new GregorianCalendar();
	pageContext.setAttribute("cal", cal);
%>
<jsp:useBean id="placeSvc" scope="page"
	class="com.place.model.PlaceService" />
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>訂單明細</title>
<style>
table, th, td {
	border: 1px solid black;
	background-color: white;
	text-align: center;
}

div {
	background-color: lightgreen;
}
</style>
</head>
<body>
	<div>
		<h1>訂單明細</h1>
		<h3>
			<a href="select_page.jsp">回首頁</a>
		</h3>
	</div>
	<table>
		<tr>
			<th>訂單編號</th>
			<th>營位名稱</th>
			<th>營位金額</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="place_order_detailsVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<%--             ${place_orderSvc.getOnePlace_Order(place_order_detailsVO.plc_ord_no).ckin_date} --%>
			<tr>
				<td>${place_order_detailsVO.plc_ord_no}</td>
				<td><c:forEach var="placeVO" items="${placeSvc.all}">
						<c:if test="${place_order_detailsVO.plc_no==placeVO.plc_no}">
	                    ${placeVO.plc_name}
                    </c:if>
					</c:forEach></td>
<%-- 					<td>${cal.set(121,3,16).get(Calendar.DAY_OF_WEEK)}</td> --%>
<%-- 					<td>${placeSvc.getOnePlace(place_order_detailsVO.plc_no).pc_wknd} </td> --%>
<%-- 					<td>${placeSvc.getOnePlace(place_order_detailsVO.plc_no).pc_wkdy} </td> --%>
				<%-- 					<td>${place_orderSvc.getOnePlace_Order(place_order_detailsVO.plc_ord_no).ckin_date.getYear()}</td> --%>
				<%-- 					<td>${place_orderSvc.getOnePlace_Order(place_order_detailsVO.plc_ord_no).ckin_date.getMonth()}</td> --%>
				<%-- 					<td>${place_orderSvc.getOnePlace_Order(place_order_detailsVO.plc_ord_no).ckin_date.getDate()}</td> --%>
				<td>
					<%-- 									${place_orderSvc.getOnePlace_Order(place_order_detailsVO.plc_ord_no).ckin_date} --%>
					<c:if
						test="${cal.setTime(place_orderSvc.getOnePlace_Order(place_order_detailsVO.plc_ord_no).ckin_date).get(Calendar.DAY_OF_WEEK)>=6}">
 						${placeSvc.getOnePlace(place_order_detailsVO.plc_no).pc_wknd} 
 						</c:if> <c:if
						test="${cal.setTime(place_orderSvc.getOnePlace_Order(place_order_detailsVO.plc_ord_no).ckin_date).get(Calendar.DAY_OF_WEEK)<6}">
						${placeSvc.getOnePlace(place_order_detailsVO.plc_no).pc_wkdy} 
						</c:if>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/place_order_details/place_order_details.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="plc_ord_no" value="${place_order_detailsVO.plc_ord_no}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/place_order_details/place_order_details.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="plc_ord_no" value="${place_order_detailsVO.plc_ord_no}">
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
</body>
</html>