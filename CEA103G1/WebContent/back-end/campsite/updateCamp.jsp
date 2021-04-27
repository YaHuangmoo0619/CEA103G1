<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite.model.*"%>

<%
	CampVO campVO = (CampVO) request.getAttribute("campVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%=campVO == null%>--${empVO.deptno}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料修改 - update_emp_input.jsp</title>

<<style>
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
		<h1>營區修改資料</h1>

		<h3>
			<a href="<%=request.getContextPath()%>/back-end/campsite/listAllCamp.jsp">回清單</a>
		</h3>
	</div>

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
				<td>營區名稱:</td>
				<td><input type="TEXT" name="camp_name" size="45"
					value="<%=campVO.getCamp_name()%>" /></td>
			</tr>
			<tr>
				<td>營區資訊:</td>
				<td><input type="TEXT" name="campInfo" size="45"
					value="<%=campVO.getCampInfo()%>" /></td>
			</tr>
			<tr>
				<td>注意事項:</td>
				<td><input type="TEXT" name="note" size="45"
					value="<%=campVO.getNote()%>" /></td>
			</tr>
			<tr>
				<td>配置圖:</td>
				<td><img
					src="/CEA103G1/camp/campconfig.do?camp_no=${campVO.camp_no}"><input
					type="file" name="config"></td>
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
				<td><input type="radio" name="pet" value="0">不可攜帶寵物 <input
					type="radio" name="pet" value="1">可攜帶寵物</td>
			</tr>
			<tr>
				<td>營區設施:</td>
				<td><input type="TEXT" name="facility" size="45"
					value="<%=campVO.getFacility()%>" /></td>
			</tr>
			<tr>
				<td>營業日:</td>
				<td><input type="radio" name="operate_Date" value="0">只有平日
					<input type="radio" name="operate_Date" value="1">只有假日 <input
					type="radio" name="operate_Date" value="2">平、假日皆營業</td>
			</tr>
			<tr>
				<td>停車方式:</td>
				<td><input type="TEXT" name="park" size="45"
					value="<%=campVO.getPark()%>" /></td>
			</tr>
			<tr>
				<td>縣市:</td>
				<td><input name="county" type="text"
					value="<%=campVO.getCounty()%>"></td>
			</tr>
			<tr>
				<td>鄉鎮市區:</td>
				<td><input name="district" type="text"
					value="<%=campVO.getDistrict()%>"></td>
			</tr>
			<tr>
				<td>地址:</td>
				<td><input name="address" type="text"
					value="<%=campVO.getAddress()%>"></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="camp_no" value="<%=campVO.getCamp_no()%>">
		<input type="submit" value="送出修改">
	</FORM>
</body>

</html>