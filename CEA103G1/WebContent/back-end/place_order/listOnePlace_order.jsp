<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.place_order.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	Place_OrderVO place_orderVO = (Place_OrderVO) request.getAttribute("place_orderVO");
%>
<jsp:useBean id="memberSvc" scope="page"
	class="com.member.model.MemberService" />
<jsp:useBean id="campSvc" scope="page"
	class="com.campsite.model.CampService" />
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<link rel="icon"
	href="<%=request.getContextPath()%>/images/campionLogoIcon.png"
	type="image/png">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>��ϸ��</title>
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
table {
	border-collapse: collapse;
	width: 40%;
	/*�۰��_��*/
	word-wrap: break-word;
	table-layout: fixed;
	text-align: left;
}
</style>
</head>
<body bgcolor='white'>
	<%@ include file="/part-of/partOfCampion_COwnerTop_body.txt"%>

	<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
	<div class="container">
		<div class="row">
			<div class="left col-3">
				<%@ include file="/part-of/partOfCampion_COwnerLeft_body.txt"%></div>
			<div class="right col-9">
				<h3>�q���T</h3>
				<table>
					<tr>
						<th>�s��:</th>
						<td>${place_orderVO.plc_ord_no}</td>
					</tr>
					<tr>
						<th>�|��:</th>
						<td>${memberSvc.getOneMember(place_orderVO.mbr_no).name}</td>
					</tr>
					<tr>
						<th>���:</th>
						<td>${campSvc.getOneCamp(place_orderVO.camp_no).camp_name}</td>
					</tr>
					<tr>
						<th>�q��ɶ�:</th>
						<td>${place_orderVO.plc_ord_time}</td>
					</tr>
					<tr>
						<th>�J����:</th>
						<td>${place_orderVO.ckin_date}</td>
					</tr>
					<tr>
						<th>�h�Ф��:</th>
						<td>${place_orderVO.ckout_date}</td>
					</tr>
					<tr>
						<th>���ƶq:</th>
						<td>${place_orderVO.plc_amt}</td>
					</tr>
					<tr>
						<th>�`���B:</th>
						<td>${place_orderVO.plc_ord_sum}</td>
					</tr>
					<tr>
						<th>�[�ʤH��:</th>
						<td>${place_orderVO.ex_ppl}</td>
					</tr>
					<tr>
						<th>�I�ڤ覡:</th>
						<td>${(place_orderVO.pay_meth == 0) ?"�H�Υd" : "�״�"}</td>
					</tr>
					<tr>
						<th>�I�ڪ��A:</th>
						<c:if test="${place_orderVO.pay_stat==0}">
							<td><c:out value="���I��" /></td>
						</c:if>
						<c:if test="${place_orderVO.pay_stat==1}">
							<td><c:out value="�w�I�q��" /></td>
						</c:if>
						<c:if test="${place_orderVO.pay_stat==2}">
							<td><c:out value="�w�I���B" /></td>
						</c:if>
					</tr>
					<tr>
						<th>�ϥ��I��:</th>
						<td>${place_orderVO.used_pt}</td>
					</tr>
					<tr>
						<th>�J���A:</th>
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
					</tr>
					<tr>
						<th>�o��:</th>
						<td>${place_orderVO.receipt}</td>
					</tr>
					<tr>
						<th>�Ƶ�:</th>
						<td>${place_orderVO.rmk}</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
</body>
</html>