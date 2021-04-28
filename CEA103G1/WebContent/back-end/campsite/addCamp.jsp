<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite.model.*"%>
<%@ page import="com.feature_list.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<%
	CampVO campVO = (CampVO) request.getAttribute("campVO");
	Feature_ListService feature_listSvc = new Feature_ListService();
	List<Feature_ListVO> list = feature_listSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<%-- <%=campVO == null%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
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
<body>
	<div>
		<h1>營區新增</h1>

		<h3>
			<a
				href="<%=request.getContextPath()%>/back-end/campsite/listAllCamp.jsp">回清單</a>
		</h3>
	</div>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camp/camp.do"
		name="form1" enctype="multipart/form-data" onsubmit="return false;">
		<table>
			<tr>
				<td>營主編號:</td>
				<td><input type="TEXT" name="cso_no" size="45"
					value="<%=(campVO == null) ? "70001" : campVO.getCso_no()%>" /></td>
			</tr>
			<tr>
				<td>營區名稱:</td>
				<td><input type="TEXT" name="camp_name" size="45"
					value="<%=(campVO == null) ? "" : campVO.getCamp_name()%>" /></td>
			</tr>
			<tr>
				<td>營區資訊:</td>
				<td><input type="TEXT" name="campInfo" size="45"
					value="<%=(campVO == null) ? "無" : campVO.getCampInfo()%>" /></td>
			</tr>
			<tr>
				<td>注意事項:</td>
				<td><input type="TEXT" name="note" size="45"
					value="<%=(campVO == null) ? "無" : campVO.getNote()%>" /></td>
			</tr>
			<tr>
				<td>配置圖:</td>
				<td><input type="file" name="config"><input
					type="hidden" name="image"
					value="<%=(campVO == null) ? "無" : campVO.getConfig()%>"></td>
			</tr>
			<tr>
				<td>無線通訊:</td>
				<td><input type="checkbox" name="wireless" value="有wifi">wifi

					<input type="checkbox" name="wireless" value="中華電信有訊號 ">
					中華電信 <input type="checkbox" name="wireless" value="遠傳有訊號">
					遠傳 <input type="checkbox" name="wireless" value="台哥大有訊號">
					台哥大 <input type="checkbox" name="wireless" value="亞太有訊號">
					亞太 <input type="checkbox" name="wireless" value="台灣之星有訊號">
					台灣之星</td>
			</tr>
			<tr>
				<td>寵物:</td>
				<td><input type="radio" name="pet" value="0"
					check="<%=(campVO == null) ? "" : (campVO.getPet() == 0 ? "true" : "")%>">不可攜帶寵物
					<input type="radio" name="pet" value="1"
					check="<%=(campVO == null) ? "" : (campVO.getPet() == 1 ? "true" : "")%>">可攜帶寵物</td>
			</tr>
			<tr>
				<td>營區設施:</td>
				<td><input type="TEXT" name="facility" size="45"
					value="<%=(campVO == null) ? "無" : campVO.getFacility()%>" /></td>
			</tr>
			<tr>
				<td>營業日:</td>
				<td><input type="radio" name="operate_Date" value="0"
					check="<%=(campVO == null) ? "" : (campVO.getOperate_Date() == 0 ? "true" : "")%>">只有平日
					<input type="radio" name="operate_Date" value="1"
					check="<%=(campVO == null) ? "" : (campVO.getOperate_Date() == 1 ? "true" : "")%>">只有假日
					<input type="radio" name="operate_Date" value="2"
					check="<%=(campVO == null) ? "" : (campVO.getOperate_Date() == 2 ? "true" : "")%>">平、假日皆營業</td>
			</tr>
			<tr>
				<td>停車方式:</td>
				<td><input type="TEXT" name="park" size="45"
					value="<%=(campVO == null) ? "集中停車" : campVO.getPark()%>" /></td>
			</tr>
			<tr>
				<td>縣市:</td>
				<td><input name="county" type="text"
					value="<%=(campVO == null) ? "桃園市" : campVO.getCounty()%>"></td>
			</tr>
			<tr>
				<td>鄉鎮市區:</td>
				<td><input name="district" type="text"
					value="<%=(campVO == null) ? "中壢區" : campVO.getDistrict()%>"></td>
			</tr>
			<tr>
				<td>地址:</td>
				<td><input name="address" type="text"
					value="<%=(campVO == null) ? "復興路46號" : campVO.getAddress()%>"></td>
			</tr>

		</table>
		<div>
			<c:forEach var="feature_listVO" items="${list}">
				<p>
					<input type="checkbox" name="feature_list"
						value="${feature_listVO.camp_fl_no}">${feature_listVO.camp_fl_name}</p>
			</c:forEach>
		</div>
		<table id="camp_plc">
			<tr>
				<th>營位名稱</th>
				<th>數量</th>
				<th>人數</th>
				<th>人數上限</th>
				<th>平日價格</th>
				<th>假日價格</th>
			</tr>
			<tr id="forcopy">
				<td><input type="text"></td>
				<td><input type="number" pattern="number"></td>
				<td><input type="number" pattern="number"></td>
				<td><input type="number" pattern="number"></td>
				<td><input type="number" pattern="number"></td>
				<td><input type="number" pattern="number"></td>
			</tr>
			<tr>
				<td><input name="plc1" type="text"></td>
				<td><input name="plc1" type="number" pattern="number"></td>
				<td><input name="plc1" type="number" pattern="number"></td>
				<td><input name="plc1" type="number" pattern="number"></td>
				<td><input name="plc1" type="number" pattern="number"></td>
				<td><input name="plc1" type="number" pattern="number"></td>
			</tr>
		</table>

		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
	<script>
		$("#camp_plc").keydown(function(e) {
			if (e.which == 13) {
				$("#forcopy").clone().appendTo("#camp_plc");
			}
		});
	</script>
</body>
</html>