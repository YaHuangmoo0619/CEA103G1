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
		if (place_orderVO.getPay_stat() == 0 && place_orderVO.getCkin_stat() == 0) {
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
<title>���q��޲z</title>
<%@ include file="/part-of/partOfCampion_backTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_backLeft_css.txt"%>
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
	padding: 10px 15px;
}
</style>
</head>
<body>
<%@ include file="/part-of/partOfCampion_backTop_body.txt"%>

	<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
	<div class="container">
		<div class="row">
			<div class="left col-3">
				<%@ include file="/part-of/partOfCampion_backLeft_body.txt"%></div>
			<div class="right col-9">
			<h3>���I�ڭq��</h3>
				<div style="display: inline-block;">
					<a href="<%=request.getContextPath()%>/back-end/campsite/UnreviewCamp.jsp">���f�����</a>
				</div>
				<div style="display: inline-block;">
					<a href="<%=request.getContextPath()%>/back-end/campsite/ReviewedCamp.jsp">�w�f�����</a>
				</div>
				<div style="display: inline-block;">
					<a href="UnpayPlace_order.jsp">���I�ڭq��</a>
				</div>
				<div style="display: inline-block;">
					<a href="PaidPlace_order.jsp">�w�I�ڭq��</a>
				</div>
				<table>
					<tr>
						<th>�q��s��</th>
						<th>�|���m�W</th>
						<th>��ϦW��</th>
						<th>�J�����</th>
						<th>�h�Ф��</th>
						<th>�J�����B</th>
						<th>�I�ڤ覡</th>
						<th>�I�ڪ��A</th>
						<th>�J�����A</th>
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
								<td><c:out value="�H�Υd" /></td>
							</c:if>
							<c:if test="${place_orderVO.pay_meth==1}">
								<td><c:out value="�״�" /></td>
							</c:if>
							<c:if test="${place_orderVO.pay_stat==0}">
								<td><c:out value="���I��" /></td>
							</c:if>
							<c:if test="${place_orderVO.pay_stat==1}">
								<td><c:out value="�w�I�q��" /></td>
							</c:if>
							<c:if test="${place_orderVO.pay_stat==2}">
								<td><c:out value="�w�I���B" /></td>
							</c:if>
							<c:if test="${place_orderVO.ckin_stat==0}">
								<td><c:out value="���J��" /></td>
							</c:if>
							<c:if test="${place_orderVO.ckin_stat==1}">
								<td><c:out value="���X�u" /></td>
							</c:if>
							<c:if test="${place_orderVO.ckin_stat==2}">
								<td><c:out value="�w�J��" /></td>
							</c:if>
							<c:if test="${place_orderVO.ckin_stat==3}">
								<td><c:out value="�w�h��" /></td>
							</c:if>
							<c:if test="${place_orderVO.ckin_stat==4}">
								<td><c:out value="�w����" /></td>
							</c:if>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/place_order/place_order.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="�d��" class="confirm"> <input
										type="hidden" name="plc_ord_no"
										value="${place_orderVO.plc_ord_no}"> <input
										type="hidden" name="action" value="getOnePlaceOrderFromBack">
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