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
		if (place_orderVO.getCkin_stat() == 0 || place_orderVO.getCkin_stat() == 2) {
			list.add(place_orderVO);
		}
	}
	pageContext.setAttribute("list", list);
	Place_OrderVO place_orderVO = (Place_OrderVO) request.getAttribute("place_orderVO");
%>
<jsp:useBean id="campSvc" scope="page"
	class="com.campsite.model.CampService" />
	<jsp:useBean id="memberSvc" scope="page"
	class="com.member.model.MemberService" />
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>營位訂單管理</title>
<%@ include file="/part-of/partOfCampion_COwnerTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_COwnerLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>
<style>
body {
	background-color: #4e5452;
	color: #4e5452;
}

div.left {
	margin-top: 20px;
}

div.right {
	background-color: #fff;
	margin-top: 40px;
	padding: 50px 50px;
	border-radius: 5px;
}

a.content {
	color: #80c344;
}

a.content:hover {
	color: #4B7F52;
}

form {
	margin: 0px auto;
}

span {
	color: #80c344;
}
input.confirm{
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}
input.confirm:hover{
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}
th, td {
	text-align: left;
	/* 	border: 1px solid #4e5452; */
	padding: 10px 10px;
	border-bottom:solid 1px;
}
</style>
</head>
<body>
<%@ include file="/part-of/partOfCampion_COwnerTop_body.txt"%>

	<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
	<div class="container">
		<div class="row">
			<div class="left col-3">
				<%@ include file="/part-of/partOfCampion_COwnerLeft_body.txt"%></div>
			<div class="right col-9">
			<h3>現有訂單</h3>
				<div style="display: inline-block;">
					<a href="PresentPlace_order.jsp">現有訂單</a>
				</div>
				<div style="display: inline-block;">
					<a href="HistoryPlace_order.jsp">歷史訂單</a>
				</div>
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
					<c:forEach var="place_orderVO" items="${list}">
						<tr>
							<td>${place_orderVO.plc_ord_no}</td>
							<td>${memberSvc.getOneMember(place_orderVO.mbr_no).name}</td>
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
									<input type="submit" value="查看" class="confirm"> <input
										type="hidden" name="plc_ord_no"
										value="${place_orderVO.plc_ord_no}"> <input
										type="hidden" name="action" value="getOnePlaceOrder">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
		<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
</body>
</html>