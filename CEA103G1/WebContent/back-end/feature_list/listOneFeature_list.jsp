<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.feature_list.model.*"%>

<%
	List<Feature_ListVO> list = (List)request.getAttribute("feature_listVO");
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>�S����</title>
<style>
table, th, td {
	border: 1px solid black;
	background-color: white;
	text-align: center;
}
#name{
    width:250px;
}
div{
    background-color: lightgreen;
}
</style>
</head>
<body>
	<div>
		<h1>�S����</h1>
		<h3>
			<a href="<%=request.getContextPath()%>/back-end/feature_list/listAllFeature_list.jsp">�^�M��</a>
		</h3>
	</div>
	<table>
		<tr>
			<th>�S��s��</th>
			<th id="name">�S��W��</th>
			<th colspan="2">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/feature_list/feature_list.do">
					<b>��J�S��W��:</b> <input type="text" name="camp_fl_name"> <input
						type="hidden" name="action" value="getOne_For_Display"> <input
						type="submit" value="�e�X">
				</FORM>
			</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="feature_listVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${feature_listVO.camp_fl_no}</td>
				<td>${feature_listVO.camp_fl_name}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/feature_list/feature_list.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="camp_fl_no" value="${feature_listVO.camp_fl_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/feature_list/feature_list.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
							name="camp_fl_no" value="${feature_listVO.camp_fl_no}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
</body>
</html>