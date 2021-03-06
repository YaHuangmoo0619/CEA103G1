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
<%@ include file="/part-of/partOfCampion_backTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_backLeft_css.txt"%>
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
th{
	width: 100px;
}
th, td {
	text-align: left;
	/* 	border: 1px solid #4e5452; */
	padding: 10px 10px;
	border-bottom:solid 1px;
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
	<%@ include file="/part-of/partOfCampion_backTop_body.txt"%>

	<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
	<div class="container">
		<div class="row">
			<div class="left col-3">
				<%@ include file="/part-of/partOfCampion_backLeft_body.txt"%></div>
			<div class="right col-9">
				<div style="display: inline-block; width:60%;">
					<h3>營區資料</h3>
					<c:if test="${campVO.review_Status==0}">
						<div style="display:inline-block;"><FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/camp/camp.do">
							<button class="confirm">
								<c:out value="通過" />
							</button>
							<input type="hidden" name=action value="updatereview"> <input
								type="hidden" name="camp_no"
								value="${campVO.camp_no}"> <input type="hidden"
								name="review_status" value="1">
						</FORM></div>
						<div style="display:inline-block;"><FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/camp/camp.do">
							<button class="not">
								<c:out value="不通過" />
							</button>
							<input type="hidden" name=action value="updatereview"> <input
								type="hidden" name="camp_no"
								value="${campVO.camp_no}"> <input type="hidden"
								name="review_status" value="2">
						</FORM></div>
					</c:if>
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
			</div>
		</div>
	</div>
	<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
</body>
</html>