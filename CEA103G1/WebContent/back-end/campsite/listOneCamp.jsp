<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	CampVO campVO = (CampVO) request.getAttribute("campVO");
%>

<html>
<head>
<title>營區資料</title>

<style>
img {
	width: 150px;
	height: 100px;
}
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
<body bgcolor='white'>
	<div>
		<h1>營區資料</h1>
		<h3>
			<a href="<%=request.getContextPath()%>/back-end/campsite/listAllCamp.jsp">回清單</a>
		</h3>
	</div>
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
			<td><img
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
			<td>${campVO.latitude} , ${campVO.longitude}</td>
		</tr>
	</table>

</body>
</html>