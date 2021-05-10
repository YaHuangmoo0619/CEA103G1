<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.place_order.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

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
<title>營區資料</title>
<%@ include file="/part-of/partOfCampion_COwnerTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_COwnerLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>
<style>
.confirm {
	background-color: #5599FF;
	color: #000088;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}

.confirm:hover {
	background-color: #000088;
	color: #5599FF;
	cursor: pointer;
}

.not {
	background-color: #FF3333;
	color: #880000;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}

.not:hover {
	background-color: #AA0000;
	color: #FF0000;
	cursor: pointer;
}

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
	/*自動斷行*/
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
				<h3>訂單資訊</h3>
				<table>
					<tr>
						<th>編號:</th>
						<td>${place_orderVO.plc_ord_no}</td>
					</tr>
					<tr>
						<th>會員:</th>
						<td>${memberSvc.getOneMember(place_orderVO.mbr_no).name}</td>
					</tr>
					<tr>
						<th>營區:</th>
						<td>${campSvc.getOneCamp(place_orderVO.camp_no).camp_name}</td>
					</tr>
					<tr>
						<th>訂單時間:</th>
						<td>${place_orderVO.plc_ord_time}</td>
					</tr>
					<tr>
						<th>入住日期:</th>
						<td>${place_orderVO.ckin_date}</td>
					</tr>
					<tr>
						<th>退房日期:</th>
						<td>${place_orderVO.ckout_date}</td>
					</tr>
					<tr>
						<th>營位數量:</th>
						<td>${place_orderVO.plc_amt}</td>
					</tr>
					<tr>
						<th>總金額:</th>
						<td>${place_orderVO.plc_ord_sum}</td>
					</tr>
					<tr>
						<th>加購人數:</th>
						<td>${place_orderVO.ex_ppl}</td>
					</tr>
					<tr>
						<th>付款方式:</th>
						<td>${(place_orderVO.pay_meth == 0) ?"信用卡" : "匯款"}</td>
					</tr>
					<tr>
						<th>付款狀態:</th>
						<c:if test="${place_orderVO.pay_stat==0}">
							<td><c:out value="未付款" /></td>
						</c:if>
						<c:if test="${place_orderVO.pay_stat==1}">
							<td><c:out value="已付訂金" /></td>
						</c:if>
						<c:if test="${place_orderVO.pay_stat==2}">
							<td><c:out value="已付全額" /></td>
						</c:if>
					</tr>
					<tr>
						<th>使用點數:</th>
						<td>${place_orderVO.used_pt}</td>
					</tr>
					<tr>
						<th>入住狀態:</th>
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
						<c:if test="${place_orderVO.ckin_stat==4}">
							<td><c:out value="已取消" /></td>
						</c:if>
					</tr>
					<tr>
						<th>發票:</th>
						<c:if test="${place_orderVO.receipt==0}">
							<td><c:out value="紙本發票" /></td>
						</c:if>
						<c:if test="${place_orderVO.receipt==1}">
							<td><c:out value="電子發票" /></td>
						</c:if>
						<c:if test="${place_orderVO.receipt==2}">
							<td><c:out value="發票捐贈" /></td>
						</c:if>
					</tr>
					<tr>
						<th>備註:</th>
						<td>${place_orderVO.rmk}</td>
					</tr>
				</table>
				<br>
				<c:if test="${place_orderVO.ckin_stat==0}">
					<div style="display: inline-block;">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/place_order/place_order.do">
							<button class="not">
								<c:out value="取消訂單" />
							</button>
							<input type="hidden" name=action value="update"> <input
								type="hidden" name="plc_ord_no"
								value="${place_orderVO.plc_ord_no}"> <input
								type="hidden" name="ckin_stat" value="4">
						</FORM>
					</div>
					<div style="display: inline-block;">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/place_order/place_order.do">
							<button class="not">
								<c:out value="未出席" />
							</button>
							<input type="hidden" name=action value="update"> <input
								type="hidden" name="plc_ord_no"
								value="${place_orderVO.plc_ord_no}"> <input
								type="hidden" name="ckin_stat" value="1">
						</FORM>
					</div>
					<div style="display: inline-block;">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/place_order/place_order.do">
							<button class="confirm">
								<c:out value="CKECK IN" />
							</button>
							<input type="hidden" name=action value="update"> <input
								type="hidden" name="plc_ord_no"
								value="${place_orderVO.plc_ord_no}"> <input
								type="hidden" name="ckin_stat" value="2">
						</FORM>
					</div>
				</c:if>
				<c:if test="${place_orderVO.ckin_stat==2}">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/place_order/place_order.do">
						<button class="confirm">
							<c:out value="CKECK OUT" />
						</button>
						<input type="hidden" name=action value="update"> <input
							type="hidden" name="plc_ord_no"
							value="${place_orderVO.plc_ord_no}"> <input type="hidden"
							name="ckin_stat" value="3">
					</FORM>
				</c:if>
			</div>
		</div>
	</div>
	<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
</body>
</html>