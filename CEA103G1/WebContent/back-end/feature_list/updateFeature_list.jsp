<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.feature_list.model.*"%>

<%
	Feature_ListVO feature_listVO = (Feature_ListVO) request.getAttribute("feature_listVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>修改特色資料</title>
<style>
table, th, td {
	border: 1px solid black;
	background-color: white;
	text-align: center;
}

#name {
	width: 250px;
}
div {
	background-color: lightgreen;
}
</style>
</head>
<body>
	<div>
		<h1>特色名稱修改</h1>
		<h3>
			<a href="<%=request.getContextPath()%>/back-end/feature_list/listAllFeature_list.jsp">回清單</a>
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

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/feature_list/feature_list.do"
		name="form1">
		<b><font color=red>只能是中、英文字母、數字和_ , 且長度必需在10之內</font></b>
		<table>
			<tr>
				<th>特色編號</th>
				<th id="name">特色名稱</th>
				<th>修改名稱</th>
			</tr>
			<tr>
				<td><%=feature_listVO.getCamp_fl_no()%></td>
				<td><%=feature_listVO.getCamp_fl_name()%></td>
				<td><b>輸入特色名稱:</b><input type="TEXT" name="camp_fl_name"
					size="45"
					value="<%=(feature_listVO.getCamp_fl_name() == null) ? "" : feature_listVO.getCamp_fl_name()%>" />
				<input type="submit" value="送出"></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="camp_fl_no"
			value="<%=feature_listVO.getCamp_fl_no()%>">
	</FORM>

</body>
</html>