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
<title>�ק�S����</title>
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
		<h1>�S��W�٭ק�</h1>
		<h3>
			<a href="<%=request.getContextPath()%>/back-end/feature_list/listAllFeature_list.jsp">�^�M��</a>
		</h3>
	</div>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/feature_list/feature_list.do"
		name="form1">
		<b><font color=red>�u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb10����</font></b>
		<table>
			<tr>
				<th>�S��s��</th>
				<th id="name">�S��W��</th>
				<th>�ק�W��</th>
			</tr>
			<tr>
				<td><%=feature_listVO.getCamp_fl_no()%></td>
				<td><%=feature_listVO.getCamp_fl_name()%></td>
				<td><b>��J�S��W��:</b><input type="TEXT" name="camp_fl_name"
					size="45"
					value="<%=(feature_listVO.getCamp_fl_name() == null) ? "" : feature_listVO.getCamp_fl_name()%>" />
				<input type="submit" value="�e�X"></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="camp_fl_no"
			value="<%=feature_listVO.getCamp_fl_no()%>">
	</FORM>

</body>
</html>