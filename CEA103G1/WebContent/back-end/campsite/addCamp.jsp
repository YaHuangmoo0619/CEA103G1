<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite.model.*"%>

<%
	CampVO campVO = (CampVO) request.getAttribute("campVO");
%>
<%-- <%=campVO == null%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
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
		name="form1" enctype="multipart/form-data">
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
				<td><input type="file" name="config"><input type="hidden" name="image" value="<%=(campVO == null) ? "無" : campVO.getConfig()%>"></td>
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
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>
</html>