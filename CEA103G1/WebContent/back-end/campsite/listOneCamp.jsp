<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite.model.*"%>
<%@ page import="com.place.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	CampVO campVO = (CampVO) request.getAttribute("campVO");
%>
<jsp:useBean id="placeSvc" scope="page"
	class="com.place.model.PlaceService" />
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
table, th, td {
	border: 0.1px solid black;
	background-color: white;
	text-align: center;
}

#config {
	width: 150px;
	height: 100px;
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
				<div style="display: inline-block;">
					<h3>營區資料</h3>
<!-- 					<button>修改</button> -->
					<table>
						<tr>
							<th>營主編號</th>
							<td>${campVO.cso_no}</td>
						</tr>
						<tr>
							<th>營區名稱</th>
							<td>${campVO.camp_name}</td>
						</tr>
						<tr>
							<th>營區資訊</th>
							<td>${campVO.campInfo}</td>
						</tr>
						<tr>
							<th>注意事項</th>
							<td>${campVO.note}</td>
						</tr>
						<tr>
							<th>配置圖</th>
							<td><img id="config"
								src="<%=request.getContextPath()%>/camp/campconfig.do?camp_no=${campVO.camp_no}"></td>
						</tr>
						<tr>
							<th>海拔</th>
							<td>${campVO.height}</td>
						</tr>
						<tr>
							<th>無線通訊</th>
							<td>${campVO.wireless}</td>
						</tr>
						<tr>
							<th>寵物</th>
							<c:if test="${campVO.pet==0}">
								<td><c:out value="不可攜帶寵物" /></td>
							</c:if>
							<c:if test="${campVO.pet==1}">
								<td><c:out value="可攜帶寵物" /></td>
							</c:if>
						</tr>
						<tr>
							<th>營區設施</th>
							<td>${campVO.facility}</td>
						</tr>
						<tr>
							<th>營業日</th>
							<td>${(campVO.operate_Date >= 1) ? ((campVO.operate_Date ==2) ? "平假日" : "假日") : "平日"}</td>
						</tr>
						<tr>
							<th>停車方式</th>
							<td>${campVO.park}</td>
						</tr>
						<tr>
							<th>地址</th>
							<td>${campVO.address}</td>
						</tr>
						<tr>
							<th>緯經度</th>
							<td>${campVO.latitude},${campVO.longitude}</td>
						</tr>
					</table>
				</div>
				<div style="display: inline-block; margin-left: 50px;">
					<h3>營位資料</h3>
					<table>
						<tr>
							<th>營位名稱</th>
							<th>人數</th>
							<th>人數上限</th>
							<th>平日價格</th>
							<th>假日價格</th>
							<th>狀態</th>
						</tr>
						<c:forEach var="placeVO"
							items="${placeSvc.getByCamp(campVO.camp_no)}">
							<tr>
								<td>${placeVO.plc_name}</td>
								<td>${placeVO.ppl}</td>
								<td>${placeVO.max_ppl}</td>
								<td>${placeVO.pc_wkdy}</td>
								<td>${placeVO.pc_wknd}</td>
								<c:if test="${placeVO.open_stat==0}">
									<td><c:out value="不開放" /></td>
								</c:if>
								<c:if test="${placeVO.open_stat==1}">
									<td><c:out value="開放" /></td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
</body>
</html>